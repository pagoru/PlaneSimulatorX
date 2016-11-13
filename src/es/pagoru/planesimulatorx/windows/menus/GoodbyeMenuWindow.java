package es.pagoru.planesimulatorx.windows.menus;

import es.pagoru.planesimulatorx.Window;
import es.pagoru.planesimulatorx.windows.MenuWindow;

/**
 * Created by Pablo on 30/10/2016.
 */
public class GoodbyeMenuWindow extends MenuWindow {

    public GoodbyeMenuWindow() {
        super("GoodbyeWindow");
    }

    @Override
    public void draw(){
        Window.getInstance().loadWindow(getRawWindow());
    }
}