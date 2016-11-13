package es.pagoru.planesimulatorx.input;

import es.pagoru.planesimulatorx.input.keys.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pablo on 29/10/2016.
 */
public class KeyboardEventHandler {

    private static List<KeyInterface> keyList = new ArrayList<>();

    public static List<KeyInterface> getKeyBoardKeyList(){
        return keyList;
    }

    public static void add(KeyInterface key){
        keyList.add(key);
    }

    public static void load(){
        add(new KeyEscape());
        add(new KeyUP());
        add(new KeyDown());
        add(new KeyEnter());
        add(new KeyD());
        add(new KeyA());
        add(new KeyQ());
        add(new KeyE());
        add(new KeyW());
        add(new KeyS());
        add(new KeyR());
        add(new KeyF());
        add(new KeyB());
        add(new KeyRight());
        add(new KeyLeft());
    }

}
