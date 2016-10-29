package es.pagoru.planesimulatorx.input.keys;

import es.pagoru.planesimulatorx.Window;
import es.pagoru.planesimulatorx.input.KeyI;
import es.pagoru.planesimulatorx.windows.MainMenuWindow;

import java.awt.event.KeyEvent;

/**
 * Created by Pablo on 29/10/2016.
 */
public class KeyEscape implements KeyI {

    @Override
    public int getKeyCode() {
        return KeyEvent.VK_ESCAPE;
    }

    @Override
    public void executePressed() {

    }

    @Override
    public void executeReleased() {
        MainMenuWindow mainMenuWindow = MainMenuWindow.INSTANCE;
        if(!mainMenuWindow.isInsideMenu()){
            mainMenuWindow.loadCurrentMenu();
            return;
        }
        Window.getInstance().close();
    }
}

