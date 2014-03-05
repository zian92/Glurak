package de.glurak.frontend.mainFrame.content.upload;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import de.glurak.feature.Uploader;

/**
 * In der Uploadview kann man Medien auswaehlen und hochladen. 
 * Ausserdem muss man Informationen wie Titel und Album angeben.
 * @author Simon
 *
 */
public class UploadView extends JPanel {

	//Buttons
	protected JButton b_upload;
	protected JButton b_cancel;
	//Textfelder
	protected JTextField t_artist = new JTextField(20);
	protected JTextField t_title = new JTextField(20);
	protected JTextField t_album = new JTextField(20);
	//Uploader
	protected Uploader uploader;
	
	/**
	 * Konstruktor
	 */
	public UploadView(){
		 //Layout festlegen
		setLayout(new BorderLayout());
		
		//Initialiserung der Panels
		JPanel pan_buttons = new JPanel(new FlowLayout());
		JPanel pan_info = new JPanel(new GridLayout(3, 1, 10, 15));
		JPanel pan_upload = new JPanel(new GridLayout(1,1));
		
		//Initialisierung der Labels
		JLabel l_upload = new JLabel("Wählen sie eine Musikdatei aus: ");
		JLabel l_artist = new JLabel("Interpret: ");
		JLabel l_title = new JLabel("Songtitel: ");
		JLabel l_album = new JLabel("Album: ");
		
		//Initialisierung der Buttons
		b_upload = new JButton("Upload");
		b_cancel = new JButton("Abbrechen");
		
		//Uploadpanel zusammenfuegen
		pan_upload.add(l_upload);
		//TODO Datei auswaehlen. Mit dem Uploader?
		
		//Infopanel zusammenfuegen
		pan_info.add(l_artist);
		pan_info.add(t_artist);
		pan_info.add(l_title);
		pan_info.add(t_title);
		pan_info.add(l_album);
		pan_info.add(t_album);
		
		//Buttonpanel zusammenfuegen
		pan_buttons.add(b_upload);
		pan_buttons.add(b_cancel);
		
		//Panels in das Frame einfuegen
		add(pan_upload, BorderLayout.NORTH);
		add(pan_info, BorderLayout.CENTER);
		add(pan_buttons, BorderLayout.SOUTH);
	}
	
	/**
	 * Erzeugt die Uploadview und zeigt sie an.
	 */
	private static void createAndShowView(){
		//Erzeugen des Frames
		JFrame upload = new JFrame("Upload");
		upload.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//UploadView in das Frame laden
		JComponent newContentPane = new UploadView();
        newContentPane.setOpaque(true);
        upload.setContentPane(newContentPane);
        
		//Frame anpassen und sichtbar machen
		upload.pack();
		upload.setVisible(true);
	}
	
	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowView();
            }
        });
	}
	
}
