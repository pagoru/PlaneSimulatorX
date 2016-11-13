package es.pagoru.planesimulatorx.windows;

import es.pagoru.planesimulatorx.Window;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Pablo on 30/10/2016.
 */
public class MenuWindow {

    private static List<String> getMatches(String regex, String text){
        List<String> matches = new ArrayList<>();
        Matcher m = Pattern.compile(regex).matcher(text);
        while (m.find()){
            matches.add(m.group());
        }
        matches.sort((o1, o2) -> o1.compareTo(o2));
        return matches;
    }

    protected String name;
    protected int currentSelection;

    private String rawWindow;
    protected List<String> selections;

    public MenuWindow(String name){
        this.name = name;
        currentSelection = 0;
        rawWindow = Window.getInstance().getWindowString(getName(), "UTF-8");
        selections = getMatches("\\[([a-z])\\]", rawWindow);
    }

    public String getName(){
        return name;
    }

    public String getCurrentSelection(){
        return selections.get(currentSelection);
    }

    public void moveSelection(boolean up){
        currentSelection = up ? (currentSelection == 0 ? (selections.size()-1) : currentSelection - 1)
                : (currentSelection == (selections.size()-1) ? 0 : currentSelection + 1);
        draw();
    }

    public String getRawWindow(){
        return this.rawWindow;
    }

    public void draw(){
        Window.getInstance().loadWindow(
                rawWindow
                .replace(selections.get(currentSelection), "[*]")
                .replaceAll("\\[([a-z])\\]", "[ ]")
                .replaceAll("@([a-z])\\{(.*?)\\}", "______________________________________")
                .replaceAll("#([a-z])\\{(.*?)\\}", "                              ")
                .replaceAll("(&)", " ")
        );
    }
    
    public void openCurrentSelection(){}

}
