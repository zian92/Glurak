package de.glurak.frontend.mainFrame.content.profile;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Die LabelProfileView zeigt dem Benutzer ein Labelprofile an.
 * @author Christopher Distelkämper
 * Date: 27.02.2014
 */

public class LabelProfileView extends JPanel{

		// Panels
		private JPanel pan_labelprofileview;
		private JPanel pan_profilepic;
		protected JPanel pan_picture;
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
		protected JTextField[] t_playlist;
		
		// Labels profile_data
		private JLabel l_labelname;		
		private JLabel[] l_kuenstler;
		private JLabel[] l_playlist;
		
		/**
		 * Constructor
		 * @param artist Greift ein Artist auf das Profil zu?
		 * @param labelManager Greift ein Labelmanager auf das Profil zu?
		 * @param anzArtists <= 10
		 * @param anzPlaylists <= 5 bei mehr als 5 klicke "More"-Button
		 */
		public LabelProfileView(boolean artist, boolean labelManager, int anzArtists, int anzPlaylists){
			
			// Initialisieren Panel pan_profileview
			pan_labelprofileview = new JPanel(new GridBagLayout());
			pan_labelprofileview.setPreferredSize(new Dimension(724, 545));
			pan_labelprofileview.setBackground(Color.black);
			
			// Layout-Restriktionen festlegen.
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(2,2,2,2);	
						
			// Initialisieren Panel pan_profilepic
			pan_profilepic = new JPanel(new GridBagLayout());
			pan_profilepic.setPreferredSize(new Dimension(350, 200));
			pan_profilepic.setBorder(BorderFactory.createLineBorder(Color.black));
			pan_profilepic.setBackground(Color.black);
			
				// Layout-Restriktionen festlegen.
				GridBagConstraints d = new GridBagConstraints();
				d.fill = GridBagConstraints.HORIZONTAL;
				d.insets = new Insets(2,2,2,2);	
				
				// Initialisieren des Labelbildes
				d.gridx = 0;
				d.gridy = 0;
				d.gridheight = 1;
				d.gridwidth = 2;
				pan_picture = new JPanel();
				pan_picture.setPreferredSize(new Dimension(100, 100));
				pan_picture.setBackground(Color.green);
				pan_profilepic.add(pan_picture, d);
				
			    // Initialisieren der Buttons b_apply, b_follow, b_edit
				if (labelManager){  // Falls aus der Sicht des LabelManagers auf das LabelProfile zugegriffen wird.
				
			    	d.gridx = 0;
					d.gridy = 1;
					d.gridheight = 1;
					d.gridwidth = 2;
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
						d.gridheight = 1;
						d.gridwidth = 1;
						b_apply = new JButton("Bewerben");
						b_apply.setBorder(BorderFactory.createLineBorder(Color.white));
						b_apply.setBackground(Color.black);
						b_apply.setForeground(Color.white);
						pan_profilepic.add(b_apply, d);    
				    
						d.gridx = 1;
						d.gridy = 1;
						d.gridheight = 1;
						d.gridwidth = 1;
						b_follow = new JButton("Follow");
						b_follow.setBorder(BorderFactory.createLineBorder(Color.white));
						b_follow.setBackground(Color.black);
						b_follow.setForeground(Color.white);
						pan_profilepic.add(b_follow, d);	
					}
					else{		 // Falls weder der LabelManager noch der Künstler auf das Profil zugreifen.
						d.gridx = 0;
						d.gridy = 1;
						d.gridheight = 1;
						d.gridwidth = 2;
						b_follow = new JButton("Follow");
						b_follow.setBorder(BorderFactory.createLineBorder(Color.white));
						b_follow.setBackground(Color.black);
						b_follow.setForeground(Color.white);
						pan_profilepic.add(b_follow, d);
					}
				}
			// Initialisieren Panel pan_playlists
			pan_playlists = new JPanel(new GridBagLayout());	
			pan_playlists.setPreferredSize(new Dimension(350, 400));
			pan_playlists.setBorder(BorderFactory.createLineBorder(Color.black));
			pan_playlists.setBackground(Color.black);
			
				// Layout-Restriktionen festlegen.
				GridBagConstraints e = new GridBagConstraints();
				e.fill = GridBagConstraints.HORIZONTAL;
				e.insets = new Insets(2,2,2,2);	
				
				l_playlist = new JLabel[5];
				t_playlist = new JTextField[5];
				
