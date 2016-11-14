package es.pagoru.planesimulatorx.windows.menus;

import es.pagoru.planesimulatorx.Window;
import es.pagoru.planesimulatorx.utils.StringUtils;
import es.pagoru.planesimulatorx.windows.MenuWindow;
import es.pagoru.planesimulatorx.windows.MenuWindows;
import es.pagoru.planesimulatorx.windows.menus.cockpit.CockpitMenuWindowThread;
import es.pagoru.planesimulatorx.windows.menus.cockpit.Plane;
import es.pagoru.planesimulatorx.utils.Vector3Di;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Pablo on 01/11/2016.
 */
public class CockpitMenuWindow extends MenuWindow {

    /**
     * Amplada de cada linea en la pantalla
     */
    private static final int LINE_SIZE = 240;

    /**
     * Amplada de la pantalla del cockpit
     */
    private static final int WIDTH = 60;
    /**
     * Altura de la pantalla del cockpit
     */
    private static final int HEIGHT = 19;

    /**
     * Thread que controla l'avió i les funcions varies del menu
     */
    private Thread cockpitMenuWindowThread;

    /**
     * Selecció actual del planeList
     */
    private int currentPlane;

    /**
     * Llistat dels avions disponibles
     */
    private List<Plane> planeList;

    public CockpitMenuWindow() {
        super("Cockpit");
        planeList = new ArrayList<>();
        currentPlane = 0;
    }

    /**
     * Sobreescriptura. Permet seleccionar les opcions:
     * a: Tancar el menú
     * b: Canviar al anterior avió
     * c: Canviar al següent avió
     */
    @Override
    public void openCurrentSelection() {
        switch (getCurrentSelection()) {
            case "[a]":
                MenuWindows.closeLastMenu();
                break;
            case "[b]":
                togglePlane(false);
                break;
            case "[c]":
                togglePlane(true);
                break;
        }
    }

    /**
     * Retorna la llista d'avions disponibles
     *
     * @return
     */
    public List<Plane> getPlaneList() {
        return planeList;
    }

    /**
     * Elimina un avió en funció de l'index
     *
     * @param index
     */
    public void removePlane(int index) {
        planeList.remove(index);
        currentPlane = 0;
    }

    /**
     * Permet asignar el thread del menu
     *
     * @param cockpitMenuWindowThread
     */
    public void setCockpitMenuWindowThread(Thread cockpitMenuWindowThread) {
        this.cockpitMenuWindowThread = cockpitMenuWindowThread;
    }

    /**
     * Retorna l'avió actual
     *
     * @return
     */
    public Plane getPlane() {
        return (planeList.size() == 0) ? null : planeList.get(currentPlane);
    }

    /**
     * Crea un avió basic
     */
    public void createBasicPlane() {
        addPlane(new Plane(
                "A300",
                "AIRBUS",
                "AA3HKK",
                "AMERICAN AIRLINES",
                97,
                8000,
                new Vector3Di(0, 0, 0)
        ));
        this.currentPlane = 0;
    }

    /**
     * Permet afegir un avió
     *
     * @param plane
     */
    public void addPlane(Plane plane) {
        planeList.add(plane);
    }

    /**
     * Permet canviar de selecció d'avió en funció de la variable booleana que s'introdueix.
     * Aquesta aumentará o disminuirá la selecció.
     *
     * @param right
     */
    public void togglePlane(boolean right) {
        if (right) {
            if (planeList.size() - 1 > currentPlane) {
                currentPlane++;
            } else {
                currentPlane = 0;
            }
            return;
        }
        if (0 < currentPlane) {
            currentPlane--;
        } else {
            currentPlane = planeList.size() - 1;
        }
    }

    /**
     * Permet, passat un String[] representatiu del fons (cockpit de l'avió),
     * un String[] de una altre capa y un caracter per fer de transparencia,
     * Sobreescriure al segona imatge per sobre de la primera respectant la
     * transparencia.
     *
     * @param originalImage
     * @param topImage
     * @param character
     * @return
     */
    public String[] addImageOnTop(String[] originalImage, String[] topImage, String character) {
        for (int r = 0; r < originalImage.length; r++) {
            if (!topImage[r].equalsIgnoreCase(character)) {
                originalImage[r] = topImage[r];
            }
        }
        return originalImage;
    }

