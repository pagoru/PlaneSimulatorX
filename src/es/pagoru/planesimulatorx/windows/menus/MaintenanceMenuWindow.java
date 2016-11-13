package es.pagoru.planesimulatorx.windows.menus;

import es.pagoru.planesimulatorx.Window;
import es.pagoru.planesimulatorx.utils.StringUtils;
import es.pagoru.planesimulatorx.windows.MenuWindow;
import es.pagoru.planesimulatorx.windows.MenuWindows;
import es.pagoru.planesimulatorx.windows.menus.cockpit.Plane;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Pablo on 13/11/2016.
 */
public class MaintenanceMenuWindow extends MenuWindow{
    
    private static final String[] CHAR_LIST = {"b", "c", "d", "e", "f", "g", "h", "i", "j"};
    
    public MaintenanceMenuWindow() {
        super("Maintenance");
    }
    
    @Override
    public void openCurrentSelection() {
        switch (getCurrentSelection()) {
            case "[a]":
                MenuWindows.closeLastMenu();
                break;
            default:
                for (int i = 0; i < CHAR_LIST.length; i++) {
                    if(getCurrentSelection().contains(CHAR_LIST[i])){
                        ((CockpitMenuWindow) MenuWindows.getMenuWindow("Cockpit")).removePlane(i);
                        break;
                    }
                }
                draw();
                break;
        }
    }

    @Override
    public void draw() {
        String raw = getRawWindow();
        
        List<Plane> planeList = ((CockpitMenuWindow) MenuWindows.getMenuWindow("Cockpit"))
                .getPlanes()
                .stream()
                .filter(p -> 
                        p.getPosition().getY() == 0
                        || p.getPosition().getX() > 1000
                        || p.getPosition().getX() < 0
                        || p.getPosition().getZ() > 1000 
                        || p.getPosition().getZ() < 0
                )
                .collect(Collectors.toList());
        Plane[] planeArray = new Plane[9];
        
        String[][] planeArrayInfo = new String[9][2];
        
        for (int i = 0; i < planeArray.length; i++) {
            if(i > planeList.size() - 1){
                break;
            }
            planeArray[i] = planeList.get(i);
            if(planeArray[i] != null){
                planeArrayInfo[i][0] = planeArray[i].getOwner() + " [" + planeArray[i].getPlate() + "] - " 
                        + planeArray[i].getBrand() + " " + planeArray[i].getModel();
                planeArrayInfo[i][1] = (planeArray[i].getPosition().getX() > 1000
                        || planeArray[i].getPosition().getX() < 0
                        || planeArray[i].getPosition().getZ() > 1000
                        || planeArray[i].getPosition().getZ() < 0) ? "Fora del radar" : "Aparcat";
            }
        }
        
        for (int i = 0; i < planeArray.length; i++) {
            if(planeArrayInfo[i][0] != null){
                String text0 = planeArrayInfo[i][0];
                String text1 = planeArrayInfo[i][1];

                text0 += StringUtils.getAmountOfString(" ", 44 - text0.length());
                text1 += StringUtils.getAmountOfString(" ", 19 - text1.length());
                
                raw = raw.replaceAll("#" + CHAR_LIST[i] + "0\\{(.*?)\\}", text0);
                raw = raw.replaceAll("#" + CHAR_LIST[i] + "1\\{(.*?)\\}", text1);
            }
        }
        
        Window.getInstance().loadWindow(
                raw
                        .replace(selections.get(currentSelection), "[*]")
                        .replaceAll("\\[([a-z])\\]", "[ ]")
                        .replaceAll("#[a-z]0\\{(.*?)\\}", "                                            ")
                        .replaceAll("#[a-z]1\\{(.*?)\\}", "                   ")
        );
    }
}
