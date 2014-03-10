package de.glurak.frontend.mainFrame.content.news;

import java.awt.Color;
import java.awt.Font;
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
import de.glurak.feature.IconLoader;
import de.glurak.frontend.mainFrame.ContentController;

//public class NewsEntry extends JLayeredPane {
public class NewsEntry extends Observable {


    private String fullPicturePathName;
    private String message;
    private int flag = 0;
    private Hateable source;
    
    public NewsEntry(Medium source) {
        // TODO: implement Album img
        //this( source.getPictureFilename(), null, "Your Song", source);
        this(Query.FOLDER_PICTURE_ICONS + "musicfile.jpg", "Your Song", source);
        flag = 1;

    }
    public NewsEntry(Album source) {
        // TODO: implement Album img
        this(source.getFilename(), source.getName(), source);
        flag = 2;
    }

    public NewsEntry(User source) {
        // TODO: implement User img
        this(source.getProfile().getPictureFileNameOrDefaultPictureName(), source.getProfile().getFirstname(), source);
        flag = 3;
    }

    public NewsEntry(Label source) {
        this(null, source.getProfile().getName(), source);
        flag = 4;
    }

    public NewsEntry(String imgFilename, String message, Hateable h) {
        this.fullPicturePathName = imgFilename;
        this.message = message;
        this.source = h;
        //TODO: link Hateble to this newsEntry or manage the Hate-Forwarding within the viewController that uses this Entry
    }

    public String getPictureName(){
    	return fullPicturePathName;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public Hateable getSource(){
    	return source;
    }
    
    /**
     * Liefert den Typflag des NewsEntry Source.
     *  Flag = 1: Typ Medium
     *  Flag = 2: Typ Album
     *  Flag = 3: Typ User
     *  Flag = 4: Typ 	 
     * @return
     */
    public int getType(){
    	return flag;
    }
}
