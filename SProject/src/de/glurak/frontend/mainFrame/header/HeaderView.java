package de.glurak.frontend.mainFrame.header;

import javax.swing.*;
import java.awt.*;

/**
 * Die HeaderView ist dazu da die das oberste Panel anzuzeigen.
 * Dieses enthält unter anderem die Suchefunktion.
 * @author Christopher Distelkämper
 * Date: 26.02.2014
 */
public class HeaderView extends JFrame{
	
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
		c.gridy = 5;
		t_search = new JTextField();
		t_search.setText("Sucheingabe");
		pan_header.add(t_search, c);
		
		// Initialisierung des Suchbuttons
		c.gridx = 0;
		c.gridy = 6;
		b_search = new JButton("Suchen");
		pan_header.add(b_search, c);
		
		// Beschaffen der ContentPane
		java.awt.Container content = getContentPane();
		
		// Hinzufügen des Panels zur ContentPane
		content.add(pan_header);		
		setVisible(true);
		
	}

}
