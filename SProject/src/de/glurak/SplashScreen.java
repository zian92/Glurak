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

    private final String filepath = Query.FOLDER_PICTURE_ICONS + "splash.png";

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
            img = ImageIO.read(new File(filepath));
        } catch (IOException e) {
            // TODO Auto-generated catch block
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

    public void showSplash() {
        JPanel content = new JPanel(new BorderLayout());// (JPanel) getContentPane();
        content.setBackground(Color.black);

        // Set the window's bounds, centering the window
        int width = 300;
        int height = 250;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setBounds(x, y, width, height);

        // Build the splash screen
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(filepath));
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        JLabel label = new JLabel(new ImageIcon(img));
        content.add(label, BorderLayout.CENTER);
        content.setVisible(true);
        // Display it
        // this.setUndecorated(true);
        add(content);
        setVisible(true);

        // wait for init
        try {
            Thread.sleep(200);
        } catch (Exception e) {
        }
    }

    public void hideSplashScreen() {
        setVisible(false);
        this.dispose();
    }

    public static void main(String[] args) {
        SplashScreen s = new SplashScreen();

    }
}