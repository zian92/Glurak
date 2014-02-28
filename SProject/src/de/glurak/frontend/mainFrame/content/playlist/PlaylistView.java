package de.glurak.frontend.mainFrame.content.playlist;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

/**
 * In der dieser View wird eine erstellte Playlist von einem User angezeigt.
 * Die Playlist besteht aus Musikdateien.
 * @author Simon
 *
 */
public class PlaylistView extends JPanel{

	/**
	 * Konstruktor
	 */
	public PlaylistView(){
		
	}
	
	/**
	 * Erzeugt die Playlistview und zeigt sie an.
	 */
	private static void createAndShowView(){
		//Erzeugen des Frames
		JFrame playlist = new JFrame("Playlist");
		playlist.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		//Registrationview in das Frame laden
		JComponent newContentPane = new PlaylistView();
        newContentPane.setOpaque(true);
        playlist.setContentPane(newContentPane);
				
		//Frame anpassen und sichtbar machen
		playlist.pack();
		playlist.setVisible(true);
	}
	
	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowView();
            }
        });
	}
	
}
