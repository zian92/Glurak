package de.glurak.frontend.mainFrame.content.message;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
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
			//Abfrage, ob ein Empfaenger eingegeben wurde
			if (appliview.t_receiver.getText() == null){
				JOptionPane.showMessageDialog(null, "Sie haben noch keinen Empfänger eingegeben. Bitte fügen sie einen Empfänger hinzu!", "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
			}
			else{
				//Abfrage, ob der Empfaenger existiert
				//TODO Empfaenger mit Datenbankeintraegen vergleichen
				if (appliview.t_receiver.getText() == "!!!Platzhalter"){
					JOptionPane.showMessageDialog(null, "Dieser Empfänger existiert nicht. Bitte geben sie einen existierenden Empfänger an!", "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
				}
				else{
					//Abfrage, ob die Nachricht leer ist
					if (appliview.t_application.getText() == null){
						JOptionPane.showMessageDialog(null, "Sie haben keine Nachricht eingegeben. Bitte schreiben sie zuerst ihre Nachricht!", "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
					}
					else{
						//TODO Nachricht an den Empfaenger schicken
						/**
						 * Was soll passieren wenn man den send Button drueckt
						 */
					}
				}
			}
		}
		else{
			if(e.getSource() == appliview.b_cancel){
				//TODO Das Panel schliessen, im Mainframe zerstoeren
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
