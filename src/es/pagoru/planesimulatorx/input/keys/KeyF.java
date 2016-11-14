package es.pagoru.planesimulatorx.input.keys;

import es.pagoru.planesimulatorx.input.KeyInterface;
import es.pagoru.planesimulatorx.windows.menus.CockpitMenuWindow;
import es.pagoru.planesimulatorx.windows.MenuWindow;
import es.pagoru.planesimulatorx.windows.MenuWindows;

import java.awt.event.KeyEvent;

/**
 * Created by Pablo on 13/11/2016.
 */
public class KeyF implements KeyInterface {
    @Override
    public int getKeyCode() {
        return KeyEvent.VK_F;
    }

    /**
     * Quan la tecla F es presa, els controls de l'avió actual
     * dins del menú del Cockpit disminueix la velocitat.
     */
    @Override
    public void executePressed() {
        MenuWindow menuWindow = MenuWindows.getCurrentMenu();
        if(menuWindow instanceof CockpitMenuWindow) {
            CockpitMenuWindow cockpitMenuWindow = (CockpitMenuWindow) menuWindow;
            cockpitMenuWindow.getPlane().moveFlightControlThrottlePosition(true);
            cockpitMenuWindow.draw();
        }
    }

    @Override
    public void executeReleased() {

    }
}
