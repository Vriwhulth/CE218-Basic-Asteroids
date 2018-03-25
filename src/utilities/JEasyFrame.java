package utilities;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ap16718.
 */


// class that is responsible for making the frame.
public class JEasyFrame extends JFrame {

    public Component comp;

    public JEasyFrame(Component comp, String title) {
        super(title);
        this.comp = comp;
        // Add a component to the JFrame with a borderlayout manager
        getContentPane().add(BorderLayout.CENTER, comp);
        pack();
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        repaint();
    }
}
