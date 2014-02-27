package de.glurak.frontend.mainFrame.header;

import javax.swing.*;
import java.awt.*;

/**
 * Die HeaderView ist dazu da die das oberste Panel anzuzeigen.
 * Dieses enthält unter anderem die Suchefunktion.
 * @author Christopher Distelkämper
 * Date: 26.02.2014
 */
public class HeaderView extends JPanel{
	
	// Textfeld für die Sucheingabe
	private JTextField t_search;
	// Button für die Sucheingabe 
	private JButton b_search;
	// Panel des Headers
	private JPanel pan_header;	
	
	/**
	 * Constructor
	 */
	public HeaderView(){
		
		// Initialisierung des Panels
		pan_header = new JPanel(new GridBagLayout());
		pan_header.setSize(1024,50);
		
		// Layout-Restriktionen festlegen.
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(1,1,2,2);				
				
		// Initialisierung des Textfeldes
		c.weightx = 1.0;
		c.gridx = 0;
		c.gridy = 0;
		t_search = new JTextField();
		t_search.setText("Sucheingabe");
		pan_header.add(t_search, c);
		
		// Initialisierung des Suchbuttons
		c.gridx = 1;
		c.gridy = 0;
		b_search = new JButton("Suchen");
		pan_header.add(b_search, c);
		
		// Hinzufügen des Panels zur ContentPane
		add(pan_header);		
		setVisible(true);
		
	}

	/**
	 * Erzeugt die ProfileView und zeigt sie an.
	 */
	private static void createAndShowView(){
		//Erzeugen des Frames
		JFrame header = new JFrame("Header");
		header.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Registrationview in das Frame laden
		JComponent newContentPane = new HeaderView();
        newContentPane.setOpaque(true);
        header.setContentPane(newContentPane);
        
        //Groesse des Frames festlegen
        header.setPreferredSize(new Dimension(1000, 500));
        //Groesse des Frames soll nicht veraenderbar sein
        header.setResizable(false);
        //Registrationview wird in der Mitte des Bildschirms geladen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        header.setLocation(dim.width/2-header.getSize().width/2-300, dim.height/2-header.getSize().height/2-150);
		
		//Frame anpassen und sichtbar machen
		header.pack();
		header.setVisible(true);
	}
	
	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowView();
            }
        });
	}
}
