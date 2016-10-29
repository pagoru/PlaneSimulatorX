package es.pagoru.planesimulatorx.input.keys;

import es.pagoru.planesimulatorx.input.KeyI;
import es.pagoru.planesimulatorx.input.KeyListenerEvent;
import es.pagoru.planesimulatorx.windows.MainMenuWindow;

import java.awt.event.KeyEvent;

/**
 * Created by Pablo on 29/10/2016.
 */
public class KeyUP implements KeyI {
    @Override
    public int getKeyCode() {
        return KeyEvent.VK_UP;
    }

    @Override
    public void execute() {
        MainMenuWindow mainMenuWindow = MainMenuWindow.INSTANCE;
        if(mainMenuWindow.isInsideMenu()){
            mainMenuWindow.moveMenu(true);
        }
    }

    @Override
    public KeyListenerEvent.KeyOption getKeyOption() {
        return KeyListenerEvent.KeyOption.RELEASED;
    }
}
