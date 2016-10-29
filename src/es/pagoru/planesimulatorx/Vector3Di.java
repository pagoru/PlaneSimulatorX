package es.pagoru.planesimulatorx;

/**
 * Created by pablo on 25/10/16.
 */
public class Vector3Di {

    private int x;
    private int y;
    private int z;

    public Vector3Di(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Retorna X
     * @return
     */
    public int getX(){
        return x;
    }

    /**
     * Retorna Y
     * @return
     */
    public int getY(){
        return y;
    }

    /**
     * Retorna Z
     * @return
     */
    public int getZ(){
        return z;
    }

    /**
     * Suma el vector especificat al actual
     * @param vector
     * @return
     */
    public Vector3Di add(Vector3Di vector){
        this.x += vector.x;
        this.y += vector.y;
        return this;
    }

    /**
     * Resta el vector especificat al actual
     * @param vector3Di
     * @return
     */
    public Vector3Di substract(Vector3Di vector3Di){
        this.x -= vector3Di.getX();
        this.y -= vector3Di.getY();
        this.z -= vector3Di.getZ();
        return this;
    }

    /**
     * Retorna una copia del vector actual
     * @return
     */
    public Vector3Di copy(){
        return new Vector3Di(x, y, z);
    }

    /**
     * Sobrescriu el metode equals i fa una comprobació correcta del vector
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vector3Di){
            Vector3Di vector = (Vector3Di) obj;
            return vector.getX() == getX()
                    && vector.getY() == getY()
                    && vector.getZ() == getZ();
        }
        return (this == obj);
    }

}
