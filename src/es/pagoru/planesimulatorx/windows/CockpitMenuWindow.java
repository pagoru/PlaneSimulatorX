package es.pagoru.planesimulatorx.windows;

import es.pagoru.planesimulatorx.Window;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Pablo on 01/11/2016.
 */
public class CockpitMenuWindow extends MenuWindow {
    
    public enum ControlsPosition {
        NORMAL(1),
        RIGHT_45(2),
        RIGHT_90(3),
        LEFT_45(4),
        LEFT_90(5);
        
        int id;
        ControlsPosition(int id){
            this.id = id;
        }
        public int getId(){
            return id;
        }
        public String getFileName(){
            return "cockpit_control_" + getId();
        }
        
    }
    
    private static final int LINE_SIZE = 240;

    private static final int WIDTH = 60;
    private static final int HEIGHT = 19;


    //TODO Eliminar, usar objeto Plane para ello con transformación a las coords del landscape
    public int x = 120;
    public int y = 60;
    
    private ControlsPosition controlsPosition;
    
    public CockpitMenuWindow() {
        super("Cockpit");

        controlsPosition = ControlsPosition.NORMAL;
    }
    
    public void moveControlsPosition(ControlsPosition controlsPosition){
        this.controlsPosition = controlsPosition;
    }
    
    public String[] addImageOnTop(String[] originalImage, String[] topImage, String character){
        for (int r = 0; r < originalImage.length; r++) {
            if(!topImage[r].equalsIgnoreCase(character)){
                originalImage[r] = topImage[r];
            }
        }
        return originalImage;
    }
    
    @Override
    public void draw() {
        String raw = rawWindow;

        String[] landscape = getLandscape(Window.getWindowString("plane/landscape", "UTF-8").split(""), x, y);
            
        String[] rawImage = Window.getWindowString("plane/cockpit", "UTF-8").split("");
        rawImage = addImageOnTop(landscape, rawImage, " ");
        
        //static
        String[] rawImage2 = Window.getWindowString("plane/" + controlsPosition.getFileName(), "UTF-8").split("");
        rawImage = addImageOnTop(rawImage, rawImage2, ":");
        
        //cockpit
        Pattern p = Pattern.compile("&");
        Matcher m = p.matcher(raw);
        int count = 0;
        while (m.find()){
            raw = raw.replaceFirst("&", rawImage[count]);
            count++;
        }
        
        raw = raw.replaceAll("\\[([A-z])\\]", "[*]");
        Window.getInstance().loadWindow(raw);
    }
    
    private String[] getLandscape(String[] landscape, int x, int y){
        String[] image = new String[60 * 19];

        int initX = x;
        int initY = y;
        
        int finalX = WIDTH + x;
        int finalY = HEIGHT + y;
        
        int realWidth = (finalX - initX) - ((finalX > LINE_SIZE) ? (finalX - LINE_SIZE) : 0);

        getSplittedLandscape(landscape, image, initX, initY, finalX, finalY, realWidth, 0);
        
        int oldRealWidth = realWidth;
        realWidth = WIDTH - realWidth;
        
        if(realWidth > 0){
            initX = 0;
            finalX = realWidth;
            getSplittedLandscape(landscape, image, initX, initY, finalX, finalY, realWidth, oldRealWidth);
        }
        
        return image;
    }
    
    private String[] getSplittedLandscape(String[] landscape, String[] image, int initX, int initY, int finalX, int finalY, int realWidth, int oldWidth){
        
        int imageX = 0, imageY = 0;
        int landscapeX, landscapeY = 0;
        
        for (int lsXY = 0; lsXY < landscape.length; lsXY++) {
            String currentLandscape = landscape[lsXY];

            landscapeX = lsXY % LINE_SIZE;

            if(initX <= landscapeX && finalX > landscapeX
                    && initY < landscapeY && finalY >= landscapeY){

                image[oldWidth + imageX + (imageY * WIDTH)] = currentLandscape;

                imageX++;
                if(imageX % realWidth == 0){
                    imageX = 0;
                    imageY++;
                }
            }

            if(landscapeX == 0){
                landscapeY++;
            }

        }
        return image;
    }
    
}
