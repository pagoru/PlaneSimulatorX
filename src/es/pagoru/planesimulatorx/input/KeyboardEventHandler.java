package es.pagoru.planesimulatorx.input;

import es.pagoru.planesimulatorx.input.keys.KeyDown;
import es.pagoru.planesimulatorx.input.keys.KeyEscape;
import es.pagoru.planesimulatorx.input.keys.KeyUP;
import es.pagoru.planesimulatorx.plane.CockpitMenuWindowThread;
import es.pagoru.planesimulatorx.windows.CockpitMenuWindow;
import es.pagoru.planesimulatorx.windows.MenuWindows;

import java.awt.event.KeyEvent;
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
        add(new KeyI() {
            @Override
            public int getKeyCode() {
                return KeyEvent.VK_ENTER;
            }

            @Override
            public void executePressed() {

            }

            @Override
            public void executeReleased() {
                MenuWindows.getCurrentMenu().openCurrentSelection();
                if(MenuWindows.getCurrentMenu().getName().equalsIgnoreCase("Cockpit")){
                    Thread t = new Thread(new CockpitMenuWindowThread());
                    t.start();
                }
            }

        });
        add(new KeyI() {
            @Override
            public int getKeyCode() {
                return KeyEvent.VK_D;
            }

            @Override
            public void executePressed() {
                CockpitMenuWindow cockpitMenuWindow = ((CockpitMenuWindow)MenuWindows.getCurrentMenu());
                cockpitMenuWindow.getPlane().moveFlightControlPosition(true);
                cockpitMenuWindow.draw();
            }

            @Override
            public void executeReleased() {

            }
        });
        add(new KeyI() {
            @Override
            public int getKeyCode() {
                return KeyEvent.VK_A;
            }

            @Override
            public void executePressed() {
                CockpitMenuWindow cockpitMenuWindow = ((CockpitMenuWindow)MenuWindows.getCurrentMenu());
                cockpitMenuWindow.getPlane().moveFlightControlPosition(false);
                cockpitMenuWindow.draw();
            }

            @Override
            public void executeReleased() {

            }
        });

    }

}
