package es.pagoru.planesimulatorx.input;

import es.pagoru.planesimulatorx.input.keys.KeyDown;
import es.pagoru.planesimulatorx.input.keys.KeyEscape;
import es.pagoru.planesimulatorx.input.keys.KeyUP;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pablo on 29/10/2016.
 */
public class KeyboardEventHandler {

    private static List<KeyI> keyList = new ArrayList<>();

    public static List<KeyI> getKeyBoardKeyList(){
        return keyList;
    }

    public static void add(KeyI key){
        keyList.add(key);
    }

    public static void load(){

        add(new KeyEscape());
        add(new KeyUP());
        add(new KeyDown());

    }

}
