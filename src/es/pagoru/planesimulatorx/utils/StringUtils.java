package es.pagoru.planesimulatorx.utils;

/**
 * Created by Pablo on 13/11/2016.
 */
public class StringUtils {

    public static String getAmountOfString(String text, int quantity){
        String nText = "";
        for (int i = 0; i < quantity; i++){
            nText += text;
        }
        return nText;
    }
    
}
