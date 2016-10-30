package es.pagoru.planesimulatorx.windows;

import es.pagoru.planesimulatorx.Window;

/**
 * Created by Pablo on 30/10/2016.
 */
public class GoodbyeMenuWindow extends MenuWindow {

    public GoodbyeMenuWindow() {
        super("GoodbyeWindow");
    }

    @Override
    public void draw(){
        Window.getInstance().loadWindow(rawWindow);
    }
}