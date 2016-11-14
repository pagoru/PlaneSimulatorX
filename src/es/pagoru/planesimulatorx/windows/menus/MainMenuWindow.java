package es.pagoru.planesimulatorx.windows.menus;

import es.pagoru.planesimulatorx.Window;
import es.pagoru.planesimulatorx.windows.MenuWindow;
import es.pagoru.planesimulatorx.windows.MenuWindows;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Pablo on 29/10/2016.
 */
public class MainMenuWindow extends MenuWindow {

    public MainMenuWindow() {
        super("MainMenu");
    }

    /**
     * Obre la selecció del menu actual.
     * a: Obre el menu de creació d'avions.
     * b: Obre el menu de gestió d'avions.
     * c: Obre el menu de manteniment de l'espai aeri.
     * d: Obre el menu d'informació de l'espai aeri.
     * e: Tanca el joc.
     */
    @Override
    public void openCurrentSelection() {
        switch (getCurrentSelection()){
            case "[a]":
                MenuWindows.openMenu("CreatePlane");
                break;
            case "[b]":
                MenuWindows.openMenu("Cockpit");
                break;
            case "[c]":
                MenuWindows.openMenu("Maintenance");
                break;
            case "[d]":
                MenuWindows.openMenu("Info");
                break;
            case "[e]":
                Window.getInstance().close();
                break;
        }
    }

    /**
     * Dibuixa per pantalla el menú principal.
     * Canvia la imatge de la dreta en funció de la selecció actual.
     */
    @Override
    public void draw() {
        String raw = getRawWindow();
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
}
