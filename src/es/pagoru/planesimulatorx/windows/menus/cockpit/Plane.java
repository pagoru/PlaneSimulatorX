package es.pagoru.planesimulatorx.windows.menus.cockpit;

import es.pagoru.planesimulatorx.utils.Vector3Di;

/**
 * Created by pablo on 25/10/16.
 */
public class Plane {

    /**
     * Velocitat mínima per enlairarse.
     */
    public static final int MIN_THROTTLE_TO_UP = 111; //111mph == 180kmh

    /**
     * Velocitat máxima de l'avió.
     */
    private static final int MAX_THROTTLE = 570;

    /**
     * Enumeració de les diverses potencies que te l'avió.
     */
    public enum FlightControlThrottlePosition {
        REVERSE_1,
        OFF,
        POWER_1,
        POWER_2;

        /**
         * Retorna el nom de l'arxiu en funció del número del enum.
         * @return
         */
        public String getFileName() {
            return "cockpit_throttle_" + ordinal();
        }
    }

    /**
     * Enumeració de les posicions dels controls de l'avió adalt i abaix.
     */
    public enum FlightControlPositionsUpDown {
        UP,
        NORMAL,
        DOWN
    }

    /**
     * Enumeració de les posicions dels controls de l'avió a esquerra i dreta.
     */
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

        /**
         * Retorna el identificador del enum.
         * @return
         */
        public int getId(){
            return id;
        }

