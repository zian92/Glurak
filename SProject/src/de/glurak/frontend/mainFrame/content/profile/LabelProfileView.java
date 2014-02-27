package de.glurak.frontend.mainFrame.content.profile;

import javax.swing.*;
import java.awt.*;

/**
 * Die LabelProfileView zeigt dem Benutzer ein Labelprofile an.
 * @author Christopher Distelkämper
 * Date: 27.02.2014
 */

public class LabelProfileView extends JPanel{

		// Panels
		private JPanel pan_labelprofileview;
		private JPanel pan_profilepic;
		private JPanel pan_profiledata;
		private JPanel pan_playlists;
		
		// Buttons
		private JButton b_apply;
		private JButton b_follow;
		private JButton b_edit;
		private JButton b_moreplaylists;
		
		// TextFields profile_data
		private JTextField t_labelname;
		
		// Labels profile_data
		private JLabel l_labelname;		
		
		/**
		 * Constructor
		 * @param artist Greift ein Artist auf das Profil zu?
		 * @param labelManager Greift ein Labelmanager auf das Profil zu?
		 * @param anzArtists
		 */
		public LabelProfileView(boolean artist, boolean labelManager, int anzArtists){
			
			// Initialisieren Panel pan_profileview
			pan_labelprofileview = new JPanel(new GridBagLayout());
			pan_labelprofileview.setSize(1000,500);
			
			// Layout-Restriktionen festlegen.
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(2,2,2,2);	
						
			// Initialisieren Panel pan_profilepic
			pan_profilepic = new JPanel(new GridBagLayout());
			pan_profilepic.setSize(350, 200);
			pan_profilepic.setBorder(BorderFactory.createLineBorder(Color.black));
			
				// Layout-Restriktionen festlegen.
				GridBagConstraints d = new GridBagConstraints();
				d.fill = GridBagConstraints.HORIZONTAL;
				d.insets = new Insets(2,2,2,2);	
					
			    // Initialisieren der Buttons b_apply, b_follow, b_edit
				if (labelManager){  // Falls aus der Sicht des LabelManagers auf das LabelProfile zugegriffen wird.
				
			    	d.gridx = 0;
					d.gridy = 1;
					b_edit = new JButton("Bearbeiten");
					pan_profilepic.add(b_edit, d);
					
				}
				else{      
					
					if (artist){    // Falls aus der Sicht des Künstlers auf das LabelProfile zugegriffen wird.
						d.gridx = 0;
						d.gridy = 1;
						b_apply = new JButton("Bewerben");
						pan_profilepic.add(b_apply, d);    
				    
						d.gridx = 1;
						d.gridy = 1;
						b_follow = new JButton("Follow");
						pan_profilepic.add(b_follow, d);	
					}
					else{		 // Falls weder der LabelManager noch der Künstler auf das Profil zugreifen.
						d.gridx = 0;
						d.gridy = 1;
						b_follow = new JButton("Follow");
						pan_profilepic.add(b_follow, d);
					}
				}
			// Initialisieren Panel pan_topplaylists
			pan_playlists = new JPanel(new GridBagLayout());	
			pan_playlists.setSize(350, 200);
			pan_playlists.setBorder(BorderFactory.createLineBorder(Color.black));
			
				// Initialisieren des Buttons b_moreplaylists
				b_moreplaylists = new JButton("mehr");
				pan_playlists.add(b_moreplaylists);
						
			// Initialisieren Panel pan_profiledata
			pan_profiledata = new JPanel(new GridBagLayout());	
			pan_profiledata.setSize(350, 200);
			pan_profiledata.setBorder(BorderFactory.createLineBorder(Color.black));
			
				// Layout-Restriktionen festlegen.
				GridBagConstraints e = new GridBagConstraints();
				e.fill = GridBagConstraints.HORIZONTAL;
				e.insets = new Insets(2,2,2,2);	

				// Label und Textfelder hinzufügen
				// Username
				e.gridx = 0;
				e.gridy = 0;
				e.weightx = 0.0;
				l_labelname = new JLabel("Labelname:");
				pan_profiledata.add(l_labelname, e);
				
				e.gridx = 1;
				e.gridy = 0;
				e.weightx = 1.0;
				t_labelname = new JTextField();
				t_labelname.setEditable(false);
				pan_profiledata.add(t_labelname, e);
				
				for (int i = 1; i == anzArtists; i++){
					/*
					 * ToDo: Erzueugen mehrerer JLabels und JTextFields für jeden Künstler eins.
					// Label und Textfelder hinzufügen
					e.gridx = 0;
					e.gridy = i-1;
					e.weightx = 0.0;
					JLabel i = new JLabel("Künstler " + i + ":");
					pan_profiledata.add(l_labelname, e);
					
					e.gridx = 1;
					e.gridy = i-1;
					e.weightx = 1.0;
					JTextField i = new JTextField();
					t_labelname.setEditable(false);
					pan_profiledata.add(t_labelname, e);
					*/
				}
			
				
				
			// Hinzufügen der Panels zum Panel pan_profileview
			c.gridx = 0;
			c.gridy = 0;
			c.weightx = 0.5;
			c.weighty = 0.5;
			c.gridheight = 2;
			pan_labelprofileview.add(pan_playlists, c);
			
			c.gridx = 1;
			c.gridy = 0;
			c.weightx = 0.5;
			c.weighty = 0.5;
			pan_labelprofileview.add(pan_profilepic, c);

			
			c.gridx = 1;
			c.gridy = 1;
			c.weightx = 0.5;
			c.weighty = 0.5;
			pan_labelprofileview.add(pan_profiledata, c);
			
			// Hinzufügen des Panels zur ContentPane
			add(pan_labelprofileview);		
			setVisible(true);
			
		}
	
	
	/**
	 * Erzeugt die LabelProfileView und zeigt sie an.
	 */
	private static void createAndShowView(){
		//Erzeugen des Frames
		JFrame labelprofile = new JFrame("LabelProfile");
	    labelprofile.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Registrationview in das Frame laden
		JComponent newContentPane = new LabelProfileView(true, false, 2);
        newContentPane.setOpaque(true);
        labelprofile.setContentPane(newContentPane);
        
        //Groesse des Frames festlegen
        labelprofile.setPreferredSize(new Dimension(1000, 500));
        //Groesse des Frames soll nicht veraenderbar sein
        labelprofile.setResizable(false);
        //Registrationview wird in der Mitte des Bildschirms geladen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        labelprofile.setLocation(dim.width/2-labelprofile.getSize().width/2-300, dim.height/2-labelprofile.getSize().height/2-150);
		
		//Frame anpassen und sichtbar machen
		labelprofile.pack();
		labelprofile.setVisible(true);
	}
	
	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowView();
            }
        });
	}
	
}
