package de.glurak.frontend.mainFrame.content.profile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Diese Klasse stellt dem LabelProfileView die Funktionalität zur Verfügung.
 * @author Christopher Distelkämper
 * Date: 28.02.2014
 */
public class LabelProfileVController implements ActionListener{

	private LabelProfileView labelProfileView;
	
	/**
	 * Constructor
	 * @param artist Greift ein Künstler auf das Profil zu?
	 * @param labelManager Greift ein LabelManager auf das Profil zu?
	 * @param anzArtists <= 10
	 * @param anzPlaylists <= 5, falls mehr als 5 Playlisten existieren, sind diese über den "More"-Button verfügbar
	 */
	public LabelProfileVController(boolean artist, boolean labelManager, int anzArtists, int anzPlaylists){
		
		labelProfileView = new LabelProfileView(artist, labelManager, anzArtists, anzPlaylists);
		
		// Hinzufügen der ActionListener
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
		
		// Daten in die Textfelder schreiben
		
		labelProfileView.t_labelname.setText("");
		for (int i = 1; i <= anzArtists; i++){
			labelProfileView.t_kuenstler[i].setText("Test");
		}
		
	}
	
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		
		if (obj == labelProfileView.b_follow){
	
		} else if (obj == labelProfileView.b_moreplaylists){
	//		setContentController(new PlaylistVController());				
		} else if (obj == labelProfileView.b_edit){
	// 		setContentController(new ProfileEditVController());		
		} else if (obj == labelProfileView.b_apply){
	//  	setContentController(new ApplicationVController());		
		}
		
	}
	
}
