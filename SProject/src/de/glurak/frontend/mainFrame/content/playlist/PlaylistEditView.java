package de.glurak.frontend.mainFrame.content.playlist;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.glurak.FrontendColors;
import de.glurak.data.Playlist;

public class PlaylistEditView extends JPanel{
	
	private JLabel 	lab_playlistname, lab_itemCount, lab_;
	private JButton bt_cancle, bt_delete, bt_rename;
	private Playlist plRef;
	private ActionListener lisRef;
	
	public PlaylistEditView(ActionListener l){
		super();
		lisRef = l;
		buildView();
		
	}
	
	private void buildView(){
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(600,500));
				
		JPanel pan_header = new JPanel(new GridBagLayout());
		GridBagConstraints constr = new GridBagConstraints();
		//constr.
		pan_header.setBackground(Color.GREEN);
		pan_header.setBounds(0, 0, 600, 150);
		
		lab_playlistname = new JLabel();
    	lab_playlistname.setForeground(Color.WHITE);
    	lab_playlistname.setFont(new Font("Verdana", Font.BOLD, 24));
    	
    	lab_itemCount = new JLabel();
    	lab_itemCount.setForeground(Color.WHITE);
    	lab_itemCount.setFont(new Font("Verdana", Font.BOLD, 12));
    	
    	bt_cancle = new JButton("Abbrechen");
    	bt_cancle.setActionCommand("cancel");
    	bt_cancle.addActionListener(lisRef);
    	
    	bt_rename = new JButton("Umbenennen");
    	bt_rename.setActionCommand("rename");
    	bt_rename.addActionListener(lisRef);
    	
    	
    	//pan_header.add(bt_cancle, BorderLayout.WEST);
    	constr.gridx = 0;
    	constr.gridy = 0;
    	
    	pan_header.add(bt_rename, constr);
    	constr.gridx = 0;
    	constr.gridy = 1;
    	pan_header.add(bt_cancle, constr);
    	constr.gridx = 1;
    	constr.gridy = 0;
    	constr.gridheight = 2;
    	pan_header.add(lab_playlistname, constr);
    	constr.gridx = 1;
    	constr.gridy = 2;
    	pan_header.add(lab_itemCount, constr);
       	pan_header.setVisible(true);
    	
    	this.add(pan_header, BorderLayout.NORTH);

    	this.setVisible(true);
	}
	
	public void setPlaylist(Playlist p){
		if (p != null){
			plRef = p;
			fillView();
		}
	}
	

	
	private void fillView(){
		lab_playlistname.setText(plRef.getName());
		lab_itemCount.setText("Songs: " + plRef.getMediumList().size());
		if (plRef.getMediumList().size() > 0)
			showMediumList();
		
	}
	
	/**
	 * Erzeugt eine Tabelle und füllt diese mit Informationen
	 * aus den Elementen der Playlist
	 */
	private void showMediumList(){
		
	}
	
}