        /**
         * Retorna el nom de l'arxiu en funció del número del enum.
         * @return
         */
        public String getFileName(){
            return "cockpit_control_" + getId();
        }
    }

    /**
     * Matricula de l'avió
     */
    private String plate;

    /**
     * Fabricant de l'avió.
     */
    private String brand;

    /**
     * Model de l'avió.
     */
    private String model;

    /**
     * Propietari de l'avió
     */
    private String owner;

    /**
     * Capacitat de persones que pot portar l'avió.
     */
    private int capacity;

    /**
     * Si esta desplegat el tren d'atterratge
     */
    private boolean undercarriage;

    /**
     * Si esta encés el motor.
     */
    private boolean engine;

    /**
     * Si el motor s'esta encenet.
     */
    private boolean engineTurningOn;

    /**
     * Recompte de motors encesos.
     */
    private int enginesOn;

    /**
     * Querosè que conte l'avió.
     */
    private int kerosene;

    /**
     * Si els frens estan posats.
     */
    private boolean breaks;

    /**
     * Vector amb les posicions(x, y, z) que te l'avio actuals.
     */
    private Vector3Di position;

    /**
     * Velocitat de l'avió
     */
    private int throttle;

    /**
     * Yaw de l'avió en angles.
     */
    private float yaw;

    /**
     * Pitch de l'avió en angles.
     */
    private float pitch;

    /**
     * Enum de la posició dels controls en dreta i esquerra.
     */
    private FlightControlPositionsLeftRight flightControlPositionsLeftRight;

    /**
     * Enum de la posició dels controls adalt i abaix.
     */
    private FlightControlPositionsUpDown flightControlPositionsUpDown;

    /**
     * Enum de la posició de les velocitat.
     */
    private FlightControlThrottlePosition flightControlThrottlePosition;

    public Plane(String model, String brand, String plate){
        this.plate = plate;
        this.model = model;
        this.brand = brand;
        this.engine = false;
        this.undercarriage = true;
        this.position = new Vector3Di(0, 0, 0);
        this.throttle = 0;
        this.enginesOn = 0;
        this.engineTurningOn = false;
        breaks = true;

        this.flightControlPositionsLeftRight = FlightControlPositionsLeftRight.NORMAL;
        this.flightControlPositionsUpDown = FlightControlPositionsUpDown.NORMAL;
        this.flightControlThrottlePosition = FlightControlThrottlePosition.OFF;
    }
    
    public Plane(String model, String brand, String plate, String owner, int capacity, int kerosene, Vector3Di position){
        this(model, brand, plate);
        this.owner = owner;
        this.capacity = capacity;
        this.kerosene = kerosene;
        this.position = position;
    }

    /**
     * Retorna el propietari.
     * @return
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Retorna si els frens estan posats.
     * @return
     */
    public boolean isBreaks() {
        return breaks;
    }

    /**
     * Intercanvia si els frens estan posats i els treu, en cas contrari els posa.
     */
    public void toggleBreaks(){
        setBreaks(!isBreaks());
    }

    /**
     * Posa l'estat dels frens amb un bool.
     * @param breaks
     */
    private void setBreaks(boolean breaks) {
        this.breaks = breaks;
    }

    /**
     * Retorna la quantitat de motors que es troben encesos.
     * @return
     */
    public int getEnginesOn() {
        return enginesOn;
    }

    /**
     * Retorna la amtricula de l'avió.
     * @return
     */
    public String getPlate() {
        return plate.substring(0, plate.length() > 6 ? 6 : plate.length());
    }

    /**
     * Retorna el fabricant de l'avió.
     * @return
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Retorna el model de l'avió.
     * @return
     */
    public String getModel() {
        return model;
    }

    /**
     * Retorna si el tren d'atterratge esta baixat o no.
     * @return
     */
    public boolean isUndercarriage() {
        return undercarriage;
    }

    /**
     * Retorna si el motro esta encés.
     * @return
     */
    public boolean isEngine() {
        return engine;
    }

    /**
     * Retorna la velocitat en mph
     * @return
     */
    public int getThrottle() {
        return throttle;
    }

    /**
     * Retorna la quantitat de combustible que te l'avió.
     * @return
     */
    public int getKerosene() {
        return kerosene;
    }

    /**
     * Retorna la posició de l'avió en un vector(x, y, z)
     * @return
     */
    public Vector3Di getPosition(){
        return position;
    }

    /**
     * Retorna el yaw de l'avió.
     * @return
     */
    public float getYaw(){
        return yaw;
    }

    /**
     * Retorna el Pitch de l'avió.
     * @return
     */
    public float getPitch(){
        return pitch;
    }

    /**
     * Estableix mitjançant un bool la posició del tren d'aterratge.
     * @param undercarriage
     */
    public void setUndercarriage(boolean undercarriage) {
        this.undercarriage = undercarriage;
    }

    /**
     * Intercanvia la posició del tren d'aterratge,
     * si la posició de l'avió y es 0, no es pot treure.
     * @return
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

    /**
     * Estableix l'estat del motor.
     * @param engine
     */
    private void setEngine(boolean engine) {
        this.engine = engine;
    }

    /**
     * Intercanvia l'estat del motor,
     * en cas de volerse encendre, aquest trigará 4 segons,
     * un per cada motor.
     */
    public void toggleEngine(){
        if(!engineTurningOn){
            if(!this.engine){
                engineTurningOn = true;
                new Thread(){
                    @Override
                    public void run() {
                        try{
                            Thread.sleep(1000);
                            enginesOn = 1;
                            Thread.sleep(1000);
                            enginesOn = 2;
                            Thread.sleep(1000);
                            enginesOn = 3;
                            Thread.sleep(1000);
                            enginesOn = 4;
                            engineTurningOn = false;
                        } catch(Exception e){}
                        setEngine(true);
                    }
                }.start();
                return;
            }
            setEngine(false);
            enginesOn = 0;
        }
    }

    /**
     * Afegeix yaw al actual sempre que no estiguin possats els frens.
     * @param yaw
     */
    public void addYaw(float yaw){
        if (isBreaks()) {
            return;
        }
        this.yaw += yaw;
        if(this.yaw < 0){
            this.yaw += 360;
        }
        this.yaw %= 360;
    }

    /**
     * Asigna el pitch actual, sempre que no estiguin possats els frens.
     * @param pitch
     */
    private void setPitch(float pitch){
        if (isBreaks()) {
            return;
        }
        this.pitch = pitch;
    }

    /**
     * Afegeix pitch al actual amb limit de 45
     * @param pitch
     */
    public void addPitch(float pitch){
        if(getPitch() < 45){
            setPitch(getPitch() + pitch);
        }
    }

    /**
     * Extreu pitch al actual amb limit de -45 sempre que la posició
     * sigui superior a 0.
     * @param pitch
     */
    public void subtractPitch(float pitch){
        if(getPosition().getY() > 0 || getPitch() > 0){
            if(getPitch() > -45){
                setPitch(getPitch() - pitch);
            }
        }
    }

    /**
     * Asigna la velocitat.
     * @param throttle
     */
    private void setThrottle(int throttle){
        this.throttle = throttle;
    }

    /**
     * Afegeix velocitat, comprovant que no s'excedeixi el limit ni el minim de 0.
     * En cas de perdua, també s'aumentarà la velocitat.
     * @param throttle
     */
    public void addThrottle(int throttle){
        if(isEngine()){
            if(throttle > 1){
                if(getThrottle() < MAX_THROTTLE){
                    setThrottle(getThrottle() + (throttle - 1));
                }
            } else if(throttle < 1){
                if(getThrottle() > 1){
                    setThrottle(getThrottle() - 3);
                }
                if(getThrottle() > -5 && getThrottle() < 5){
                    setThrottle(0);
                }
            }
            return;
        }
        if(getThrottle() > 0){
            setThrottle(getThrottle() - 1);
        }
        if(getThrottle() > -5 && getThrottle() < 5){
            setThrottle(0);
        }
    }

    /**
     * Retorna l'enum de la posició del cockpit en la velocitat.
     * @return
     */
    public FlightControlThrottlePosition getFlightControlThrottlePosition() {
        return flightControlThrottlePosition;
    }

    /**
     * Asigna el enum de la posició del cockpit en la velocitat.
     * @param flightControlThrottlePosition
     */
    public void setFlightControlThrottlePosition(FlightControlThrottlePosition flightControlThrottlePosition) {
        this.flightControlThrottlePosition = flightControlThrottlePosition;
    }

    /**
     * Mou l'enum de la posició del cockpit en la velocitat.
     * @param up
     */
    public void moveFlightControlThrottlePosition(boolean up){
        int id = getFlightControlThrottlePosition().ordinal();
        if(up){
            if(id > 0){
                setFlightControlThrottlePosition(FlightControlThrottlePosition.values()[id - 1]);
            }
            return;
        }
        if(id < FlightControlThrottlePosition.values().length - 1){
            setFlightControlThrottlePosition(FlightControlThrottlePosition.values()[id + 1]);
        }
    }

    /**
     * Retorna l'enum de la posició dels controls del cockpit amunt i abaix.
     * @return
     */
    public FlightControlPositionsUpDown getFlightControlPositionsUpDown() {
        return flightControlPositionsUpDown;
    }

    /**
     * Assigna l'enum de la posició dels controls del cockpit amunt i abaix.
     * @param flightControlPositionsUpDown
     */
    public void setFlightControlPositionsUpDown(FlightControlPositionsUpDown flightControlPositionsUpDown) {
        this.flightControlPositionsUpDown = flightControlPositionsUpDown;
    }

    /**
     * Mou l'enum de la posició dels controls del cockpit amunt i abaix.
     * @param up
     */
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

    /**
     * Retorna l'enum de la posició dels controls del cockpit a la dreta i esquerra.
     * @return
     */
    public FlightControlPositionsLeftRight getFlightControlPositionsLeftRight() {
        return flightControlPositionsLeftRight;
    }

    /**
     * Assigna l'enum de la posició dels controls del cockpit a la dreta i esquerra.
     * @param flightControlPositionsLeftRight
     */
    private void setFlightControlPositionsLeftRight(FlightControlPositionsLeftRight flightControlPositionsLeftRight) {
        this.flightControlPositionsLeftRight = flightControlPositionsLeftRight;
    }

    /**
     * Mou l'enum de la posició dels controls del cockpit a la dreta i esquerra.
     * @param right
     */
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
