package es.pagoru.planesimulatorx.input.keys;

import es.pagoru.planesimulatorx.input.KeyInterface;
import es.pagoru.planesimulatorx.windows.menus.CockpitMenuWindow;
import es.pagoru.planesimulatorx.windows.MenuWindow;
import es.pagoru.planesimulatorx.windows.MenuWindows;
import es.pagoru.planesimulatorx.windows.menus.cockpit.CockpitMenuWindowThread;

import java.awt.event.KeyEvent;

/**
 * Created by Pablo on 13/11/2016.
 */
public class KeyEnter implements KeyInterface {
    @Override
    public int getKeyCode() {
        return KeyEvent.VK_ENTER;
    }

    @Override
    public void executePressed() {

    }

    @Override
    public void executeReleased() {
        MenuWindow menuWindow = MenuWindows.getCurrentMenu();
        if(menuWindow instanceof CockpitMenuWindow 
                && !(MenuWindows.getCurrentMenu() instanceof CockpitMenuWindow)){
            Thread t = new Thread(new CockpitMenuWindowThread());
            t.start();
        }
        menuWindow.openCurrentSelection();
    }
}
