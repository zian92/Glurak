package de.glurak.frontend.mainFrame.content.playlist;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

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
    /**
     * Konstruktor
     */
    public PlaylistView() {
    	super();
    	this.setLayout(new BorderLayout());
    	
    	pan_buttons = new JPanel();
    	pan_buttons.setPreferredSize(new Dimension(600, 150));
    	pan_buttons.setBackground(Color.BLACK);
        
    	pan_lowButtons = new JPanel(new BorderLayout());
    	pan_lowButtons.setPreferredSize(new Dimension(600, 60));
    	pan_lowButtons.setBackground(Color.BLACK);
    	
    	bt_next = new JButton("next");
    	bt_prev = new JButton("previous");
    	
    	pan_lowButtons.add(bt_next, BorderLayout.EAST);
    	pan_lowButtons.add(bt_prev, BorderLayout.WEST);
    	
    	pan_content = new SliderPanel();
    	pan_content.setPreferredSize(new Dimension(200,250));
    	
    	JPanel jp = new JPanel();
    	jp.setPreferredSize(new Dimension(50, 50));
    	jp.setBackground(Color.ORANGE);
    	JPanel jp2 = new JPanel();
    	jp2.setPreferredSize(new Dimension(50, 50));
    	jp2.setBackground(Color.BLUE);
    	pan_content.addSliderComponent(jp);
    	pan_content.addSliderComponent(jp2);
    	
    	add(pan_buttons, BorderLayout.NORTH);
    	add(pan_content, BorderLayout.CENTER);
    	add(pan_lowButtons, BorderLayout.SOUTH);
    	
    	setVisible(true);
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
