package es.pagoru.planesimulatorx.windows;

import es.pagoru.planesimulatorx.Window;

import java.io.IOException;

/**
 * Created by Pablo on 29/10/2016.
 */
public enum MainMenuWindow {

    INSTANCE;

    private static Window window = Window.getInstance();
    private static String menuName = "MainMenu";

    private int currentOption;
    private boolean isInsideMenu = false;

    public void firstLoad(){

        currentOption = 0;
        window.setFirstLoad(false);
        isInsideMenu = true;
        loadCurrentMenu();

    }

    public boolean isInsideMenu(){
        return isInsideMenu;
    }

    public void moveMenu(boolean up){
        currentOption = up ? (currentOption == 0 ? 3 : currentOption - 1) : (currentOption == 3 ? 0 : currentOption + 1);
        loadCurrentMenu();
    }

    private void loadCurrentMenu(){

        try {
            window.loadWindow(menuName + (currentOption + 1), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
