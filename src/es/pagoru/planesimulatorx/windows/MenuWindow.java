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

    /**
     * Retorna en una llista les coincidencies de un regex en un text pasat com a variable.
     * @param regex
     * @param text
     * @return
     */
    private static List<String> getMatches(String regex, String text){
        List<String> matches = new ArrayList<>();
        Matcher m = Pattern.compile(regex).matcher(text);
        while (m.find()){
            matches.add(m.group());
        }
        matches.sort((o1, o2) -> o1.compareTo(o2));
        return matches;
    }

    /**
     * Nom del menu.
     */
    protected String name;

    /**
     * Número de la selecció actual.
     */
    protected int currentSelection;

    /**
     * Finestra en format text pla.
     */
    private String rawWindow;

    /**
     * Llistat de les seleccions que conte aquesta finestra.
     */
    protected List<String> selections;

    public MenuWindow(String name){
        this.name = name;
        currentSelection = 0;
        rawWindow = Window.getInstance().getWindowString(getName(), "UTF-8");
        selections = getMatches("\\[([a-z])\\]", rawWindow);
    }

    /**
     * Retorna el nom de la finestra.
     * @return
     */
    public String getName(){
        return name;
    }

    /**
     * Retorna el nom de la selecció actual.
     * @return
     */
    public String getCurrentSelection(){
        return selections.get(currentSelection);
    }

    /**
     * Mou la selecció actual a la següent o a la anterior
     * en funció del parametre que se li introdueix.
     * @param up
     */
    public void moveSelection(boolean up){
        currentSelection = up ? (currentSelection == 0 ? (selections.size()-1) : currentSelection - 1)
                : (currentSelection == (selections.size()-1) ? 0 : currentSelection + 1);
        draw();
    }

    /**
     * Retorna la finestra en format pla de text.
     * @return
     */
    public String getRawWindow(){
        return this.rawWindow;
    }

    /**
     * Imprimeix la pantalla actual substituint alguns parametres de finestres habituals.
     */
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

    /**
     * Implementació del metode buit que es pot utilitzar en implementacions d'aquesta clase.
     */
    public void openCurrentSelection(){}

}
