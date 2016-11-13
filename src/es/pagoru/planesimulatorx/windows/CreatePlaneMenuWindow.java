package es.pagoru.planesimulatorx.windows;

import es.pagoru.planesimulatorx.Window;
import es.pagoru.planesimulatorx.input.KeyListenerEvent;
import es.pagoru.planesimulatorx.utils.Vector3Di;
import es.pagoru.planesimulatorx.windows.cockpit.Plane;

import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * Created by Pablo on 30/10/2016.
 */
public class CreatePlaneMenuWindow extends MenuWindow {
    
    private HashMap<String, String> optionsText = new HashMap<>();
    
    private int emptyOptions = optionsText.size();
    
    public CreatePlaneMenuWindow() {
        super("CreatePlane");
        clearOptionsText();
    }
    
    private void clearOptionsText(){
        optionsText.clear();
        String[] options = {"[d]", "[e]", "[f]", "[g]", "[h]", "[i]", "[j]", "[k]", "[l]"};
        for (String opt :
                options) {
            optionsText.put(opt, "______________________________________");
        }
        emptyOptions = optionsText.size();
    }
    
    public void onKeyEvent(KeyListenerEvent keyListenerEvent){
        switch (getCurrentSelection()){
            case "[a]":
            case "[b]":
            case "[c]":
                return;
            case "[h]":
            case "[i]":
            case "[j]":
            case "[k]":
            case "[l]":
                if(keyListenerEvent.getKeyCode() >= 'A'
                        && keyListenerEvent.getKeyCode() <= 'Z'
                        || keyListenerEvent.getKeyCode() == ' '){
                    return;
                }
                break;
        }

        String currentSel = optionsText.get(getCurrentSelection());

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
        draw();
    }
    
    private int getPureIntegerFromOption(String option){
        String fp = optionsText.get(option).replace("_", "");
        if(fp.length() == 0){
            return 0;
        }
        return Integer.parseInt(fp.substring(0, (fp.length() > 6 ? 6 : fp.length())));
    }
    
    @Override
    public void openCurrentSelection() {
        switch (getCurrentSelection()) {
            case "[a]":
                optionsText.keySet().stream().forEach(opt -> {
                    if(optionsText.get(opt).replaceAll("_", "").length() == 0){
                        emptyOptions--;
                    }
                });
                if(emptyOptions != 0){
                    ((CockpitMenuWindow)MenuWindows.getMenuWindow("Cockpit")).addPlane(
                            new Plane(
                                    optionsText.get("[d]").replaceAll("_", ""),
                                    optionsText.get("[e]").replaceAll("_", ""),
                                    optionsText.get("[f]").replaceAll("_", ""),
                                    optionsText.get("[g]").replaceAll("_", ""),
                                    getPureIntegerFromOption("[h]"),
                                    getPureIntegerFromOption("[i]"),
                                    new Vector3Di(
                                            getPureIntegerFromOption("[j]"),
                                            getPureIntegerFromOption("[k]"),
                                            getPureIntegerFromOption("[l]")
                                    )
                            )
                    );
                }
                clearOptionsText();
                draw();
                break;
            case "[b]":
                clearOptionsText();
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
    
}