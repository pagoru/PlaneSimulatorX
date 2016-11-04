package es.pagoru.planesimulatorx.plane;

import es.pagoru.planesimulatorx.windows.CockpitMenuWindow;
import es.pagoru.planesimulatorx.windows.MenuWindows;

/**
 * Created by pablo on 3/11/16.
 */
public class PlaneThread implements Runnable {

    private CockpitMenuWindow cockpitMenuWindow;

    public PlaneThread(){
        this.cockpitMenuWindow = (CockpitMenuWindow) MenuWindows.getCurrentMenu();
    }

    @Override
    public void run() {

        while(true){
            try {
                int id = cockpitMenuWindow.getControlsPosition().getId();
                switch (id){
                    case 1:
                        cockpitMenuWindow.x += 2;
                        break;
                    case 2:
                        cockpitMenuWindow.x++;
                        break;
                    case 4:
                        cockpitMenuWindow.x--;
                        break;
                    case 5:
                        cockpitMenuWindow.x -= 2;
                        break;
                }
                cockpitMenuWindow.draw();

                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
