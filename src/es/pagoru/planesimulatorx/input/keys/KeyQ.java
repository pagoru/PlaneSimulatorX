package es.pagoru.planesimulatorx.input.keys;

import es.pagoru.planesimulatorx.input.KeyInterface;
import es.pagoru.planesimulatorx.windows.menus.CockpitMenuWindow;
import es.pagoru.planesimulatorx.windows.MenuWindow;
import es.pagoru.planesimulatorx.windows.MenuWindows;

import java.awt.event.KeyEvent;

/**
 * Created by Pablo on 13/11/2016.
 */
public class KeyQ implements KeyInterface {
    @Override
    public int getKeyCode() {
        return KeyEvent.VK_Q;
    }

    /**
     * Quan la tecla Q es presa, els controls de l'avió actual
     * dins del menú del Cockpit activen o desactiven el motor.
     */
    @Override
    public void executePressed() {
        MenuWindow menuWindow = MenuWindows.getCurrentMenu();
        if(menuWindow instanceof CockpitMenuWindow) {
            CockpitMenuWindow cockpitMenuWindow = (CockpitMenuWindow) menuWindow;
            cockpitMenuWindow.getPlane().toggleEngine();
            cockpitMenuWindow.draw();
        }
    }

    @Override
    public void executeReleased() {

    }
}
