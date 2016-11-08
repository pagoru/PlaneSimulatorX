package es.pagoru.planesimulatorx.plane;

import es.pagoru.planesimulatorx.windows.CockpitMenuWindow;
import es.pagoru.planesimulatorx.windows.MenuWindows;

/**
 * Created by pablo on 3/11/16.
 */
public class CockpitMenuWindowThread extends Thread {

    private CockpitMenuWindow cockpitMenuWindow;

    public CockpitMenuWindowThread(){
        this.cockpitMenuWindow = (CockpitMenuWindow) MenuWindows.getCurrentMenu();
    }

    @Override
    public void run() {

        while(true){
            try {
                Plane plane = cockpitMenuWindow.getPlane();
                int id = plane.getFlightControlPositions().getId();
                switch (id){
                    case 1:
                        plane.addPitch(3);
                        break;
                    case 2:
                        plane.addPitch(1);
                        break;
                    case 4:
                        plane.addPitch(-1);
                        break;
                    case 5:
                        plane.addPitch(-3);
                        break;
                }
                cockpitMenuWindow.draw();

                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!(MenuWindows.getCurrentMenu() instanceof  CockpitMenuWindow)){
                break;
            }
        }
    }

}
