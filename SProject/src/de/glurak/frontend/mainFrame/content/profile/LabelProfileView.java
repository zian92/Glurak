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
		protected JButton b_apply;
		protected JButton b_follow;
		protected JButton b_edit;
		protected JButton b_moreplaylists;
		
		// TextFields profile_data
		protected JTextField t_labelname;
		protected JTextField[] t_kuenstler;
		
		// Labels profile_data
		private JLabel l_labelname;		
		private JLabel[] l_kuenstler;
		
		/**
		 * Constructor
		 * @param artist Greift ein Artist auf das Profil zu?
		 * @param labelManager Greift ein Labelmanager auf das Profil zu?
		 * @param anzArtists <= 10
		 */
		public LabelProfileView(boolean artist, boolean labelManager, int anzArtists){
			
			// Initialisieren Panel pan_profileview
			pan_labelprofileview = new JPanel(new GridBagLayout());
			pan_labelprofileview.setSize(1000,500);
			pan_labelprofileview.setBackground(Color.black);
			
			// Layout-Restriktionen festlegen.
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(2,2,2,2);	
						
			// Initialisieren Panel pan_profilepic
			pan_profilepic = new JPanel(new GridBagLayout());
			pan_profilepic.setSize(350, 200);
			pan_profilepic.setBorder(BorderFactory.createLineBorder(Color.black));
			pan_profilepic.setBackground(Color.black);
			
				// Layout-Restriktionen festlegen.
				GridBagConstraints d = new GridBagConstraints();
				d.fill = GridBagConstraints.HORIZONTAL;
				d.insets = new Insets(2,2,2,2);	
					
			    // Initialisieren der Buttons b_apply, b_follow, b_edit
				if (labelManager){  // Falls aus der Sicht des LabelManagers auf das LabelProfile zugegriffen wird.
				
			    	d.gridx = 0;
					d.gridy = 1;
					b_edit = new JButton("Bearbeiten");
					b_edit.setBorder(BorderFactory.createLineBorder(Color.white));
					b_edit.setBackground(Color.black);
					b_edit.setForeground(Color.white);
					pan_profilepic.add(b_edit, d);
					
				}
				else{      
					
					if (artist){    // Falls aus der Sicht des Künstlers auf das LabelProfile zugegriffen wird.
						d.gridx = 0;
						d.gridy = 1;
						b_apply = new JButton("Bewerben");
						b_apply.setBorder(BorderFactory.createLineBorder(Color.white));
						b_apply.setBackground(Color.black);
						b_apply.setForeground(Color.white);
						pan_profilepic.add(b_apply, d);    
				    
						d.gridx = 1;
						d.gridy = 1;
						b_follow = new JButton("Follow");
						b_follow.setBorder(BorderFactory.createLineBorder(Color.white));
						b_follow.setBackground(Color.black);
						b_follow.setForeground(Color.white);
						pan_profilepic.add(b_follow, d);	
					}
					else{		 // Falls weder der LabelManager noch der Künstler auf das Profil zugreifen.
						d.gridx = 0;
						d.gridy = 1;
						b_follow = new JButton("Follow");
						b_follow.setBorder(BorderFactory.createLineBorder(Color.white));
						b_follow.setBackground(Color.black);
						b_follow.setForeground(Color.white);
						pan_profilepic.add(b_follow, d);
					}
				}
			// Initialisieren Panel pan_topplaylists
			pan_playlists = new JPanel(new GridBagLayout());	
			pan_playlists.setSize(350, 200);
			pan_playlists.setBorder(BorderFactory.createLineBorder(Color.black));
			pan_playlists.setBackground(Color.black);
			
				// Initialisieren des Buttons b_moreplaylists
				b_moreplaylists = new JButton("mehr");
				b_moreplaylists.setBorder(BorderFactory.createLineBorder(Color.white));
				b_moreplaylists.setBackground(Color.black);
				b_moreplaylists.setForeground(Color.white);
				pan_playlists.add(b_moreplaylists);
						
			// Initialisieren Panel pan_profiledata
			pan_profiledata = new JPanel(new GridBagLayout());	
			pan_profiledata.setSize(350, 200);
			pan_profiledata.setBorder(BorderFactory.createLineBorder(Color.black));
			pan_profiledata.setBackground(Color.black);
			
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
				l_labelname.setForeground(Color.white);
				pan_profiledata.add(l_labelname, e);
				
				e.gridx = 1;
				e.gridy = 0;
				e.weightx = 1.0;
				t_labelname = new JTextField();
				t_labelname.setEditable(false);
				t_labelname.setBackground(Color.black);
				t_labelname.setForeground(Color.white);
				pan_profiledata.add(t_labelname, e);
				
				
				t_kuenstler = new JTextField[10];
				l_kuenstler = new JLabel[10];
				
				for (int i = 1; i == anzArtists; i++){

					// Label und Textfelder hinzufügen
					e.gridx = 0;
					e.gridy = i+1;
					e.weightx = 0.0;
					l_kuenstler[i] = new JLabel("Künstler " + i + ":");
					pan_profiledata.add(l_kuenstler[i], e);
					
					e.gridx = 1;
					e.gridy = i+1;
					e.weightx = 1.0;
					t_kuenstler[i] = new JTextField();
					t_labelname.setEditable(false);
					pan_profiledata.add(t_kuenstler[i], e);

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
