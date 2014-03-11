package de.glurak;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.glurak.feature.FontLoader;

/**
 * erstellt einen Splashscreen der angezeigt werden kann
 * 
 * @author Jonas
 * 
 */
public class SplashScreen extends JFrame {
    /**
     * der splashscreen wird erstellt und angezeigt
     */
    public SplashScreen() {
        JPanel c = new JPanel(new BorderLayout());
        // Set the window's bounds, centering the window
        int width = 500;
        int height = 300;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setBounds(x, y, width, height);
        // font
        Font font = null;
        try {
            font = new FontLoader().loadFontFromFile(Query.SPLASHSCREEN_FONT);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        font = font.deriveFont(18f);
        font = font.deriveFont(Font.BOLD);
        // image
        JLabel image = new JLabel(Query.APPLICATION_NAME, new ImageIcon(Query.SPLASHSCREEN_IMAGE), JLabel.LEFT);
        image.setFont(font);

        // Panel
        c.add(image, BorderLayout.CENTER);
        this.setUndecorated(true);
        c.setOpaque(false);
        c.setVisible(true);
        setBackground(new Color(0, 255, 0, 0));
        // this.setOpacity(0);
        this.add(c);
        this.setVisible(true);
    }

    /**
     * zerstört den Splashscreen
     */
    public void hideSplashScreen() {
        setVisible(false);
        this.dispose();
    }
}