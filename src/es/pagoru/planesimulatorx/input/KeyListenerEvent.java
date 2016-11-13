package es.pagoru.planesimulatorx.input;

import es.pagoru.planesimulatorx.windows.menus.CreatePlaneMenuWindow;
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
        if(MenuWindows.getCurrentMenu() instanceof CreatePlaneMenuWindow){
            if((keyboardEvent.getKeyCode() >= 'A' && keyboardEvent.getKeyCode() <= 'Z')
                    || (keyboardEvent.getKeyCode() >= '0' && keyboardEvent.getKeyCode() <= '9')
                    || keyboardEvent.getKeyCode() == ' ' 
                    || keyboardEvent.getKeyCode() == KeyEvent.VK_DELETE){
                if(!pressed){
                    ((CreatePlaneMenuWindow) MenuWindows.getCurrentMenu()).onKeyEvent(keyboardEvent);
                }
            }
        }
        if(!keyboardEvent.equals(lastKeyboardEvent)){
            KeyInterface keyboardKey = KeyboardEventHandler.getKeyBoardKeyList().stream()
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

    public KeyListenerEvent.KeyOption getKeyOption() {
        return keyOption;
    }

    public int getKeyCode() {
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
