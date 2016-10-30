package es.pagoru.planesimulatorx.input;

import es.pagoru.planesimulatorx.windows.MenuWindows;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Pablo on 29/10/2016.
 */
public class KeyListenerEvent implements KeyListener {

    private static KeyListenerEvent lastKeyboardEvent;

    private static boolean firstKey = true;
    private static void onEvent(KeyListenerEvent keyboardEvent){
        boolean pressed = keyboardEvent.getKeyOption().equals(KeyOption.PRESSED);
        if(firstKey){
            if(!pressed){
                MenuWindows.openMenu("MainMenu");
                firstKey = false;
            }
            return;
        }
        if(!keyboardEvent.equals(lastKeyboardEvent)){
            KeyI keyboardKey = KeyboardEventHandler.getKeyBoardKeyList().stream()
                    .filter(key -> key.getKeyCode() == keyboardEvent.getKeyCode())
                    .findFirst().orElse(null);
            if(keyboardKey != null){
                if(pressed){
                    keyboardKey.executePressed();
                } else {
                    keyboardKey.executeReleased();
                }
            }
        }
        lastKeyboardEvent = keyboardEvent;
    }

    //Enum de las opciones de la key
    private enum KeyOption{
        PRESSED,
        RELEASED
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
