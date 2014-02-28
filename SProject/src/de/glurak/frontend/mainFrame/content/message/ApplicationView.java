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
	//Scrolltabelle fuer das Nachrichtenfeld
	private JScrollPane s_application = new JScrollPane(t_application);
	//Buttons
	protected JButton b_send;
	protected JButton b_cancel;
	
	public ApplicationView(){
		
		//Layout des Frames setzen
		setLayout(new BorderLayout());
		
		//Initialisierung der Buttons
		b_send = new JButton("Senden");
		b_cancel = new JButton("Abbrechen");
		
		//Initialisierung der Labels
		JLabel l_receiver = new JLabel("Empfänger: ");
		JLabel l_application = new JLabel("Bewerbung: ");
		
		//Initialisierung der Panels
		JPanel pan_receiver = new JPanel();
		JPanel pan_message = new JPanel();
		JPanel pan_buttons = new JPanel();
		
		//Layouts der Panels festlegen
		pan_receiver.setLayout(new GridLayout(1,0));
		pan_message.setLayout(new BorderLayout());
		pan_buttons.setLayout(new FlowLayout());
		
		//Textfeld anpassen
		t_application.setLineWrap(true);
		t_application.setWrapStyleWord(true);
		
		//Label und Textfeld dem Epmfaengerpanel hinzufuegen
		pan_receiver.add(l_receiver);
		pan_receiver.add(t_receiver);
		
		//Label und Textfeld dem Bewerbungspanel hinzufuegen
		pan_message.add(l_application, BorderLayout.NORTH);
		pan_message.add(s_application);
		
		//Buttons dem Buttonpanel hinzufuegen
		pan_buttons.add(b_send);
		pan_buttons.add(b_cancel);
		
		//Panels in das Frame einfuegen
		add(pan_receiver, BorderLayout.NORTH);
		add(pan_message, BorderLayout.CENTER);
		add(pan_buttons, BorderLayout.SOUTH);
	}
	
	/**
	 * Erzeugt die Applicationview und zeigt sie an.
	 */
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
