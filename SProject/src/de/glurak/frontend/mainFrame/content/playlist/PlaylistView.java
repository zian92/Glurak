package de.glurak.frontend.mainFrame.content.playlist;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import de.glurak.Query;
import de.glurak.data.Playlist;
import de.glurak.feature.SliderPanel;

/**
 * In der dieser View wird eine erstellte Playlist von einem User angezeigt. Die Playlist besteht aus Musikdateien.
 * 
 * @author Simon
 * 
 */
public class PlaylistView extends JPanel {
    
	private JTable jT;
    private JLabel playlistName;
    
	private JPanel 		pan_buttons, pan_lowButtons;
	private SliderPanel pan_content;
	private ImageIcon 	ico_playList;
	private JButton 	bt_new, bt_edit, bt_next, bt_prev;
	private List<JPanel> pageArray = new ArrayList<JPanel>();	
	private List<JPanel> IconArray = new ArrayList<JPanel>();	
	
    /**
     * Konstruktor
     */
    public PlaylistView() {
    	super();
    	this.setLayout(new BorderLayout());
    	
    	// Buttonpanel for the main functions of this view
    	pan_buttons = new JPanel(new FlowLayout());
    	//pan_buttons.setPreferredSize(new Dimension(600, 80));
    	pan_buttons.setBounds(0, 10, 600, 80);
    	pan_buttons.setBackground(Color.BLACK);
    	
        // Buttonpanel for the navigation within this Panel
    	pan_lowButtons = new JPanel(new BorderLayout());
    	pan_lowButtons.setBounds(0, 10, 600, 80);
    	pan_lowButtons.setBackground(Color.BLACK);
    	
    	// Main Buttons
    	bt_new = new JButton("Neue Playlist erstellen");
    	bt_new.setActionCommand("newList");
    	bt_edit = new JButton("Playlist bearbeiten");
    	bt_edit.setActionCommand("editList");
    	
    	pan_buttons.add(bt_new);
    	pan_buttons.add(bt_edit);
    	
    	// Navigation Buttons
    	bt_next = new JButton("next");
    	bt_next.setActionCommand("nextSlide");
    	bt_prev = new JButton("previous");
    	bt_prev.setActionCommand("prevSlide");
    	    	
    	// Linking all the components together
    	pan_lowButtons.add(bt_next, BorderLayout.EAST);
    	pan_lowButtons.add(bt_prev, BorderLayout.WEST);
    	
    	pan_content = new SliderPanel();
    	pan_content.setPreferredSize(new Dimension(600,300));
    	
    	
    	// creating a test environment
    	
    	BufferedImage BGImage = null;
		try {
			BGImage = ImageIO.read(new File(Query.FOLDER_PICTURE_ICONS + "pll.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Image img =  BGImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ico_playList = new ImageIcon(img);

    	JPanel jp = new JPanel();
    	jp.setPreferredSize(new Dimension(50, 50));
    	jp.setBackground(Color.ORANGE); 
    	pageArray.add(jp);
      	JPanel jp2 = new JPanel();
    	jp2.setPreferredSize(new Dimension(50, 50));
    	jp2.setBackground(Color.BLUE);
    	pageArray.add(jp2);
     	JPanel jp3 = new JPanel();
    	jp3.setPreferredSize(new Dimension(50, 50));
    	jp3.setBackground(Color.MAGENTA);
    	pageArray.add(jp3);
    
    	for (int i =0; i < pageArray.size(); i++)
    		pan_content.addSliderComponent(pageArray.get(i));
    	
    	add(pan_buttons, BorderLayout.NORTH);
    	add(pan_content, BorderLayout.CENTER);
    	add(pan_lowButtons, BorderLayout.SOUTH);
    	setVisible(true);
    }
    
    public JButton getBtNext(){ return bt_next; }
    public JButton getBtPrev(){ return bt_prev; }
    public JButton getBtNew(){ return bt_new; }

    public void nextPage(){ 
    	if (pan_content.getItemCount() > 0)
    		pan_content.previous();
   
    }
    
    /**
     * Erzeugt und positioniert ein Panel, das die übergebene Playist repräsentiert
     * @param p Playlistverweis
     */
    public void addPlaylist(Playlist p){
    	JPanel jp = new JPanel(new BorderLayout());
    	JLabel icon = new JLabel(ico_playList);
    	icon.setText(p.getName());
    	icon.setHorizontalTextPosition(JLabel.CENTER);
    	icon.setVerticalTextPosition(JLabel.BOTTOM);
    	jp.setPreferredSize(new Dimension(80,65));
    	jp.setVisible(true);
    	jp.add(icon, BorderLayout.CENTER);
    	IconArray.add(jp);
    	System.out.println("PlaylistView: Icon set");
    	pageArray.get(0).add(IconArray.get(IconArray.size()-1 ), BorderLayout.CENTER );
    	pan_content.refresh();
    }
    
    
    public void prevPage(){ 
    	if (pan_content.getItemCount() > 0)
    		pan_content.next();
    	//pan_content.removeSliderCompinent(contentPanArray.get(contentPanArray.size()-1));
    	//contentPanArray.remove(contentPanArray.size()-1);
    }
    
    public PlaylistView(JTable jT) {
        this();
        this.jT = jT;
        this.playlistName = new JLabel("Playlist test");
        this.createAndShowView();
    }

    /**
     * Erzeugt die Playlistview und zeigt sie an.
     */
    private void createAndShowView() {
        this.add(playlistName);
        this.add(jT);
    }

    public JTable getjT() {
        return jT;
    }

    public void setjT(JTable jT) {
        this.jT = jT;
    }

}
