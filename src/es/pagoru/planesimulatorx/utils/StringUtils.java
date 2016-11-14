package es.pagoru.planesimulatorx.utils;

/**
 * Created by Pablo on 13/11/2016.
 */
public class StringUtils {

    /**
     * Retorna un string amb un determinat número de caracters,
     * en funció de la variable de text que es pasa aquesta es repetirá per
     * la quantitat de vegades que es repeteix en la variable númerica que
     * se li dona.
     * @param text
     * @param quantity
     * @return
     */
    public static String getAmountOfString(String text, int quantity){
        String nText = "";
        for (int i = 0; i < quantity; i++){
            nText += text;
        }
        return nText;
    }
    
}
