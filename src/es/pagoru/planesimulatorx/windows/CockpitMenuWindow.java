package es.pagoru.planesimulatorx.windows;

import es.pagoru.planesimulatorx.Window;
import es.pagoru.planesimulatorx.plane.CockpitMenuWindowThread;
import es.pagoru.planesimulatorx.plane.Plane;
import es.pagoru.planesimulatorx.utils.Vector3Di;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Pablo on 01/11/2016.
 */
public class CockpitMenuWindow extends MenuWindow {
    
    private static final int LINE_SIZE = 240;

    private static final int WIDTH = 60;
    private static final int HEIGHT = 19;

    private Thread cockpitMenuWindowThread;

    private int currentPlane;
    private List<Plane> planes;

    public CockpitMenuWindow() {
        super("Cockpit");
        planes = new ArrayList<>();
        currentPlane = 0;//MODIFY
    }

    public CockpitMenuWindowThread getCockpitMenuWindowThread() {
        return (CockpitMenuWindowThread) cockpitMenuWindowThread;
    }

    public void setCockpitMenuWindowThread(Thread cockpitMenuWindowThread) {
        this.cockpitMenuWindowThread = cockpitMenuWindowThread;
    }

    public Plane getPlane() {
        return (planes.size() == 0) ? null : planes.get(currentPlane);
    }

    public void addPlane(Plane plane){
        planes.add(plane);
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

        int x = (int) (((float)LINE_SIZE / 360.0f) * getPlane().getYaw());
//        int y = (int) (((float)LINE_SIZE / 360.0f) * getPlane().getYaw());

        String[] landscape = getLandscape(Window.getWindowString("plane/landscape", "UTF-8").split(""), x, 60);
            
        String[] rawImage = Window.getWindowString("plane/cockpit", "UTF-8").split("");
        rawImage = addImageOnTop(landscape, rawImage, " ");
        
        //static
        String[] rawImage2 = Window.getWindowString("plane/" + getPlane().getFlightControlPositionsLeftRight().getFileName(),
                "UTF-8").split("");

        //TODO UP & DOWN
        String[] rawImage2_B = new String[rawImage2.length];
        int widthFlightControl = WIDTH * getPlane().getFlightControlPositionsUpDown().ordinal();
        for (int i = 0; i < rawImage2_B.length; i++) {
            if(i < widthFlightControl){
                rawImage2_B[i] = ":";
                continue;
            }
            rawImage2_B[i] = rawImage2[i - widthFlightControl];
        }
        rawImage2 = rawImage2_B;
        //<-----------------------

        rawImage = addImageOnTop(rawImage, rawImage2, ":");

        Vector3Di pos = getPlane().getPosition();
        setTextInPosition(rawImage, "X=" + pos.getX(), 0);
        setTextInPosition(rawImage, "Y=" + pos.getY(), WIDTH);
        setTextInPosition(rawImage, "Z=" + pos.getZ(), WIDTH * 2);

        setTextInPosition(rawImage, getPlane().getThrottle() + "mph", WIDTH * 12 + 27);

        String onEngines = "#";
        for (int i = 0; i < getPlane().getEnginesOn(); i++) {
            onEngines += "1";
        }
        for (int i = 0; i < 4 - getPlane().getEnginesOn(); i++) {
            onEngines += "0";
        }
        setTextInPosition(rawImage, onEngines + "#", WIDTH * 18 + 27);

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

    private String[] setTextInPosition(String[] raw, String text, int initialPosition){
        String[] textS = text.split("");
        for (int i = 0; i < textS.length; i++) {
            raw[initialPosition + i] = textS[i];
        }
        return raw;
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
    
    private String[] getSplittedLandscape(String[] landscape, String[] image, int initX, int initY,
                                          int finalX, int finalY, int realWidth, int oldWidth){
        
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
