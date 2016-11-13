package es.pagoru.planesimulatorx.windows;

import es.pagoru.planesimulatorx.Window;
import es.pagoru.planesimulatorx.utils.StringUtils;
import es.pagoru.planesimulatorx.utils.Vector3Di;
import es.pagoru.planesimulatorx.windows.cockpit.Plane;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Pablo on 13/11/2016.
 */
public class InfoMenuWindow extends MenuWindow {
    
    public InfoMenuWindow() {
        super("Info");
    }

    private String getFitData(String info){
        String nInfo = info.substring(0, (info.length() > 12) ? 12 : info.length());
        nInfo += StringUtils.getAmountOfString(" ", 12 - nInfo.length());
        return nInfo;
    }
    
    private String getRawDataFrom(String raw, String regexFirstPart, String data){
        return raw.replaceAll("#" + regexFirstPart + "\\{(.*?)\\}", getFitData(data));
    }

    @Override
    public void openCurrentSelection() {
        switch (getCurrentSelection()) {
            case "[a]":
                MenuWindows.closeLastMenu();
                break;
        }
    }
    
    @Override
    public void draw(){
        String rawWindow = getRawWindow();
        List<Plane> planeList = ((CockpitMenuWindow)MenuWindows.getMenuWindow("Cockpit")).getPlanes();
        
        int currentInfo = 0;
        String[] orderInfoSymbol = {"@", "\\$", "&", "%", "Ç", "Ñ"};
        String[] planeListInfo = new String[6];
        
        for (int i = 0; i < (planeList.size() > 4 ? 4 : planeList.size()); i++) {
            Plane plane = planeList.get(i);
            if(plane != null){
                rawWindow = getRawDataFrom(rawWindow, "a" + i, plane.getModel());
                rawWindow = getRawDataFrom(rawWindow, "b" + i, plane.getBrand());
                rawWindow = getRawDataFrom(rawWindow, "c" + i, plane.getPlate());

                Vector3Di position = plane.getPosition();
                rawWindow = getRawDataFrom(rawWindow, "d" + i, position.getX() + "");
                rawWindow = getRawDataFrom(rawWindow, "e" + i, position.getY() + "");
                rawWindow = getRawDataFrom(rawWindow, "f" + i, position.getZ() + "");

                rawWindow = getRawDataFrom(rawWindow, "g" + i, plane.getYaw() + "");
                rawWindow = getRawDataFrom(rawWindow, "h" + i, plane.getPitch() + "");

                rawWindow = getRawDataFrom(rawWindow, "i" + i, plane.getThrottle() + "mph");
                rawWindow = getRawDataFrom(rawWindow, "j" + i, plane.isUndercarriage() ? "Baixat" : "Pujat");
                rawWindow = getRawDataFrom(rawWindow, "k" + i, plane.isEngine() ? "Encés" : "Apagat");
                rawWindow = getRawDataFrom(rawWindow, "l" + i, plane.isBreaks() ? "Frenat" : "No frenat");
                
                if(currentInfo < planeListInfo.length - 1){
                    if(position.getY() > 0){
                        if(!plane.isEngine()){
                            planeListInfo[currentInfo] = "[" + plane.getPlate() + "] Motors apagats en ple vol";
                            currentInfo++;
                        }
                        if(plane.isUndercarriage()){
                            planeListInfo[currentInfo] = "[" + plane.getPlate() + "] Tren d'aterratge baixat en ple vol";
                            currentInfo++;
                        }
                        if(plane.getThrottle() < 400){
                            planeListInfo[currentInfo] = "[" + plane.getPlate() + "] Poca velocitat";
                            currentInfo++;
                        }
                        int minDis = 200;
                        String collisionPlanes = planeList.stream().filter(p ->
                                !plane.getPlate().equalsIgnoreCase(p.getPlate())
                                        && position.getX() + minDis > p.getPosition().getX()
                                        && position.getX() - minDis < p.getPosition().getX()
                                        && position.getY() + minDis > p.getPosition().getY()
                                        && position.getY() - minDis < p.getPosition().getY()
                                        && position.getZ() + minDis > p.getPosition().getZ()
                                        && position.getZ() - minDis < p.getPosition().getZ()
                        ).map(p -> p.getPlate()).collect(Collectors.joining(", "));

                        if(collisionPlanes.length() != 0){
                            planeListInfo[currentInfo] = "[" + plane.getPlate() + "] Colisions amb " + collisionPlanes;
                            currentInfo++;
                        }
                    }
                }              
            }
        }
        for (int j = 0; j < 6; j++) {
            if(planeListInfo[j] == null){
                break;
            }
            String currentInfoText = planeListInfo[j].substring(0, planeListInfo[j].length() > 46 ? 46 : planeListInfo[j].length());
            currentInfoText += StringUtils.getAmountOfString(" ", 46 - currentInfoText.length());
            rawWindow = rawWindow.replaceAll(StringUtils.getAmountOfString(orderInfoSymbol[j], 46), currentInfoText);
        }
        
        Window.getInstance().loadWindow(
                rawWindow
                        .replace(selections.get(currentSelection), "[*]")
                        .replaceAll("\\[([a-z])\\]", "[ ]")
                        .replaceAll("#[a-z][0-9]\\{(.*?)\\}", "            ")
                        .replaceAll("(@)", " ")
                        .replaceAll("(\\$)", " ")
                        .replaceAll("(&)", " ")
                        .replaceAll("(%)", " ")
                        .replaceAll("(Ñ)", " ")
                        .replaceAll("(Ç)", " ")
        );
    }
}
