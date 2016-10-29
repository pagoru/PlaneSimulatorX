package es.pagoru.planesimulatorx;

import es.pagoru.planesimulatorx.input.KeyListenerEvent;
import es.pagoru.planesimulatorx.input.KeyboardEventHandler;

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

    public static Window getInstance(){
        return PlaneSimulatorX.window;
    }

    private JTextArea textArea;
    private JFrame frame;

    private boolean firstLoad;

    public Window() throws IOException {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        frame = new JFrame("Planes Simulator X");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(43, 43, 43));
        frame.getContentPane().add(mainPanel);

        textArea = new JTextArea();
        loadWindow("WelcomeWindow", "UTF-8");
        textArea.setEditable(false);
        textArea.setRows(24);
        textArea.setColumns(95);
        textArea.setLineWrap(true);
        textArea.setBackground(new Color(0, 0, 0));
        textArea.setForeground(new Color(0, 228, 0));
        textArea.setFont(loadFont("Consolas").deriveFont(16f));
        textArea.addKeyListener(new KeyListenerEvent());

        mainPanel.add(textArea);

        frame.pack();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle frameSize = frame.getBounds();
        int x = (int)(screenSize.getWidth()/2 - frameSize.getWidth()/2);
        int y = (int)(screenSize.getHeight()/2 - frameSize.getHeight()/2);
        frame.setLocation(x, y);

        frame.setVisible(true);

        setFirstLoad(true);
        KeyboardEventHandler.load();

    }

    public void setFirstLoad(boolean firstLoad){
        this.firstLoad = firstLoad;
    }

    public boolean isFirstLoad(){
        return firstLoad;
    }

    public void loadWindow(String path, String charset) throws IOException {
        String text = Files.readAllLines(Paths.get("src/assets/" + path + ".txt"), Charset.forName(charset)).stream().collect(Collectors.joining());
        textArea.setText(text);
        setFirstLoad(false);
    }

    public void loadWindow(String text){
        textArea.setText(text);
        setFirstLoad(false);
    }

    public void close(){
        frame.dispose();
    }

    private Font loadFont(String name){
        for(Font f : GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts()){
            if(f.getName().equalsIgnoreCase("Consolas")){
                return f;
            }
        }
        return null;
    }

}
