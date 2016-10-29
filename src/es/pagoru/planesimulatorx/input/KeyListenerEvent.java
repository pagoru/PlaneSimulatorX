package es.pagoru.planesimulatorx.input;

import es.pagoru.planesimulatorx.Window;
import es.pagoru.planesimulatorx.windows.MainMenuWindow;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Pablo on 29/10/2016.
 */
public class KeyListenerEvent implements KeyListener {

    private static KeyListenerEvent lastKeyboardEvent;

    private static void onEvent(KeyListenerEvent keyboardEvent){
        if(Window.getInstance().isFirstLoad()){
            MainMenuWindow.INSTANCE.firstLoad();
            return;
        }
        if(!keyboardEvent.equals(lastKeyboardEvent)){
            KeyI keyboardKey = KeyboardEventHandler.getKeyBoardKeyList().stream()
                    .filter(key -> key.getKeyCode() == keyboardEvent.getKeyCode()
                            && (key.getKeyOption().equals(keyboardEvent.getKeyOption())
                            || key.getKeyOption().equals(KeyListenerEvent.KeyOption.BOTH)
                    ))
                    .findFirst().orElse(null);
            if(keyboardKey != null){
                keyboardKey.execute();
            }
        }
        lastKeyboardEvent = keyboardEvent;
    }

    //Enum de las opciones de la key
    public enum KeyOption{
        PRESSED,
        RELEASED,
        BOTH
    }

    private KeyListenerEvent.KeyOption keyOption;
    private int keyCode;

    public KeyListenerEvent(){}

    private KeyListenerEvent(int keyCode, KeyListenerEvent.KeyOption keyOption){
        this.keyCode = keyCode;
        this.keyOption = keyOption;
    }

    private KeyListenerEvent.KeyOption getKeyOption() {
        return keyOption;
    }

    private int getKeyCode() {
        return keyCode;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null){
            if(obj instanceof KeyListenerEvent){
                KeyListenerEvent keyboardEvent = (KeyListenerEvent) obj;
                return keyboardEvent.getKeyOption() == getKeyOption()
                        && keyboardEvent.getKeyCode() == getKeyCode();
            }
        }
        return false;
    }

    //Parte KeyListener
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        onEvent(new KeyListenerEvent(e.getKeyCode(), KeyOption.PRESSED));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        onEvent(new KeyListenerEvent(e.getKeyCode(), KeyOption.RELEASED));
    }
}
