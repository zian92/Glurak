package de.glurak.frontend.mainFrame.content.news;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.geom.QuadCurve2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.glurak.Query;
import de.glurak.data.Album;
import de.glurak.data.Hateable;
import de.glurak.data.Medium;
import de.glurak.data.User.*;
import de.glurak.frontend.mainFrame.ContentController;

public class NewsEntry extends JLayeredPane{
	
	private JLabel mainPicture;
	private JLabel ownerPicture;
	private JLabel textSpace; 
	private Hateable hateTogepi;
	private JPanel newContent;
	private JButton bt_like, bt_hate;
	private ContentController newController;
	private int panWidth = 190,
				panHeight = 180,
				ownerIconWidth = 20,
				ownerIconHeight = 20;
	
	public NewsEntry(Medium source){
		//TODO: implement Album img 
		this(Query.FOLDER_PICTURE_ICONS + "musicfile.jpg", null, source.getTitel(),source);
	}
	public NewsEntry(Album source){
		//TODO: implement Album img 
		this(source.getFilename(), null, source.getName(),source);
	}
	public NewsEntry(User source){

		//TODO: implement User img
		this(null, null, source.getProfile().getFirstname(),source);
	}
	public NewsEntry(Label source){
		this(null, null, source.getProfile().getName() ,source);
	}
	
	public NewsEntry(String imgFilename, ContentController newContent, String message, Hateable h){
		// initialize components
		hateTogepi=h;
		newController= newContent;
		mainPicture = new JLabel(new ImageIcon( scaleImage(imgFilename, panWidth, panHeight)));
		mainPicture.setBounds(0, 0, panWidth, panHeight);;
		textSpace = new JLabel(message);
		textSpace.setBounds(0, 0, panWidth, 70);
		textSpace.setForeground(Color.BLUE);
		// build NewsEntry
		bt_like = new JButton("L");
		bt_hate = new JButton("H");
		bt_like.setBounds(panWidth-2*35,panHeight-35,30,30);
		bt_hate.setBounds(panWidth-35,panHeight-35,30,30);
		
		setBounds(0 , 0, panWidth ,panHeight);
		
		add(mainPicture, JLayeredPane.DEFAULT_LAYER, 0);
		//TODO:  add User Picture
		add(textSpace, JLayeredPane.PALETTE_LAYER, 0);
		add(bt_like, JLayeredPane.PALETTE_LAYER, 0);
		add(bt_hate, JLayeredPane.PALETTE_LAYER, 0);
		setVisible(true);
	}
	
	
	/**
	 * 
	 * 
	 * @param filename Vollqualifizierter Dateiname oder null für DefaultImage
	 * @param w	
	 * @param h
	 * @return Skalliertes Bild oder skalliertes Defaultbild
	 */
	private Image scaleImage(String filename, int w, int h){
		Image scaledImg = null;
		if (filename != null){
			try {
				BufferedImage img = ImageIO.read(new File(filename));
				scaledImg =  img.getScaledInstance( w, h, Image.SCALE_SMOOTH);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			scaledImg = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
		}
		return scaledImg;
	}
}
