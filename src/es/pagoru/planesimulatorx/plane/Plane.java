package es.pagoru.planesimulatorx.plane;

import es.pagoru.planesimulatorx.utils.Vector3Di;
import es.pagoru.planesimulatorx.windows.CockpitMenuWindow;

/**
 * Created by pablo on 25/10/16.
 */
public class Plane {

    public enum FlightControlPositions {
        RIGHT_90(1),
        RIGHT_45(2),
        NORMAL(3),
        LEFT_45(4),
        LEFT_90(5);

        int id;

        FlightControlPositions(int id){
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

    private FlightControlPositions flightControlPositions;

    public Plane(String plate, String model, String brand){
        this.plate = plate;
        this.model = model;
        this.brand = brand;
        flightControlPositions = FlightControlPositions.NORMAL;
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

    public void setEngine(boolean engine) {
        this.engine = engine;
    }

    public void addPitch(float pitch){
        this.pitch += pitch;
        if(this.pitch < 0){
            this.pitch += 360;
        }
        this.pitch %= 360;
    }

    public FlightControlPositions getFlightControlPositions() {
        return flightControlPositions;
    }

    private void setFlightControlPositions(FlightControlPositions controlsPosition) {
        this.flightControlPositions = controlsPosition;
    }

    public void moveFlightControlPosition(boolean right){
        int id = getFlightControlPositions().getId();
        if(right){
            if(id > 1){
                setFlightControlPositions(FlightControlPositions.values()[id - 2]);
            }
            return;
        }
        if(id < 5){
            setFlightControlPositions(FlightControlPositions.values()[id]);
        }
    }

    public static void main(String[] args){
        Plane plane = new Plane("asd", "adsa", "asd");
        plane.addPitch(45);
        plane.addPitch(-67);

        System.out.println(plane.getPitch());
    }
}
