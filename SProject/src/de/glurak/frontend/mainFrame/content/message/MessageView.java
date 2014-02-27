package de.glurak.frontend.mainFrame.content.message;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 * MessageView zeigt das Fenster an, wenn man einen anderen User 
 * des Systems eine Nachricht schreiben moechte.
 * @author Simon
 *
 */
public class MessageView extends JPanel{

	//Textfelder
	private JTextField t_receiver = new JTextField();
	private JTextArea t_message = new JTextArea();
	//Scrolltabelle fuer das Nachrichtenfeld
	private JScrollPane s_message = new JScrollPane(t_message);
	//Buttons
	private JButton b_send;
	private JButton b_cancel;
	
	/**
	 * Konstruktor
	 */
	public MessageView(){
		
		//Layout des Frames festlegen
		setLayout(new BorderLayout());
		
		//Initialisierung der Buttons
		b_send = new JButton("Senden");
		b_cancel = new JButton("Abbrechen");
		
		//Initialisierung der Labels
		JLabel l_receiver = new JLabel("Empf�nger: ");
		JLabel l_message = new JLabel("Nachricht: ");
		
		//Initialisierung der Panels
		JPanel pan_receiver = new JPanel();
		JPanel pan_message = new JPanel();
		JPanel pan_buttons = new JPanel();
		
		//Layout der Panels festlegen
		pan_receiver.setLayout(new GridLayout(1, 0));
		pan_message.setLayout(new BorderLayout());
		pan_buttons.setLayout(new FlowLayout());
		
		//Buttons in das Buttonpanel hinzufuegen
		pan_buttons.add(b_send);
		pan_buttons.add(b_cancel);
		
		//Label und Textfeld in das Empfaengerpanel hinzufuegen
		pan_receiver.add(l_receiver);
		pan_receiver.add(t_receiver);
		
		//Textfeld anpassen
		t_message.setLineWrap(true);
		t_message.setWrapStyleWord(true);
		
		//Label und Textfeld in das Nachrichtenpanel hinzufuegen
		pan_message.add(l_message, BorderLayout.NORTH);
		pan_message.add(s_message, BorderLayout.CENTER);
		
		//Panels in das Frame einfuegen
		add(pan_receiver, BorderLayout.NORTH);
		add(pan_message, BorderLayout.CENTER);
		add(pan_buttons, BorderLayout.SOUTH);
	}
	
	/**
	 * Erzeugt die MessageView und zeigt sie an.
	 */
	private static void createAndShowView(){
		//Erzeugen des Frames
		JFrame f_message = new JFrame("Nachricht schreiben");
		f_message.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Registrationview in das Frame laden
		JComponent newContentPane = new MessageView();
		newContentPane.setOpaque(true);
		f_message.setContentPane(newContentPane);
		
		//Groesse des Frames festlegen
		f_message.setPreferredSize(new Dimension(400,300));
		//Groesse des Frames soll nicht veraenderbar sein
		f_message.setResizable(false);
		
		//Frame anpassen und sichtbar machen
		f_message.pack();
		f_message.setVisible(true);
	}
	
	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowView();
            }
        });
	}
	
}
