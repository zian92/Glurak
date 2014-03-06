package de.glurak.frontend.mainFrame.content.playlist;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

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
	
	private JButton 	bt_new, bt_edit, bt_next, bt_prev;
	private List<JPanel> contentPanArray = new ArrayList<JPanel>();	
    /**
     * Konstruktor
     */
    public PlaylistView() {
    	super();
    	this.setLayout(new BorderLayout());
    	
    	// Buttonpanel for the main functions of this view
    	pan_buttons = new JPanel();
    	pan_buttons.setPreferredSize(new Dimension(600, 150));
    	pan_buttons.setBackground(Color.BLACK);
    	
        // Buttonpanel for the navigation within this Panel
    	pan_lowButtons = new JPanel(new BorderLayout());
    	pan_lowButtons.setPreferredSize(new Dimension(600, 60));
    	pan_lowButtons.setBackground(Color.BLACK);
    	
    	// Main Buttons
    	bt_new = new JButton("Neue Playlist erstellen");
    	bt_edit = new JButton("Playlist bearbeiten");
    	
    	// Navigation Buttons
    	bt_next = new JButton("next");
    	bt_next.setActionCommand("nextSlide");
    	bt_prev = new JButton("previous");
    	bt_prev.setActionCommand("prevSlide");
    	
    	
    	// Linking all the components together
    	pan_lowButtons.add(bt_next, BorderLayout.EAST);
    	pan_lowButtons.add(bt_prev, BorderLayout.WEST);
    	
    	pan_content = new SliderPanel();
    	pan_content.setPreferredSize(new Dimension(200,250));
    	
    	
    	// creating a test environment
    	JPanel jp = new JPanel();
    	jp.setPreferredSize(new Dimension(50, 50));
    	jp.setBackground(Color.ORANGE); 
    	contentPanArray.add(jp);
      	JPanel jp2 = new JPanel();
    	jp2.setPreferredSize(new Dimension(50, 50));
    	jp2.setBackground(Color.BLUE);
    	contentPanArray.add(jp2);
     	JPanel jp3 = new JPanel();
    	jp3.setPreferredSize(new Dimension(50, 50));
    	jp3.setBackground(Color.MAGENTA);
    	contentPanArray.add(jp3);
    
    	for (int i =0; i < contentPanArray.size(); i++)
    		pan_content.addSliderComponent(contentPanArray.get(i));
    	
    	add(pan_buttons, BorderLayout.NORTH);
    	add(pan_content, BorderLayout.CENTER);
    	add(pan_lowButtons, BorderLayout.SOUTH);
    	setVisible(true);
    }
    
    public JButton getBtNext(){ return bt_next; }
    public JButton getBtPrev(){ return bt_prev; }

    public void nextPage(){ 
    	if (pan_content.getItemCount() > 0)
    		pan_content.previous();
   
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
