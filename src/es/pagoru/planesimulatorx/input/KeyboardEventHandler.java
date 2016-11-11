package es.pagoru.planesimulatorx.input;

import es.pagoru.planesimulatorx.input.keys.KeyDown;
import es.pagoru.planesimulatorx.input.keys.KeyEscape;
import es.pagoru.planesimulatorx.input.keys.KeyUP;
import es.pagoru.planesimulatorx.plane.CockpitMenuWindowThread;
import es.pagoru.planesimulatorx.windows.CockpitMenuWindow;
import es.pagoru.planesimulatorx.windows.MenuWindow;
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
                MenuWindow menuWindow = MenuWindows.getCurrentMenu();
                if(menuWindow.getName().equalsIgnoreCase("Cockpit")){
                    Thread t = new Thread(new CockpitMenuWindowThread());
                    t.start();
                }
                menuWindow.openCurrentSelection();
            }

        });
        add(new KeyI() {
            @Override
            public int getKeyCode() {
                return KeyEvent.VK_D;
            }

            @Override
            public void executePressed() {
                MenuWindow menuWindow = MenuWindows.getCurrentMenu();
                if(menuWindow instanceof CockpitMenuWindow){
                    CockpitMenuWindow cockpitMenuWindow = (CockpitMenuWindow) menuWindow;
                    cockpitMenuWindow.getPlane().moveFlightControlPositionLeftRight(true);
                    cockpitMenuWindow.draw();
                }
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
                MenuWindow menuWindow = MenuWindows.getCurrentMenu();
                if(menuWindow instanceof CockpitMenuWindow) {
                    CockpitMenuWindow cockpitMenuWindow = (CockpitMenuWindow) menuWindow;
                    cockpitMenuWindow.getPlane().moveFlightControlPositionLeftRight(false);
                    cockpitMenuWindow.draw();
                }
            }

            @Override
            public void executeReleased() {

            }
        });
        add(new KeyI(){
            @Override
            public int getKeyCode() {
                return KeyEvent.VK_Q;
            }

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
        });
        add(new KeyI(){
            @Override
            public int getKeyCode() {
                return KeyEvent.VK_E;
            }

            @Override
            public void executePressed() {
                MenuWindow menuWindow = MenuWindows.getCurrentMenu();
                if(menuWindow instanceof CockpitMenuWindow) {
                    CockpitMenuWindow cockpitMenuWindow = (CockpitMenuWindow) menuWindow;
                    cockpitMenuWindow.getPlane().toggleUndercarriage();
                    cockpitMenuWindow.draw();
                }
            }

            @Override
            public void executeReleased() {

            }
        });

    }

}
