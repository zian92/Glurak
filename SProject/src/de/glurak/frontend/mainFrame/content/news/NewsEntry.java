package de.glurak.frontend.mainFrame.content.news;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import de.glurak.Query;
import de.glurak.data.Album;
import de.glurak.data.Hateable;
import de.glurak.data.Medium;
import de.glurak.data.User.Label;
import de.glurak.data.User.User;
import de.glurak.frontend.mainFrame.ContentController;

//public class NewsEntry extends JLayeredPane {
public class NewsEntry extends Observable {

    private JLayeredPane layeredPane;
    private JLabel mainPicture;
    private JLabel ownerPicture;
    private JLabel textSpace;
    private Hateable hateTogepi;
    private JPanel newContent;
    private JButton bt_like, bt_hate;
    private ContentController newController;
    private int panWidth = 180;
    private int panHeight = 180;
    private int ownerIconWidth = 80;
    private int ownerIconHeight = 80;

    /**
     * 
     * 
     * @param mainImage
     *            The image displayed on the Slider
     * @param owner
     *            A reference to the owner of this entry
     * @param message
     *            A short text, that can be displayed
     */
    
    public NewsEntry(Medium source) {
        // TODO: implement Album img
        this(Query.FOLDER_PICTURE_ICONS + "musicfile.jpg", null, "Your Song", source);
    }
    public NewsEntry(Album source) {
        // TODO: implement Album img
        this(source.getFilename(), null, source.getName(), source);
    }

    public NewsEntry(User source) {
        // TODO: implement User img
        this(source.getProfile().getPictureFileNameOrDefaultPictureName(), null, source.getProfile().getFirstname(), source);
    }

    public NewsEntry(Label source) {
        this(null, null, source.getProfile().getName(), source);
    }

    public NewsEntry(String imgFilename, ContentController newContent, String message, Hateable h) {
        layeredPane = new JLayeredPane();
        // initialize components
        hateTogepi = h;
        newController = newContent;
        mainPicture = new JLabel(new ImageIcon(scaleImage(imgFilename, panWidth, panHeight)));
        mainPicture.setBounds(0, 0, panWidth, panHeight);
        ;
        /*
         * JFrame f = new JFrame(); f.getContentPane().setLayout(new BorderLayout()); f.getContentPane().add(mainPicture,BorderLayout.CENTER); f.setVisible(true); /* ownerPicture = new JLabel(new ImageIcon(sclaeImage( owner.getProfileImage() , ownerIconWidth, ownerIconHeight)))
         */
        textSpace = new JLabel(message);
        textSpace.setBounds(0, 0, panWidth, 70);
        textSpace.setForeground(Color.BLUE);
        // build NewsEntry
        bt_like = new JButton("L");
        bt_hate = new JButton("H");
        bt_like.setBounds(panWidth - 2 * 55, panHeight - 55, 50, 50);
        bt_hate.setBounds(panWidth - 55, panHeight - 55, 50, 50);

        layeredPane.setBounds(0, 0, panWidth, panHeight);

        layeredPane.add(mainPicture, JLayeredPane.DEFAULT_LAYER, 0);
        // TODO: add User Picture
        layeredPane.add(textSpace, JLayeredPane.PALETTE_LAYER, 0);
        layeredPane.add(bt_like, JLayeredPane.PALETTE_LAYER, 0);
        layeredPane.add(bt_hate, JLayeredPane.PALETTE_LAYER, 0);
        layeredPane.setVisible(true);
    }

    /**
     * 
     * 
     * @param filename
     *            Vollqualifizierter Dateiname oder null für DefaultImage
     * @param w
     * @param h
     * @return Skalliertes Bild oder skalliertes Defaultbild
     */
    private Image scaleImage(String filename, int w, int h) {
        Image scaledImg = null;
        if (filename != null) {
            try {
                BufferedImage img = ImageIO.read(new File(filename));
                scaledImg = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            scaledImg = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
        }
        return scaledImg;
    }

    public JLayeredPane getLayeredPane() {
        return this.layeredPane;
    }
}
