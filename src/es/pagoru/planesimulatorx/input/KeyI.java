package es.pagoru.planesimulatorx.input;

/**
 * Created by Pablo on 29/10/2016.
 */
public interface KeyI {

    int getKeyCode();
    //TODO Separar el execute del release/press
    void execute();
    KeyListenerEvent.KeyOption getKeyOption();

}
