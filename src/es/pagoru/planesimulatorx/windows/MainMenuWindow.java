package es.pagoru.planesimulatorx.windows;

import es.pagoru.planesimulatorx.Window;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Pablo on 29/10/2016.
 */
public enum MainMenuWindow {

    INSTANCE;

    private static Window window = Window.getInstance();
    private static String menuName = "MainMenu";

    private int currentOption;
    private boolean isInsideMenu = false;

    public void firstLoad(){
        currentOption = 0;
        window.setFirstLoad(false);
        isInsideMenu = true;
        loadCurrentMenu();
    }

    public boolean isInsideMenu(){
        return isInsideMenu;
    }

    public void moveMenu(boolean up){
        currentOption = up ? (currentOption == 0 ? 3 : currentOption - 1) : (currentOption == 3 ? 0 : currentOption + 1);
        loadCurrentMenu();
    }

    public void openSubMenu(){
        isInsideMenu = false;
        switch (currentOption){
            case 0:
                loadCreatePlaneMenu();
                break;
        }

    }

    private void loadCreatePlaneMenu(){
        try{
            String windowText = Window.getWindowString("CreatePlaneMenu", "UTF-8");

            List<String> options = getMatches("\\[([a-z])\\]", windowText);
            options.sort((o1, o2) -> o1.compareTo(o2));

            System.out.println(options);

            String rawText = windowText.replace(options.get(0), "[*]")
                    .replaceAll("\\[([a-z])\\]", "[ ]")
                    .replaceAll("@([a-z])\\{(.*?)\\}", "______________________________________")
                    .replaceAll("#([a-z])\\{(.*?)\\}", "                              ");

            window.loadWindow(rawText);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private List<String> getMatches(String regex, String text){
        List<String> matches = new ArrayList<>();
        Matcher m = Pattern.compile(regex).matcher(text);
        while (m.find()){
            matches.add(m.group());
        }
        return matches;
    }

    public void loadCurrentMenu(){
        isInsideMenu = true;
        try {
            window.loadWindow(menuName + (currentOption + 1), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
