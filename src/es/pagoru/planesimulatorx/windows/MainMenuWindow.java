package es.pagoru.planesimulatorx.windows;

import es.pagoru.planesimulatorx.Window;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Pablo on 29/10/2016.
 */
public class MainMenuWindow extends MenuWindow{

    public MainMenuWindow() {
        super("MainMenu");
    }

    @Override
    public void draw() {
        String raw = rawWindow;
        String currentOption = getCurrentSelection().replace("[", "").replace("]", "");
        String[] rawImage = Window.getWindowString("mainmenu/" + currentOption, "UTF-8").split("");

        Pattern p = Pattern.compile("&");
        Matcher m = p.matcher(raw);
        int count = 0;
        while (m.find()){
            raw = raw.replaceFirst("&", rawImage[count]);
            count++;
        }
        raw = raw.replace(selections.get(currentSelection), "[*]")
                .replaceAll("\\[([A-z])\\]", "[ ]")
                .replaceAll("@([A-z])\\{(.*?)\\}", "______________________________________")
                .replaceAll("#([A-z])\\{(.*?)\\}", "                              ");
        Window.getInstance().loadWindow(raw);
    }

    @Override
    public void openCurrentSelection() {
        switch (getCurrentSelection()){
            case "[a]":
                MenuWindows.openMenu("CreatePlane");
                break;
            case "[e]":
                Window.getInstance().close();
                break;
        }
    }
}
