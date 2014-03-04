package de.glurak.frontend.mainFrame.content.message;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Der Kontroller fuer die Messageview.
 * @author Simon
 *
 */
public class MessageVController implements ActionListener{

	private MessageView messview;
	
	/**
	 * Konstruktor
	 */
	public MessageVController(){
		messview = new MessageView();
		messview.b_send.addActionListener(this);
		messview.b_cancel.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == messview.b_send){
			//Abfrage, ob ein Empfaenger eingegeben wurde
			if (messview.t_receiver.getText() == null){
				JOptionPane.showMessageDialog(null, "Sie haben noch keinen Empfänger eingegeben. Bitte fügen sie einen Empfänger hinzu!", "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
			}
			else{
				//Abfrage, ob der Empfaenger existiert
				//TODO Empfaenger mit Datenbankeintraegen vergleichen
				if (messview.t_receiver.getText() == "!!!Platzhalter"){
					JOptionPane.showMessageDialog(null, "Dieser Empfänger existiert nicht. Bitte geben sie einen existierenden Empfänger an!", "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
				}
				else{
					//Abfrage, ob die Nachricht leer ist
					if (messview.t_message.getText() == null){
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
			if(e.getSource() == messview.b_cancel){
				//TODO Das Panel schliessen, im Mainframe zerstoeren
				/**
				 * Was soll passieren wenn man den abbrechen button drueckt
				 */
			}
		}
	}
	
	/**
	 * @return Gibt das Messageviewpanel zurueck.
	 */
	public JPanel getView(){
		return messview;
	}

}
