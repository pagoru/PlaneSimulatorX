package es.pagoru.planesimulatorx;

/**
 * Created by pablo on 25/10/16.
 */
public class Plane {

    private String plate;

    private String brand;
    private String model;

    private boolean undercarriage;

    private boolean engine;
    private int velocity;
    private int kerosene;

    private Vector3Di position;
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

    public int getVelocity() {
        return velocity;
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
