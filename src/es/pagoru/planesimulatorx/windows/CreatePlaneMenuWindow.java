package es.pagoru.planesimulatorx.windows;

import es.pagoru.planesimulatorx.Window;
import es.pagoru.planesimulatorx.input.KeyListenerEvent;

import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * Created by Pablo on 30/10/2016.
 */
public class CreatePlaneMenuWindow extends MenuWindow {

    private Thread createPlaneMenuWindowThread;
    
    private HashMap<String, String> optionsText = new HashMap<>();
    
    public CreatePlaneMenuWindow() {
        super("CreatePlane");
    }
    
    public void onKeyEvent(KeyListenerEvent keyListenerEvent){
        switch (getCurrentSelection()){
            case "[a]":
            case "[b]":
            case "[c]":
                break;
            default:
            
                String currentSel = optionsText.get(getCurrentSelection()) != null 
                        ? optionsText.get(getCurrentSelection()) 
                        : "______________________________________";
                
                if(keyListenerEvent.getKeyCode() == KeyEvent.VK_DELETE){
                    String newCurrentSel = currentSel.replaceAll("_", "");
                    if(newCurrentSel.length() > 0){
                        newCurrentSel = newCurrentSel.substring(0, newCurrentSel.length() - 1);
                        for (int i = 0; i <= (currentSel.length() - currentSel.replace("_", "").length()); i++) {
                            newCurrentSel += "_";
                        }
                        currentSel = newCurrentSel;
                    }
                } else {
                    currentSel = currentSel.replaceFirst("_", (char)keyListenerEvent.getKeyCode() + "");
                }
                
                optionsText.put(getCurrentSelection(), currentSel);
//                System.out.println(optionsText.get(getCurrentSelection()) + "<->" + currentSel);
//
//                newRawWindow = getRawWindow().replaceAll("@(" + getCurrentSelection() + ")\\{(.*?)\\}", optionsText.get(getCurrentSelection()) );
                
                draw();
                break;
        }
    }
    
    @Override
    public void openCurrentSelection() {
        switch (getCurrentSelection()) {
            case "[a]":
                new Plane();
                draw();
                break;
            case "[b]":
                optionsText.clear();
                draw();
                break;
            case "[c]":
                MenuWindows.closeLastMenu();
                break;
        }
    }

    @Override
    public void draw() {
        String newRawWindow = getRawWindow();
        for (String opt : optionsText.keySet()) {
            newRawWindow = newRawWindow.replaceAll("@(" + opt + ")\\{(.*?)\\}", optionsText.get(opt));
        }
        
        Window.getInstance().loadWindow(
                newRawWindow
                .replace(selections.get(currentSelection), "[*]")
                .replaceAll("\\[([a-z])\\]", "[ ]")
                .replaceAll("@([a-z])\\{(.*?)\\}", "______________________________________")
                .replaceAll("#([a-z])\\{(.*?)\\}", "                              ")
                .replaceAll("(&)", " ")
        );
    }
    
    public void setCreatePlaneMenuWindowThread(Thread thread){
        this.createPlaneMenuWindowThread = thread;
    }
    
}