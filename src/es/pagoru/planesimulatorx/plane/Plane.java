package es.pagoru.planesimulatorx.plane;

import es.pagoru.planesimulatorx.utils.Vector3Di;

/**
 * Created by pablo on 25/10/16.
 */
public class Plane {

    public enum FlightControlPositionsUpDown {
        UP,
        NORMAL,
        DOWN;
    }

    public enum FlightControlPositionsLeftRight {
        RIGHT_90(1),
        RIGHT_45(2),
        NORMAL(3),
        LEFT_45(4),
        LEFT_90(5);

        int id;

        FlightControlPositionsLeftRight(int id){
            this.id = id;
        }

        public int getId(){
            return id;
        }

        public String getFileName(){
            return "cockpit_control_" + getId();
        }
    }

    private String plate;

    private String brand;
    private String model;

    private boolean undercarriage;

    private boolean engine;
    private int kerosene;

    private Vector3Di position;
    
    private int throttle;
    private float yaw;
    private float pitch;

    private FlightControlPositionsLeftRight flightControlPositionsLeftRight;
    private FlightControlPositionsUpDown flightControlPositionsUpDown;

    public Plane(String plate, String model, String brand){
        this.plate = plate;
        this.model = model;
        this.brand = brand;
        flightControlPositionsLeftRight = FlightControlPositionsLeftRight.NORMAL;
        flightControlPositionsUpDown = FlightControlPositionsUpDown.NORMAL;
        this.engine = false;
        this.undercarriage = true;
        position = new Vector3Di(0, 0, 0);
    }

    public String getPlate() {
        return plate;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public boolean isUndercarriage() {
        return undercarriage;
    }

    public boolean isEngine() {
        return engine;
    }

    public int getThrottle() {
        return throttle;
    }

    public int getKerosene() {
        return kerosene;
    }

    public Vector3Di getPosition(){
        return position;
    }

    public float getYaw(){
        return yaw;
    }

    public float getPitch(){
        return pitch;
    }

    public void setUndercarriage(boolean undercarriage) {
        this.undercarriage = undercarriage;
    }

    /*
    Capturar error conforme no se puede subir porque esta tocando el suelo.
     */
    public boolean toggleUndercarriage(){
        if(isUndercarriage()){
            if(position.getY() == 0){
                return false;
            }
        }
        setUndercarriage(!isUndercarriage());
        return true;
    }

    private void setEngine(boolean engine) {
        this.engine = engine;
    }

    public void toggleEngine(){
        if(!this.engine){
            new Thread(){
                @Override
                public void run() {
                    try{
                        Thread.sleep(3000);
                    } catch(Exception e){}
                    //TODO Mostrar tiempo en cockpit
                    setEngine(true);
                }
            }.start();
            return;
        }
        setEngine(false);
    }

    public void addYaw(float yaw){
        if(isEngine()){
            this.yaw += yaw;
            if(this.yaw < 0){
                this.yaw += 360;
            }
            this.yaw %= 360;
        }
    }

    public void addPitch(float pitch){
        if(isEngine()){
            this.pitch += pitch;
            if(this.pitch < 0){
                this.pitch += 360;
            }
            this.pitch %= 360;
        }
    }

    public FlightControlPositionsUpDown getFlightControlPositionsUpDown() {
        return flightControlPositionsUpDown;
    }

    public void setFlightControlPositionsUpDown(FlightControlPositionsUpDown flightControlPositionsUpDown) {
        this.flightControlPositionsUpDown = flightControlPositionsUpDown;
    }

    public void moveFlightControlPositionsUpDown(boolean up){
        int id = getFlightControlPositionsUpDown().ordinal();
        if(up){
            if(id > 0){
                setFlightControlPositionsUpDown(FlightControlPositionsUpDown.values()[id - 1]);
            }
            return;
        }
        if(id < FlightControlPositionsUpDown.values().length - 1){
            setFlightControlPositionsUpDown(FlightControlPositionsUpDown.values()[id + 1]);
        }
    }

/*    public static void main(String[] args){
        Plane plane = new Plane("asd", "adsa", "asd");
        plane.addPitch(45);
        plane.addPitch(-67);

        plane.moveFlightControlPositionsUpDown(true);
        plane.moveFlightControlPositionsUpDown(true);

        System.out.println(plane.getFlightControlPositionsUpDown());
    }*/

    public FlightControlPositionsLeftRight getFlightControlPositionsLeftRight() {
        return flightControlPositionsLeftRight;
    }

    private void setFlightControlPositionsLeftRight(FlightControlPositionsLeftRight controlsPosition) {
        this.flightControlPositionsLeftRight = controlsPosition;
    }

    public void moveFlightControlPositionLeftRight(boolean right){
        int id = getFlightControlPositionsLeftRight().getId();
        if(right){
            if(id > 1){
                setFlightControlPositionsLeftRight(FlightControlPositionsLeftRight.values()[id - 2]);
            }
            return;
        }
        if(id < FlightControlPositionsLeftRight.values().length){
            setFlightControlPositionsLeftRight(FlightControlPositionsLeftRight.values()[id]);
        }
    }
}
