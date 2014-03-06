package de.glurak.frontend.mainFrame.content.playlist;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
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

import de.glurak.FrontendColors;
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
    private JLabel lab_name;
    
	private JPanel 		pan_buttons, pan_lowButtons;
	private SliderPanel pan_content;
	private ImageIcon 	ico_playList;
	private JButton 	bt_new, bt_edit, bt_next, bt_prev;
	private List<JPanel> pageArray = new ArrayList<JPanel>();	
	private List<JLabel> IconArray = new ArrayList<JLabel>();	
	private int currentPage = -1;
	private ActionListener actionRef;
	private MouseListener mouseRef;
    /**
     * Konstruktor
     */
    public PlaylistView(ActionListener a, MouseListener m) {
    	super();
    	this.setLayout(new BorderLayout());
    	this.actionRef = a;
    	this.mouseRef = m;
    	lab_name = new JLabel();
    	lab_name.setBounds(10, 10, 80, 50);
    	lab_name.setForeground(Color.white);
    	
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
    	bt_new.addActionListener(actionRef);
    	bt_edit = new JButton("Playlist bearbeiten");
    	bt_edit.setActionCommand("editList");
    	bt_edit.addActionListener(actionRef);
    	
    	pan_buttons.add(bt_new);
    	pan_buttons.add(bt_edit);
    	
    	// Navigation Buttons
    	bt_next = new JButton("next");
    	bt_next.setActionCommand("nextSlide");
    	bt_next.addActionListener(actionRef);
    	bt_prev = new JButton("previous");
    	bt_prev.setActionCommand("prevSlide");
    	bt_prev.addActionListener(actionRef);
    	    	
    	// Linking all the components together
    	pan_lowButtons.add(bt_next, BorderLayout.EAST);
    	pan_lowButtons.add(bt_prev, BorderLayout.WEST);
    	pan_lowButtons.add(lab_name, BorderLayout.CENTER);
    	
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
		Image img =  BGImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ico_playList = new ImageIcon(img);

    	JPanel jp = new JPanel();
    	jp.setPreferredSize(new Dimension(50, 50));
    	jp.setBackground(FrontendColors.DARK_GREY); 
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
    	currentPage = 0;
    	setVisible(true);
    }

    public void nextPage(){ 
    	if (pan_content.getItemCount() > 0){
    		pan_content.next();
    		currentPage++;
    		if (currentPage > pan_content.getItemCount())
    			currentPage = 0;
    	}	
    }
    
    public void prevPage(){ 
    	if (pan_content.getItemCount() > 0){
    		pan_content.previous();
    		currentPage--;
  			if (currentPage < 0)
			currentPage = pan_content.getItemCount();
		}	
    	//pan_content.removeSliderCompinent(contentPanArray.get(contentPanArray.size()-1));
    	//contentPanArray.remove(contentPanArray.size()-1);
    }
    
    /**
     * Erzeugt und positioniert ein Panel, das die übergebene Playist repräsentiert
     * @param p Playlistverweis
     */
    public void addPlaylist(Playlist p){
    	JLabel icon = new JLabel(ico_playList);
    	icon.setForeground(Color.WHITE);
    	icon.setFont(new Font("Verdana", Font.BOLD, 13));
    	icon.setText(p.getName());
    	icon.setHorizontalTextPosition(JLabel.CENTER);
    	icon.setVerticalTextPosition(JLabel.BOTTOM);
    	icon.setPreferredSize(new Dimension(100	,100));
    	icon.setVisible(true);
    	icon.addMouseListener(mouseRef);
    	IconArray.add(icon);
    	placeIcon(icon);
    	
    }
    
    private void placeIcon(JLabel icon){
    	
    	pageArray.get(currentPage).add(IconArray.get(IconArray.size()-1 ), BorderLayout.CENTER );
    	pan_content.refresh();
    	lab_name.setText("" + pan_content.getItemCount());
    }
    
    public JLabel getTextLabel(){
    	return lab_name;
    }
    
    public PlaylistView(JTable jT) {
        this.jT = jT;
       // this.playlistName = new JLabel("Playlist test");
        this.createAndShowView();
    }
    
    
    /**
     * Erzeugt die Playlistview und zeigt sie an.
     */
    private void createAndShowView() {
       // this.add(playlistName);
        this.add(jT);
    }

    public JTable getjT() {
        return jT;
    }

    public void setjT(JTable jT) {
        this.jT = jT;
    }
    
}
