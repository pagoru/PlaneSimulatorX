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
                int idYaw = plane.getFlightControlPositionsLeftRight().getId();
                switch (idYaw){
                    case 1:
                        plane.addYaw(3);
                        break;
                    case 2:
                        plane.addYaw(1);
                        break;
                    case 4:
                        plane.addYaw(-1);
                        break;
                    case 5:
                        plane.addYaw(-3);
                        break;
                }
                int idPitch = plane.getFlightControlPositionsUpDown().ordinal();
                switch (idPitch){
                    case 0:
                        plane.subtractPitch(1);
                        break;
                    case 2:
                        plane.addPitch(1);
                        break;
                }
                int idThrottle = plane.getFlightControlThrottlePosition().ordinal();
                plane.addThrottle(idThrottle);
//                System.out.println(plane.getPitch());
                //TODO Coords Y

                System.out.println("throttle: " + plane.getThrottle() + " >> " + plane.getFlightControlThrottlePosition());

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
