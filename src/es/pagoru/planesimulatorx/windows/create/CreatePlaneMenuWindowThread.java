package es.pagoru.planesimulatorx.windows.create;

import es.pagoru.planesimulatorx.windows.CreatePlaneMenuWindow;
import es.pagoru.planesimulatorx.windows.MenuWindows;

/**
 * Created by Pablo on 12/11/2016.
 */
@Deprecated
public class CreatePlaneMenuWindowThread extends Thread {
    
    private CreatePlaneMenuWindow createPlaneMenuWindow;
    
    public CreatePlaneMenuWindowThread(){
        createPlaneMenuWindow = (CreatePlaneMenuWindow) MenuWindows.getCurrentMenu();
    }
    
    @Override
    public void run() {
        
        while(true){
            
            if(!(MenuWindows.getCurrentMenu() instanceof CreatePlaneMenuWindow)){
                break;
            }
        }
        
    }
}
