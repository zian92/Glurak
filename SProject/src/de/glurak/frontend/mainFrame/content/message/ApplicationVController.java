package de.glurak.frontend.mainFrame.content.message;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

/**
 * Der Kontroller fuer die Applicationview.
 * @author Simon
 *
 */
public class ApplicationVController implements ActionListener{
	
	private ApplicationView appliview;

	/**
	 * Konstruktor
	 */
	public ApplicationVController(){
		appliview = new ApplicationView();
		appliview.b_send.addActionListener(this);
		appliview.b_cancel.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == appliview.b_send){
			/**
			 * Was soll passiren wenn man den send Button drueckt
			 */
		}
		else{
			if(e.getSource() == appliview.b_cancel){
				/**
				 * Was soll passieren wenn man den abbrechen button drueckt
				 */
			}
		}
	}
	
	/**
	 * @return Gibt das Applicationviewpanel zurueck 
	 */
	public JPanel getView(){
		return appliview;
	}

}
