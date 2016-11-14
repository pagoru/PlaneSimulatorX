package es.pagoru.planesimulatorx.input.keys;

import es.pagoru.planesimulatorx.input.KeyInterface;
import es.pagoru.planesimulatorx.windows.menus.CockpitMenuWindow;
import es.pagoru.planesimulatorx.windows.MenuWindow;
import es.pagoru.planesimulatorx.windows.MenuWindows;

import java.awt.event.KeyEvent;

/**
 * Created by Pablo on 13/11/2016.
 */
public class KeyD implements KeyInterface {
    @Override
    public int getKeyCode() {
        return KeyEvent.VK_D;
    }

    /**
     * Quan la tecla D es presa, els controls de l'avió actual
     * dins del menú del Cockpit es mouen cap a la dreta.
     */
    @Override
    public void executePressed() {
        MenuWindow menuWindow = MenuWindows.getCurrentMenu();
        if(menuWindow instanceof CockpitMenuWindow){
            CockpitMenuWindow cockpitMenuWindow = (CockpitMenuWindow) menuWindow;
            cockpitMenuWindow.getPlane().moveFlightControlPositionLeftRight(true);
            cockpitMenuWindow.draw();
        }
    }

    @Override
    public void executeReleased() {

    }
}
