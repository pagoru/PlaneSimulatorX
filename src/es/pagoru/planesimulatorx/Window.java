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

    public static final int WIDTH = 95;
    public static final int HEIGHT = 24;

    public static Window getInstance(){
        return PlaneSimulatorX.window;
    }

    public static String getWindowString(String path, String charset) {
        try {
            return Files.readAllLines(Paths.get("src/assets/menus/" + path + ".txt"),
                    Charset.forName(charset)).stream().collect(Collectors.joining());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private JTextArea textArea;
    private JFrame frame;

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

    public void loadWindow(String path, String charset) throws IOException {
        loadWindow(getWindowString(path, charset));
    }

    public void loadWindow(String text){

        if(text.length() >= (WIDTH * HEIGHT)){
            text = text.substring(0, (WIDTH * HEIGHT));
        }
        textArea.setText(text);
    }
    
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

    private Font loadFont(String name){
        for(Font f : GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts()){
            if(f.getName().equalsIgnoreCase(name)){
                return f;
            }
        }
        return null;
    }

}
