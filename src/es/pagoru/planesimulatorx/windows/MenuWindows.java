package es.pagoru.planesimulatorx.windows;

import es.pagoru.planesimulatorx.utils.Vector3Di;
import es.pagoru.planesimulatorx.windows.cockpit.CockpitMenuWindowThread;
import es.pagoru.planesimulatorx.windows.cockpit.Plane;
import es.pagoru.planesimulatorx.windows.create.CreatePlaneMenuWindowThread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pablo on 30/10/2016.
 */
public class MenuWindows {

    private static List<MenuWindow> menuWindowList = new ArrayList<>();

    private static List<String> currentMenus = new ArrayList<>();

    public static void load(){
        menuWindowList.add(new MainMenuWindow());
        menuWindowList.add(new CreatePlaneMenuWindow());
        menuWindowList.add(new GoodbyeMenuWindow());
        menuWindowList.add(new CockpitMenuWindow());
        menuWindowList.add(new MenuWindow("Info"));

        CockpitMenuWindow cockpitMenuWindow = ((CockpitMenuWindow)getMenuWindow("Cockpit"));
        cockpitMenuWindow.addPlane(
            new Plane(
                    "A300",
                    "AIRBUS",
                    "AA3HKK",
                    "AMERICAN AIRLINES",
                    97,
                    8000,
                    new Vector3Di(0, 0, 0)
            )
        );
    }

    public static MenuWindow getMenuWindow(String name){
        return menuWindowList.stream().filter(m -> m.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public static void openMenu(String name){
        currentMenus.add(name);
        Thread thread;
        switch(name){
            case "Cockpit":
                CockpitMenuWindow cockpitMenuWindow = ((CockpitMenuWindow)getCurrentMenu());
                thread = new CockpitMenuWindowThread();
                cockpitMenuWindow.setCockpitMenuWindowThread(thread);
                thread.start();
                break;
            case "CreatePlane":
                CreatePlaneMenuWindow createPlaneMenuWindow = ((CreatePlaneMenuWindow)getCurrentMenu());
                thread = new CreatePlaneMenuWindowThread();
                createPlaneMenuWindow.setCreatePlaneMenuWindowThread(thread);
                thread.start();
                break;
        }
        getMenuWindow(name).draw();
    }

    public static MenuWindow getCurrentMenu(){
        return getMenuWindow(currentMenus.get(currentMenus.size() - 1));
    }

    public static void closeLastMenu(){
        currentMenus.remove(currentMenus.size() - 1);
        MenuWindows.openMenu(getCurrentMenu().getName());
        
    }

}
