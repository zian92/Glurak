package de.glurak.frontend.mainFrame.header;

import javax.swing.*;

import java.awt.*;

/**
 * Die HeaderView ist dazu da die das oberste Panel anzuzeigen.
 * Dieses enthÃ¤lt unter anderem die Suchefunktion.
 * @author Christopher DistelkÃ¤mper
 * Date: 26.02.2014
 */
public class HeaderView extends JPanel{
	
	// Textfeld fÃ¼r die Sucheingabe
	private JTextField t_search;
	// Button fÃ¼r die Sucheingabe und Logout
	private JButton b_search;
	private JButton b_logout;
	// Panel des Headers und Logout-Buttons
	private JPanel pan_header;
	private JPanel pan_logout;
	
	/**
	 * Constructor
	 */
	public HeaderView(){
		
		setLayout(new GridLayout());
		
		// Initialisierung des Panels
		pan_header = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		// pan_header.setSize(1024,50);
		
		// Initialisierung des Textfeldes
		t_search = new JTextField();
		t_search.setText(" Sucheingabe ");
		t_search.setPreferredSize(new Dimension(200,25));
		// t_search.setBackground(Color.LIGHT_GRAY);
		// t_search.setForeground(Color.blue);
		
		pan_header.add(t_search);
		
		// Initialisierung des Suchbuttons
		b_search = new JButton("Suchen");
		// b_search.setBackground(Color.black);
		// b_search.setForeground(Color.white);
		b_logout = new JButton("Logout");
		
		pan_header.add(b_search);
		
		pan_logout = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pan_logout.add(b_logout);
		
		// HinzufÃ¼gen des Panels zur ContentPane
		add(pan_logout);
		add(pan_header);
		setVisible(true);
		
	}
	
	/**
	 * Getter für SearchField
	 * @return JTextField t_search
	 */
	public JTextField getSearchField() {
		return t_search;
	}
	
	/**
	 * Getter für SearchButton
	 * @return JButton b_search
	 */
	public JButton getSearchButton() {
		return b_search;
	}
	
	/**
	 * Getter für logoutButton
	 * @return JButton b_logout
	 */
	public JButton getLogoutButton() {
		return b_logout;
	}
	
}
