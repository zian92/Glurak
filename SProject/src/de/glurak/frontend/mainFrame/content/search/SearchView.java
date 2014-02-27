package de.glurak.frontend.mainFrame.content.search;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

/**
 * In der Searchview werden die Ergebnisse von einer Suche angezeigt.
 * @author Simon
 *
 */
public class SearchView extends JPanel{

	//Tabelle in der die Ergebnisse geladen werden
	private DefaultTableModel searchtable_model = new DefaultTableModel();
	private JTable searchtable = new JTable(searchtable_model);
	//Scrollpanel in das die Tabelle geladen wird
	private JScrollPane scroll_searchtable = new JScrollPane(searchtable);
	
	/**
	 * Konstruktor
	 */
	public SearchView(){
		
		//Einfuegen der ScrollTabelle in das Frame
		add(scroll_searchtable);
	}
	
	/**
	 * Erzeugt die Searchview und  zeigt sie an.
	 */
	private static void createAndShowView(){
		//Erzeugen des Frames
		JFrame search = new JFrame("Registrierung");
		search.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		//Registrationview in das Frame laden
		JComponent newContentPane = new SearchView();
		newContentPane.setOpaque(true);
		search.setContentPane(newContentPane);
		        
		//Groesse des Frames festlegen
        //search.setPreferredSize(new Dimension(600,300));
        //Groesse des Frames soll nicht veraenderbar sein
        //search.setResizable(false);
			
		//Frame anpassen und sichtbar machen
		search.pack();
		search.setVisible(true);
	}
	
	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowView();
            }
        });
	}
	
}
