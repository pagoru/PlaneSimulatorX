package es.pagoru.planesimulatorx.input.keys;

import es.pagoru.planesimulatorx.input.KeyInterface;
import es.pagoru.planesimulatorx.windows.MenuWindows;

import java.awt.event.KeyEvent;

/**
 * Created by Pablo on 13/11/2016.
 */
public class KeyRight implements KeyInterface {
    @Override
    public int getKeyCode() {
        return KeyEvent.VK_RIGHT;
    }

    @Override
    public void executePressed() {
    }

    /**
     * Una vegada la flecha cap a la dreta es deixa anar,
     * es mou l'opció actual del menu cap a la següent.
     */
    @Override
    public void executeReleased() {
        MenuWindows.getCurrentMenu().moveSelection(false);
    }
}