				for (int i = 1; i <= anzPlaylists; i++){
					
					// Labels und Textfelder hinzufügen
					e.gridx = 0;
					e.gridy = i-1;
					e.weightx = 0.0;
					e.gridheight = 1;
					e.gridwidth = 1;
					l_playlist[i] = new JLabel("Playlist " + i + ":");
					l_playlist[i].setForeground(Color.white);
					pan_playlists.add(l_playlist[i], e);
					
					e.gridx = 1;
					e.gridy = i-1;
					e.weightx = 1.0;
					e.gridheight = 1;
					e.gridwidth = 1;
					t_playlist[i] = new JTextField();
					t_playlist[i].setBackground(Color.black);
					t_playlist[i].setForeground(Color.white);
					t_playlist[i].setEditable(false);
					t_playlist[i].setCaretColor(Color.white);
					pan_playlists.add(t_playlist[i], e);
					
				}
				
				// Initialisieren des Buttons b_moreplaylists
				e.gridx = 0;
				e.gridy = anzPlaylists;
				e.weightx = 0.0;
				e.gridheight = 1;
				e.gridwidth = 2;
				b_moreplaylists = new JButton("mehr");
				b_moreplaylists.setBorder(BorderFactory.createLineBorder(Color.white));
				b_moreplaylists.setBackground(Color.black);
				b_moreplaylists.setForeground(Color.white);
				pan_playlists.add(b_moreplaylists, e);
						
			// Initialisieren Panel pan_profiledata
			pan_profiledata = new JPanel(new GridBagLayout());	
			pan_profiledata.setPreferredSize(new Dimension(350, 200));
			pan_profiledata.setBorder(BorderFactory.createLineBorder(Color.black));
			pan_profiledata.setBackground(Color.black);
			
				// Layout-Restriktionen festlegen.
				GridBagConstraints f = new GridBagConstraints();
				f.fill = GridBagConstraints.HORIZONTAL;
				f.insets = new Insets(2,2,2,2);	

				// Label und Textfelder hinzufügen
				// Username
				f.gridx = 0;
				f.gridy = 0;
				f.weightx = 0.0;
				l_labelname = new JLabel("Labelname:");
				l_labelname.setForeground(Color.white);
				pan_profiledata.add(l_labelname, f);
				
				f.gridx = 1;
				f.gridy = 0;
				f.weightx = 1.0;
				t_labelname = new JTextField();
				t_labelname.setEditable(false);
				t_labelname.setBackground(Color.black);
				t_labelname.setForeground(Color.white);
				pan_profiledata.add(t_labelname, f);
				
				
				t_kuenstler = new JTextField[10];
				l_kuenstler = new JLabel[10];
				
				for (int i = 1; i <= anzArtists; i++){

					// Label und Textfelder hinzufügen
					f.gridx = 0;
					f.gridy = i+1;
					f.gridheight = 1;
					f.gridwidth = 1;
					f.weightx = 0.0;
					l_kuenstler[i] = new JLabel("Künstler " + i + ":");
					l_kuenstler[i].setForeground(Color.white);
					pan_profiledata.add(l_kuenstler[i], f);
					
					f.gridx = 1;
					f.gridy = i+1;
					f.gridheight = 1;
					f.gridwidth = 1;
					f.weightx = 1.0;
					t_kuenstler[i] = new JTextField();
					t_kuenstler[i].setEditable(false);
					t_kuenstler[i].setBackground(Color.black);
					t_kuenstler[i].setForeground(Color.white);
					t_kuenstler[i].setCaretColor(Color.white);
					pan_profiledata.add(t_kuenstler[i], f);

				}				
				
			// Hinzufügen der Panels zum Panel pan_profileview
			c.gridx = 0;
			c.gridy = 0;
			c.weightx = 0.5;
			c.weighty = 0.5;
			c.gridheight = 2;
			c.gridwidth = 1;
			pan_labelprofileview.add(pan_playlists, c);
			
			c.gridx = 1;
			c.gridy = 0;
			c.weightx = 0.5;
			c.weighty = 0.5;
			c.gridheight = 1;
			c.gridwidth = 1;
			pan_labelprofileview.add(pan_profilepic, c);

			
			c.gridx = 1;
			c.gridy = 1;
			c.weightx = 0.5;
			c.weighty = 1.0;
			c.gridheight = 1;
			c.gridwidth = 1;
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
		JComponent newContentPane = new LabelProfileView(false, false, 4, 2);
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
