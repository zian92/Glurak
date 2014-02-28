package de.glurak.frontend.mainFrame.content.message;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
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
			/**
			 * Was soll passiren wenn man den send Button drueckt
			 */
		}
		else{
			if(e.getSource() == messview.b_cancel){
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
