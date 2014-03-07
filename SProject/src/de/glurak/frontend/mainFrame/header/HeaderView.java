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
		pan_header = new JPanel(new FlowLayout(FlowLayout.LEFT));
		// pan_header.setSize(1024,50);
		
		// Initialisierung des Textfeldes
		t_search = new JTextField();
		t_search.setText(" Sucheingabe ");
		t_search.setPreferredSize(new Dimension(85,20));
		// t_search.setBackground(Color.LIGHT_GRAY);
		// t_search.setForeground(Color.blue);
		
		pan_header.add(t_search);
		
		// Initialisierung des Suchbuttons
		b_search = new JButton("Suchen");
		// b_search.setBackground(Color.black);
		// b_search.setForeground(Color.white);
		b_logout = new JButton("Logout");
		
		pan_header.add(b_search);
		
		pan_logout = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pan_logout.add(b_logout);
		
		// HinzufÃ¼gen des Panels zur ContentPane
		add(pan_header);
		add(pan_logout);
		setVisible(true);
		
	}
	
	public JTextField getSearchField() {
		return t_search;
	}
	
	public JButton getSearchButton() {
		return b_search;
	}
	
	public JButton getLogoutButton() {
		return b_logout;
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
