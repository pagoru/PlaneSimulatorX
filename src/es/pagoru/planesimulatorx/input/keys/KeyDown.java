package es.pagoru.planesimulatorx.input.keys;

import es.pagoru.planesimulatorx.input.KeyI;
import es.pagoru.planesimulatorx.windows.MainMenuWindow;

import java.awt.event.KeyEvent;

/**
 * Created by Pablo on 29/10/2016.
 */
public class KeyDown implements KeyI {
    @Override
    public int getKeyCode() {
        return KeyEvent.VK_DOWN;
    }

    @Override
    public void executePressed() {

    }

    @Override
    public void executeReleased() {
        MainMenuWindow mainMenuWindow = MainMenuWindow.INSTANCE;
        if(mainMenuWindow.isInsideMenu()){
            mainMenuWindow.moveMenu(false);
        }
    }
}
