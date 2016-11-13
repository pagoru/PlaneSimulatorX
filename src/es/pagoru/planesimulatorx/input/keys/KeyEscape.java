package es.pagoru.planesimulatorx.input.keys;

import es.pagoru.planesimulatorx.Window;
import es.pagoru.planesimulatorx.input.KeyInterface;
import es.pagoru.planesimulatorx.windows.menus.GoodbyeMenuWindow;
import es.pagoru.planesimulatorx.windows.menus.MainMenuWindow;
import es.pagoru.planesimulatorx.windows.MenuWindow;
import es.pagoru.planesimulatorx.windows.MenuWindows;

import java.awt.event.KeyEvent;

/**
 * Created by Pablo on 29/10/2016.
 */
public class KeyEscape implements KeyInterface {

    @Override
    public int getKeyCode() {
        return KeyEvent.VK_ESCAPE;
    }

    @Override
    public void executePressed() {

    }

    @Override
    public void executeReleased() {
        MenuWindow menuWindow = MenuWindows.getCurrentMenu();
        if(menuWindow instanceof GoodbyeMenuWindow){
            return;
        }
        if(!(menuWindow instanceof MainMenuWindow)){
            MenuWindows.closeLastMenu();
            return;
        }
        Window.getInstance().close();
    }
}