    /**
     * Sobreescriptura.
     * Dibuixa tota la cabina de l'avió en funció de les variables de l'avió
     * seleccionat.
     */
    @Override
    public void draw() {
        String raw = getRawWindow();

        int x = (int) (((float) LINE_SIZE / 360.0f) * getPlane().getYaw());
        int y = 66 - (getPlane().getPosition().getY() / 50);
        y = (y < 0) ? 0 : y;
        y = (y > 66) ? 66 : y;

        String[] landscape = getLandscape(Window.getWindowString("cockpit/landscape", "UTF-8").split(""), x, y);

        String[] rawImage = Window.getWindowString("cockpit/cockpit", "UTF-8").split("");
        rawImage = addImageOnTop(landscape, rawImage, " ");

        //static
        String[] rawImage2 = Window.getWindowString("cockpit/" + getPlane().getFlightControlPositionsLeftRight().getFileName(),
                "UTF-8").split("");

        //TODO UP & DOWN
        String[] rawImage2_B = new String[rawImage2.length];
        int widthFlightControl = WIDTH * getPlane().getFlightControlPositionsUpDown().ordinal();
        for (int i = 0; i < rawImage2_B.length; i++) {
            if (i < widthFlightControl) {
                rawImage2_B[i] = ":";
                continue;
            }
            rawImage2_B[i] = rawImage2[i - widthFlightControl];
        }
        rawImage2 = rawImage2_B;
        //<-----------------------

        rawImage = addImageOnTop(rawImage, rawImage2, ":");

        String[] rawImage3 = Window.getWindowString("cockpit/" + getPlane().getFlightControlThrottlePosition().getFileName(), "UTF-8").split("");
        rawImage = addImageOnTop(rawImage, rawImage3, ":");

        Vector3Di pos = getPlane().getPosition();
        setTextInPosition(rawImage, "X=" + pos.getX(), 0);
        setTextInPosition(rawImage, "Y=" + pos.getY(), WIDTH);
        setTextInPosition(rawImage, "Z=" + pos.getZ(), WIDTH * 2);

        setTextInPosition(rawImage, ((int) getPlane().getPitch()) + "ºP", WIDTH * 11 + 20);
        setTextInPosition(rawImage, ((int) getPlane().getYaw()) + "ºY", WIDTH * 11 + 36);

        setTextInPosition(rawImage, (getPlane().isUndercarriage() ? "╒╕" : "╘╛"), WIDTH * (getPlane().isUndercarriage() ? 13 : 14) + 33);

        setTextInPosition(rawImage, (getPlane().isBreaks() ? "╒╕" : "╘╛"), WIDTH * (getPlane().isBreaks() ? 13 : 14) + 25);

        setTextInPosition(rawImage, getPlane().getThrottle() + "mph", WIDTH * 11 + 27);

        String onEngines = "";
        for (int i = 0; i < getPlane().getEnginesOn(); i++) {
            onEngines += "╜";
        }
        for (int i = 0; i < 4 - getPlane().getEnginesOn(); i++) {
            onEngines += "╖";
        }
        setTextInPosition(rawImage, onEngines, WIDTH * 18 + 28);

        //cockpit
        Pattern p = Pattern.compile("&");
        Matcher m = p.matcher(raw);
        int count = 0;
        while (m.find()) {
            raw = raw.replaceFirst("&", rawImage[count]);
            count++;
        }

        String planeInfo = getCenterText(
                getPlane().getOwner() + " ["
                        + getPlane().getPlate() + "] - "
                        + getPlane().getBrand() + " "
                        + getPlane().getModel()
        );

        for (int i = 0; i < planeInfo.length(); i++) {
            raw = raw.replaceFirst("@", planeInfo.substring(i, i + 1));
        }

        raw = raw
                .replace(selections.get(currentSelection), "[*]")
                .replaceAll("\\[([a-z])\\]", "[ ]")
                .replaceAll("@", " ");
        Window.getInstance().loadWindow(raw);
    }

    /**
     * Retorna un text centrat en funció d'una mida determinada.
     *
     * @param text
     * @return
     */
    private String getCenterText(String text) {
        int c = (93 - text.length()) / 2;
        return StringUtils.getAmountOfString(" ", c) + text + StringUtils.getAmountOfString(" ", (c % 2 == 0 ? c : c + 1));
    }

    /**
     * Estableix un text en un altre text en funció de la posició.
     * En cas d'afegir mes caracters, aquest el sobreescriu y eliminar els antics caracters.
     *
     * @param raw
     * @param text
     * @param initialPosition
     * @return
     */
    private String[] setTextInPosition(String[] raw, String text, int initialPosition) {
        String[] textS = text.split("");
        for (int i = 0; i < textS.length; i++) {
            raw[initialPosition + i] = textS[i];
        }
        return raw;
    }

    /**
     * Retorna, amb una mida de 60x19, part del landscape en funció de les coordenades x i y;
     *
     * @param landscape
     * @param x
     * @param y
     * @return
     */
    private String[] getLandscape(String[] landscape, int x, int y) {
        String[] image = new String[60 * 19];

        int initX = x;
        int initY = y;

        int finalX = WIDTH + x;
        int finalY = HEIGHT + y;

        int realWidth = (finalX - initX) - ((finalX > LINE_SIZE) ? (finalX - LINE_SIZE) : 0);

        getSplittedLandscape(landscape, image, initX, initY, finalX, finalY, realWidth, 0);

        int oldRealWidth = realWidth;
        realWidth = WIDTH - realWidth;

        if (realWidth > 0) {
            initX = 0;
            finalX = realWidth;
            getSplittedLandscape(landscape, image, initX, initY, finalX, finalY, realWidth, oldRealWidth);
        }

        return image;
    }

    /**
     * Retorna una part del string pasant com a variables el propi string
     * una nova imatge representada com a string, la x i y inicial, la x i y finals,
     * l'actual amplada i la antiga amplada.
     *
     * @param landscape
     * @param image
     * @param initX
     * @param initY
     * @param finalX
     * @param finalY
     * @param realWidth
     * @param oldWidth
     * @return
     */
    private String[] getSplittedLandscape(String[] landscape, String[] image, int initX, int initY,
                                          int finalX, int finalY, int realWidth, int oldWidth) {

        int imageX = 0, imageY = 0;
        int landscapeX, landscapeY = 0;

        for (int lsXY = 0; lsXY < landscape.length; lsXY++) {
            String currentLandscape = landscape[lsXY];

            landscapeX = lsXY % LINE_SIZE;

            if (initX <= landscapeX && finalX > landscapeX
                    && initY < landscapeY && finalY >= landscapeY) {

                image[oldWidth + imageX + (imageY * WIDTH)] = currentLandscape;

                imageX++;
                if (imageX % realWidth == 0) {
                    imageX = 0;
                    imageY++;
                }
            }

            if (landscapeX == 0) {
                landscapeY++;
            }

        }
        return image;
    }
}