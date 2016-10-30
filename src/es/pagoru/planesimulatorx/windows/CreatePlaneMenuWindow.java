package es.pagoru.planesimulatorx.windows;

/**
 * Created by Pablo on 30/10/2016.
 */
public class CreatePlaneMenuWindow extends MenuWindow {

    public CreatePlaneMenuWindow() {
        super("CreatePlane");
    }

    @Override
    public void openCurrentSelection() {
        switch (getCurrentSelection()) {
            case "[c]":
                MenuWindows.closeLastMenu();
                break;
        }
    }
    
}