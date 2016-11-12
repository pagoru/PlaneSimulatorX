package es.pagoru.planesimulatorx.windows;

/**
 * Created by Pablo on 30/10/2016.
 */
public class CreatePlaneMenuWindow extends MenuWindow {

    private Thread createPlaneMenuWindowThread;
    
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

    @Override
    public void draw() {
        //rawWindow = rawWindow.replaceAll("@(a)\\{(.*?)\\}", "______________________________________");
        super.draw();
    }
    
    public void setCreatePlaneMenuWindowThread(Thread thread){
        this.createPlaneMenuWindowThread = thread;
    }
    
    public String getRawWindow(){
        return this.rawWindow;
    }
    
    public void setRawWindow(String rawWindow){
        this.rawWindow = rawWindow;
    }
    
}