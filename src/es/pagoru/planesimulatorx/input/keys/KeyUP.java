package es.pagoru.planesimulatorx.input.keys;

import es.pagoru.planesimulatorx.input.KeyInterface;
import es.pagoru.planesimulatorx.windows.MenuWindows;

import java.awt.event.KeyEvent;

/**
 * Created by Pablo on 29/10/2016.
 */
public class KeyUP implements KeyInterface {
    @Override
    public int getKeyCode() {
        return KeyEvent.VK_UP;
    }

    @Override
    public void executePressed() {

    }

    @Override
    public void executeReleased() {
        MenuWindows.getCurrentMenu().moveSelection(true);
    }
}
