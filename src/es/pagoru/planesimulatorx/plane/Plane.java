package es.pagoru.planesimulatorx.plane;

import es.pagoru.planesimulatorx.utils.Vector3Di;

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

    public Plane(String plate, String model, String brand){
        this.plate = plate;
        this.model = model;
        this.brand = brand;
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
}
