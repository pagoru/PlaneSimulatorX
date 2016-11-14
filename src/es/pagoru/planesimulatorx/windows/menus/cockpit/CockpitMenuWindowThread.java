package es.pagoru.planesimulatorx.windows.menus.cockpit;

import es.pagoru.planesimulatorx.utils.Vector3Di;
import es.pagoru.planesimulatorx.windows.menus.CockpitMenuWindow;
import es.pagoru.planesimulatorx.windows.MenuWindows;

/**
 * Created by pablo on 3/11/16.
 */
public class CockpitMenuWindowThread extends Thread {

    /**
     * Instancia del menu del cockpit
     */
    private CockpitMenuWindow cockpitMenuWindow;

    public CockpitMenuWindowThread(){
        this.cockpitMenuWindow = (CockpitMenuWindow) MenuWindows.getCurrentMenu();
    }

    /**
     * Executa el thread en funció de l'avió existent.
     * 1. Comproba si els controls del yaw s'ha modificat.
     * 2. Comproba si els controls del pitch s'han modificat.
     * 3. Comproba la velocitat de l'avió.
     * 4. Comproba totes les variables per tal de que l'avió es pugui enlairar.
     * 5. Amaga el tren d'atterratge en cas de que es compleixin uns parametres.
     * 6. Dibuixa de nou el menu
     * x. En cas de que la finestra actual no sigui la del cockpit, es para el thread.
     */
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

                Vector3Di vector = new Vector3Di(0, 0, 0);
                
                int throttle = plane.getThrottle();
                float pitch = plane.getPitch();
                double yaw = Math.toRadians(plane.getYaw());
                
                double vX = Math.sin(yaw);
                double vZ = Math.cos(yaw);
                
                if(throttle > 0){//THROTTLE = 1 -> 570
                    
                    vector.addX((int)((throttle/50) * vX));
                    vector.addZ((int)((throttle/50) * vZ));
                    
                    if(throttle >= Plane.MIN_THROTTLE_TO_UP) {//THROTTLE = 400 -> 570)
                        
                        if(pitch > 5){
                            vector.substractX((int)((throttle/100) * vX));
                            vector.substractZ((int)((throttle/100) * vZ));
                            
                            vector.addY(throttle/(200 - (int)pitch));
                        } else if(pitch < -5){
                            vector.substractX((int)((throttle/25) * vX));
                            vector.substractZ((int)((throttle/25) * vZ));
                            vector.substractY(throttle/(100 - (int)pitch));
                        }
                        
                    } else {//THROTTLE = 1 -> 399
                        if(throttle < 80){
                            throttle = 80;
                        }
                        vector.substractY(throttle/(80 - (int)pitch));
                        
                    }
                    
                } else { //THROTTLE = 0
                    
                    if(plane.getPosition().getY() > 0){
                        vector.addX((int)(2 * vX));
                        vector.addZ((int)(2 * vZ));
                        vector.substractY(1);
                    }
                    
                }
                //Amagar tren d'atterratge 500metres & (300kmh) = 186mph
                if(plane.getPosition().getY() >= 500
                        && plane.getThrottle() >= 186
                        && plane.isUndercarriage()){
                    plane.setUndercarriage(false);
                }

                plane.getPosition().add(vector);
                
                if(plane.getPosition().getY() < 0){
                    plane.getPosition().setY(0);
                }
                
                cockpitMenuWindow.draw();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!(MenuWindows.getCurrentMenu() instanceof CockpitMenuWindow)){
                break;
            }
        }
    }

}
