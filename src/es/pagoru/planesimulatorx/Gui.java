package es.pagoru.planesimulatorx;

import javax.swing.*;
import java.awt.*;

/**
 * Created by pablo on 26/10/16.
 */
public class Gui {

    public Gui(){

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Planes Simulator X");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel mainPanel = new JPanel();
        frame.getContentPane().add(mainPanel);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setRows(24);
        textArea.setColumns(96);
        textArea.setLineWrap(true);

        Font font = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts()[2];
        textArea.setFont(font.deriveFont(12f));
        textArea.setText(
                        "╒══════════════════════════════════════════════════════════════════════════════════════════════╕" +
                        "│                                                                                              │" +
                        "╘══════════════════════════════════════════════════════════════════════════════════════════════╛"
        );

        mainPanel.add(textArea);

        frame.pack();
        frame.setVisible(true);

    }

}
