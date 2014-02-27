package de.glurak.frontend.mainFrame.navigation;

import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import de.glurak.frontend.registration.RegistrationView;

/**
 * In der Navigationview werden das Profilbild des Users und seine Funktionen,
 * wie z.B. Profil bearbeiten, und seine Playlists angezeigt.
 * @author Simon
 *
 */
public class NavigationView extends JPanel{

	/**
	 * Konstruktor
	 */
	public NavigationView(){
		
		//Layout des Frames festlegen
		setLayout(new GridLayout());
		
		
	}
	
	/**
	 * Erzeugt die Navigationview und zeigt sie an.
	 */
	private static void createAndShowView(){
		//Erzeugen des Frames
		JFrame navigation = new JFrame("Navigation");
		navigation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Navigationview in das Frame laden
		JComponent newContentPane = new RegistrationView();
		newContentPane.setOpaque(true);
		navigation.setContentPane(newContentPane);
		        
		//Groesse des Frames festlegen
        //navigation.setPreferredSize(new Dimension(600,300));
        //Groesse des Frames soll nicht veraenderbar sein
        //navigation.setResizable(false);
				
		//Frame anpassen und sichtbar machen
		navigation.pack();
		navigation.setVisible(true);
	}
	
	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowView();
            }
        });
	}
	
}
