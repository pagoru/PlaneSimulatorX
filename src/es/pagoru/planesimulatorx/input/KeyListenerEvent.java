package es.pagoru.planesimulatorx.input;

import es.pagoru.planesimulatorx.windows.menus.CreatePlaneMenuWindow;
import es.pagoru.planesimulatorx.windows.MenuWindows;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Pablo on 29/10/2016.
 */
public class KeyListenerEvent implements KeyListener {

    /**
     * Guarda l'ultim event realitzat.
     */
    private static KeyListenerEvent lastKeyboardEvent;

    /**
     * Comprova si es la primera key que es presa en el programa.
     * Només s'utilitza per poder premer qualsevol tecla en la finestrad e benvinguda.
     */
    private static boolean firstKey = true;

    /**
     * Event que es cridat cada vegada que es presa una tecla del teclat.
     * Comprova que les tecles introduides son correctes per el menú adequat.
     * @param keyboardEvent
     */
    private static void onEvent(KeyListenerEvent keyboardEvent){
        boolean pressed = keyboardEvent.getKeyState().equals(KeyState.PRESSED);
        if(firstKey){
            if(!pressed){
                MenuWindows.openMenu("MainMenu");
                firstKey = false;
            }
            return;
        }
        if(MenuWindows.getCurrentMenu() instanceof CreatePlaneMenuWindow){
            if((keyboardEvent.getKeyCode() >= 'A' && keyboardEvent.getKeyCode() <= 'Z')
                    || (keyboardEvent.getKeyCode() >= '0' && keyboardEvent.getKeyCode() <= '9')
                    || keyboardEvent.getKeyCode() == ' ' 
                    || keyboardEvent.getKeyCode() == KeyEvent.VK_DELETE){
                if(!pressed){
                    ((CreatePlaneMenuWindow) MenuWindows.getCurrentMenu()).onKeyEvent(keyboardEvent);
                }
            }
        }
        if(!keyboardEvent.equals(lastKeyboardEvent)){
            KeyInterface keyboardKey = KeyboardEventHandler.getKeyBoardKeyList().stream()
                    .filter(key -> key.getKeyCode() == keyboardEvent.getKeyCode())
                    .findFirst().orElse(null);
            if(keyboardKey != null){
                if(pressed){
                    keyboardKey.executePressed();
                } else {
                    keyboardKey.executeReleased();
                }
            }
        }
        lastKeyboardEvent = keyboardEvent;
    }

    /**
     * Enumeració dels estats que tenen les keys.
     */
    //Enum de las opciones de la key
    private enum KeyState {
        PRESSED,
        RELEASED
    }

    /**
     * Variable per guardar l'estat de la key actual.
     */
    private KeyState keyState;

    /**
     * Variable per guardar el keycode de la key actual.
     */
    private int keyCode;

    /**
     * Constructor buit que permet afegir aquesta clase als listener per defecte de java.
     */
    public KeyListenerEvent(){}

    /**
     * Constructor que es cridat per l'event d'aquesta clase (static) cada vegada que es prem una tecla.
     * @param keyCode
     * @param keyState
     */
    private KeyListenerEvent(int keyCode, KeyState keyState){
        this.keyCode = keyCode;
        this.keyState = keyState;
    }

    /**
     * Retorna l'estat de la key.
     * @return
     */
    public KeyState getKeyState() {
        return keyState;
    }

    /**
     * Retorna el key code, que es pot parsejar a char.
     * @return
     */
    public int getKeyCode() {
        return keyCode;
    }

    /**
     * Sobreescriptura del metode equals.
     * Compara de forma correcta que dos keyListenerevent siguin iguals en funcio de l'estat y al codi de la key.
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if(obj != null){
            if(obj instanceof KeyListenerEvent){
                KeyListenerEvent keyboardEvent = (KeyListenerEvent) obj;
                return keyboardEvent.getKeyState() == getKeyState()
                        && keyboardEvent.getKeyCode() == getKeyCode();
            }
        }
        return false;
    }

    /**
     * Implementació de la interficie KeyListener.
     * S'executará sempre que una key estigui presa.
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * Implementació de la interficie KeyListener.
     * S'executará només la primera vegada que una key es presa.
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        onEvent(new KeyListenerEvent(e.getKeyCode(), KeyState.PRESSED));
    }

    /**
     * Implementació de la interficie KeyListener.
     * S'executará només quan es deixi anar la tecla que s'esta prement.
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        onEvent(new KeyListenerEvent(e.getKeyCode(), KeyState.RELEASED));
    }
}
