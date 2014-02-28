package de.glurak.frontend.mainFrame.content.search;

import java.awt.BorderLayout;
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
		
		//Layout des Frames festlegen
		setLayout(new BorderLayout());
		
		//Der Tabelle Spalten hinzufuegen
		searchtable_model.addColumn("Titel");
		searchtable_model.addColumn("Zeit");
		searchtable_model.addColumn("Interpret");
		searchtable_model.addColumn("Album");
		
		//Spaltengroesse anpassen
		searchtable.getColumnModel().getColumn(0).setPreferredWidth(600);
		searchtable.getColumnModel().getColumn(1).setPreferredWidth(100);
		searchtable.getColumnModel().getColumn(2).setPreferredWidth(250);
		searchtable.getColumnModel().getColumn(3).setPreferredWidth(400);
		
		//Initialisierung von Labels
		JLabel l_results = new JLabel("Ergebnisse: ");
		
		//Einfuegen der ScrollTabelle in das Frame
		add(l_results, BorderLayout.NORTH);
		add(scroll_searchtable, BorderLayout.CENTER);
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
        //search.setPreferredSize(new Dimension(800,400));
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
