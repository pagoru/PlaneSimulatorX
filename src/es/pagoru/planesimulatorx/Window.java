package es.pagoru.planesimulatorx;

import es.pagoru.planesimulatorx.input.KeyListenerEvent;
import es.pagoru.planesimulatorx.input.KeyboardEventHandler;
import es.pagoru.planesimulatorx.windows.MenuWindows;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * Created by pablo on 26/10/16.
 */
public class Window {

    /**
     * Mida de la finestra
     */
    public static final int WIDTH = 95;

    /**
     * Altura de la finestra
     */
    public static final int HEIGHT = 24;

    /**
     * Retorna la instancia de la finestra principal (Singleton)
     * @return
     */
    public static Window getInstance(){
        return PlaneSimulatorX.window;
    }

    /**
     * Retorna el String de la finestra en funció del nom que es pasa al metode.
     * També se li pasa la variable del charset per saber com codificar l'arxiu.
     * @param path
     * @param charset
     * @return
     */
    public static String getWindowString(String path, String charset) {
        try {
            return Files.readAllLines(Paths.get("src/assets/menus/" + path + ".txt"),
                    Charset.forName(charset)).stream().collect(Collectors.joining());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Textarea que es mostra en la finestra principal.
     */
    private JTextArea textArea;

    /**
     * Frame de la finestra.
     */
    private JFrame frame;

    /**
     * Constructor de la finestra principal. S'ocupa de crear la finestra de forma inicial.
     * @throws IOException
     */
    public Window() throws IOException {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        MenuWindows.load();

        frame = new JFrame("Planes Simulator X");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(43, 43, 43));
        frame.getContentPane().add(mainPanel);

        textArea = new JTextArea();
        loadWindow("WelcomeWindow", "UTF-8");
        textArea.setEditable(false);
        textArea.setRows(HEIGHT);
        textArea.setColumns(WIDTH);
        textArea.setLineWrap(true);
        textArea.setBackground(new Color(0, 0, 0));
        textArea.setForeground(new Color(0, 228, 0));
        try{
            textArea.setFont(loadFont("Consolas").deriveFont(16f));
        } catch (Exception e){
            textArea.setFont(loadFont("monaco").deriveFont(16f));
        }
        textArea.addKeyListener(new KeyListenerEvent());

        mainPanel.add(textArea);

        frame.pack();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle frameSize = frame.getBounds();
        int x = (int)(screenSize.getWidth()/2 - frameSize.getWidth()/2);
        int y = (int)(screenSize.getHeight()/2 - frameSize.getHeight()/2);
        frame.setLocation(x, y);

        frame.setVisible(true);

        KeyboardEventHandler.load();

    }

    /**
     * Carrega, passat un nom d'arxiu i una codificació de caracters d'aquesta, aquest arxiu a la finestra principal.
     * @param fileName
     * @param charset
     * @throws IOException
     */
    public void loadWindow(String fileName, String charset) throws IOException {
        loadWindow(getWindowString(fileName, charset));
    }

    /**
     * Carrega, passat un string de text, aquest text en la finestra principal.
     * @param text
     */
    public void loadWindow(String text){

        if(text.length() >= (WIDTH * HEIGHT)){
            text = text.substring(0, (WIDTH * HEIGHT));
        }
        textArea.setText(text);
    }

    /**
     * Obre la finestra de goodbye i passats 3 segons tanca l'aplicació.
     */
    public void close(){
        MenuWindows.openMenu("GoodbyeWindow");

        new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    frame.dispose();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * Carrega, passant un nom de font, una font del sistema. En cas de no trobar-ne cap, retorna null.
     * @param name
     * @return
     */
    private Font loadFont(String name){
        for(Font f : GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts()){
            if(f.getName().equalsIgnoreCase(name)){
                return f;
            }
        }
        return null;
    }

}
