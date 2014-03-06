package de.glurak;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SplashScreen extends JFrame {

    public SplashScreen() {
        JPanel c = new JPanel(new BorderLayout());
        // Set the window's bounds, centering the window
        int width = 300;
        int height = 300;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setBounds(x, y, width, height);
        // image
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(Query.SPLASHSCREEN_IMAGE));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setContentPane(new JLabel(new ImageIcon(img)));
        JLabel image = new JLabel(new ImageIcon(img));
        c.add(image, BorderLayout.CENTER);
        this.setUndecorated(true);
        c.setOpaque(false);
        c.setVisible(true);
        setBackground(new Color(0, 255, 0, 0));
        // this.setOpacity(0);
        this.add(c);
        this.setVisible(true);
    }

    public void hideSplashScreen() {
        setVisible(false);
        this.dispose();
    }
}