package de.glurak.frontend.mainFrame.content.profile;

import java.awt.event.*;
import javax.swing.*;

/**
 * Diese Klasse stellt dem LabelProfileView die Funktionalität zur Verfügung.
 * @author Christopher Distelkämper
 * Date: 28.02.2014
 */
public class LabelProfileVController implements ActionListener{

	private LabelProfileView labelProfileView;
	
	public LabelProfileVController(boolean artist, boolean labelManager, int anzArtists){
		
		labelProfileView = new LabelProfileView(artist, labelManager, anzArtists);
		
		labelProfileView.b_moreplaylists.addActionListener(this);
		if (artist){
			labelProfileView.b_apply.addActionListener(this);
			labelProfileView.b_follow.addActionListener(this);
		}
		else{
			
			if (labelManager){
				labelProfileView.b_edit.addActionListener(this);
			}
			else{
				labelProfileView.b_follow.addActionListener(this);
			}
		}
	}
	
	public void actionPerformed(ActionEvent e){
		
	}
	
}
