package es.pagoru.planesimulatorx.utils;

/**
 * Created by pablo on 20/9/16.
 */
public class Vector2Di {

    private int x;
    private int y;

    public Vector2Di(int x, int y){
        this.x = x;
        this.y = y;
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
     * Suma el vector especificat al actual
     * @param vector
     * @return
     */
    public Vector2Di add(Vector2Di vector){
        this.x += vector.x;
        this.y += vector.y;
        return this;
    }

    /**
     * Resta el vector especificat al actual
     * @param vector2Di
     * @return
     */
    public Vector2Di substract(Vector2Di vector2Di){
        this.x -= vector2Di.getX();
        this.y -= vector2Di.getY();
        return this;
    }

    /**
     * Retorna una copia del vector actual
     * @return
     */
    public Vector2Di copy(){
        return new Vector2Di(x, y);
    }

    /**
     * Sobrescriu el metode equals i fa una comprobació correcta del vector
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vector2Di){
            Vector2Di vector = (Vector2Di) obj;
            return vector.getX() == getX() && vector.getY() == getY();
        }
        return (this == obj);
    }

}
