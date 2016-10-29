package es.pagoru.planesimulatorx.input.keys;

import es.pagoru.planesimulatorx.Window;
import es.pagoru.planesimulatorx.input.KeyI;
import es.pagoru.planesimulatorx.input.KeyListenerEvent;

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
    public KeyListenerEvent.KeyOption getKeyOption(){
        return KeyListenerEvent.KeyOption.BOTH;
    }

    @Override
    public void execute(){
        Window.getInstance().close();
    }
}
