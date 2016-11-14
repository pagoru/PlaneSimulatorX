package es.pagoru.planesimulatorx.input.keys;

import es.pagoru.planesimulatorx.input.KeyInterface;
import es.pagoru.planesimulatorx.windows.MenuWindow;
import es.pagoru.planesimulatorx.windows.MenuWindows;

import java.awt.event.KeyEvent;

/**
 * Created by Pablo on 29/10/2016.
 */
public class KeyDown implements KeyInterface {
    @Override
    public int getKeyCode() {
        return KeyEvent.VK_DOWN;
    }

    @Override
    public void executePressed() {

    }

    /**
     * Una vegada la flecha cap abaix es deixa anar,
     * es mou l'opció actual del menu cap a la següent.
     */
    @Override
    public void executeReleased() {
        MenuWindow menuWindow = MenuWindows.getCurrentMenu();
        menuWindow.moveSelection(false);
    }
}
