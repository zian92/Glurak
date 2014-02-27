package de.glurak.frontend.mainFrame.content.message;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;


/**
 * ApplicationView zeigt das Fenster an, wenn man als Kuenstler
 * eine Bewerbung an ein Label senden moechte.
 * @author Simon
 *
 */
public class ApplicationView extends JPanel{
	
	//Textfelder
	private JTextField t_receiver = new JTextField();
	private JTextArea t_application = new JTextArea();
	//Scrolltabelle für das Nachrichtenfeld
	private JScrollPane s_application = new JScrollPane(t_application);
	//Buttons
	private JButton b_send;
	private JButton b_cancel;
	
	public ApplicationView(){
		
	}
	
	private static void createAndShowView(){
		//Erzeugen des Frames
		JFrame f_application = new JFrame("Nachricht schreiben");
		f_application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		//Registrationview in das Frame laden
		JComponent newContentPane = new ApplicationView();
		newContentPane.setOpaque(true);
		f_application.setContentPane(newContentPane);
				
		//Groesse des Frames festlegen
		f_application.setPreferredSize(new Dimension(400,300));
		//Groesse des Frames soll nicht veraenderbar sein
		f_application.setResizable(false);
				
		//Frame anpassen und sichtbar machen
		f_application.pack();
		f_application.setVisible(true);
	}
	
	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowView();
            }
        });
	}

}
