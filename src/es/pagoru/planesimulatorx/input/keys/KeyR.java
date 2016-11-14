package es.pagoru.planesimulatorx.input.keys;

import es.pagoru.planesimulatorx.input.KeyInterface;
import es.pagoru.planesimulatorx.windows.menus.CockpitMenuWindow;
import es.pagoru.planesimulatorx.windows.MenuWindow;
import es.pagoru.planesimulatorx.windows.MenuWindows;

import java.awt.event.KeyEvent;

/**
 * Created by Pablo on 13/11/2016.
 */
public class KeyR implements KeyInterface {
    @Override
    public int getKeyCode() {
        return KeyEvent.VK_R;
    }

    /**
     * Quan la tecla R es presa, els controls de l'avió actual
     * dins del menú del Cockpit aumenten la velocitat.
     */
    @Override
    public void executePressed() {
        MenuWindow menuWindow = MenuWindows.getCurrentMenu();
        if(menuWindow instanceof CockpitMenuWindow) {
            CockpitMenuWindow cockpitMenuWindow = (CockpitMenuWindow) menuWindow;
            cockpitMenuWindow.getPlane().moveFlightControlThrottlePosition(false);
            cockpitMenuWindow.draw();
        }
    }

    @Override
    public void executeReleased() {

    }
}
