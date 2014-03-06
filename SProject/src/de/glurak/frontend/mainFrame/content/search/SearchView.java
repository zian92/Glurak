package de.glurak.frontend.mainFrame.content.search;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.*;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

/**
 * Die SearchView ermöglicht die Erweiterte-Suche und die Ergebnisse von 
 * einer Suche werden hier angezeigt.
 * @author Simon, Christopher Distelkämper
 *
 */
public class SearchView extends JPanel{
	
	// Panels
	private JPanel pan_searchview;
	private JPanel pan_advancedSearch;
	private JPanel pan_results;
	
	//Tabelle in der die Ergebnisse geladen werden
	private DefaultTableModel searchtable_model = new DefaultTableModel();
	private JTable searchtable = new JTable(searchtable_model);
	//Scrollpanel in das die Tabelle geladen wird
	private JScrollPane scroll_searchtable = new JScrollPane(searchtable);
	
	// Suchfelder
	protected JTextField t_genreSearch;
	protected JTextField t_userSearch;
	protected JTextField t_artistSearch;
	protected JTextField t_playlistSearch;
	
	// Label für Suchfelder
	private JLabel l_advancedSearch;
	private JLabel l_genreSearch;
	private JLabel l_userSearch;
	private JLabel l_artistSearch;
	private JLabel l_playlistSearch;
	
	// Button zur Suche
	protected JButton b_search;
	
	/**
	 * Konstruktor
	 */
	public SearchView(){
		
		// Initialisieren der Panels:
		pan_searchview = new JPanel(new GridBagLayout());
		
			// Layout-Restriktionen festlegen.
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(2,2,2,2);	
			
		pan_advancedSearch = new JPanel(new GridBagLayout());
		
			// Layout-Restriktionen festlegen.
			GridBagConstraints d = new GridBagConstraints();
			d.fill = GridBagConstraints.HORIZONTAL;
			d.insets = new Insets(2,2,2,2);	
			
		pan_results = new JPanel(new BorderLayout());
		
		//Layout des Frames festlegen
	//	setLayout(new BorderLayout());
		
		// Suchfeld:
		// Labels hinzufügen.
		d.gridx = 0;
		d.gridy = 0;
		d.gridheight = 1;
		d.gridwidth = 2;
		d.weightx = 0.0;
		l_advancedSearch = new JLabel("Erweiterte Suche");
		pan_advancedSearch.add(l_advancedSearch, d);
		
		d.gridx = 0;
		d.gridy = 1;
		d.gridheight = 1;
		d.gridwidth = 1;
		d.weightx = 0.0;
		l_genreSearch = new JLabel("Suche nach Genre:");
		pan_advancedSearch.add(l_genreSearch, d);
		
		d.gridx = 0;
		d.gridy = 2;
		d.gridheight = 1;
		d.gridwidth = 1;
		d.weightx = 0.0;
		l_userSearch = new JLabel("Suche nach User:");
		pan_advancedSearch.add(l_userSearch, d);
		
		d.gridx = 0;
		d.gridy = 3;
		d.gridheight = 1;
		d.gridwidth = 1;
		d.weightx = 0.0;
		l_artistSearch = new JLabel("Suche nach Künstler");
		pan_advancedSearch.add(l_artistSearch, d);
		
		d.gridx = 0;
		d.gridy = 4;
		d.gridheight = 1;
		d.gridwidth = 1;
		d.weightx = 0.0;
		l_playlistSearch = new JLabel("Suche nach Playlist");
		pan_advancedSearch.add(l_playlistSearch, d);
		
		// Textfelder hinzufügen.
		d.gridx = 1;
		d.gridy = 1;
		d.gridheight = 1;
		d.gridwidth = 1;
		d.weightx = 1.0;
		t_genreSearch = new JTextField();
		pan_advancedSearch.add(t_genreSearch, d);
		
		d.gridx = 1;
		d.gridy = 2;
		d.gridheight = 1;
		d.gridwidth = 1;
		d.weightx = 1.0;
		t_userSearch = new JTextField();
		pan_advancedSearch.add(t_userSearch, d);
		
		d.gridx = 1;
		d.gridy = 3;
		d.gridheight = 1;
		d.gridwidth = 1;
		d.weightx = 1.0;
		t_artistSearch = new JTextField();
		pan_advancedSearch.add(t_artistSearch, d);
		
		d.gridx = 1;
		d.gridy = 4;
		d.gridheight = 1;
		d.gridwidth = 1;
		d.weightx = 1.0;
		t_playlistSearch = new JTextField();
		pan_advancedSearch.add(t_playlistSearch, d);
		
		// Button hinzufügen
		d.gridx = 0;
		d.gridy = 5;
		d.gridheight = 1;
		d.gridwidth = 2;
		d.weightx = 1.0;
		b_search = new JButton("suchen");
		pan_advancedSearch.add(b_search, d);
		
		
		// Ergebnisanzeige:
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
		
		//Einfuegen der ScrollTabelle in das Result-Panel
		pan_results.add(l_results, BorderLayout.NORTH);
		pan_results.add(scroll_searchtable, BorderLayout.CENTER);
		
		// Einfügen in das pan_profileview
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		pan_searchview.add(pan_advancedSearch, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		pan_searchview.add(pan_results, c);
		
		add(pan_searchview);
		setVisible(true);
	}
	
	/**
	 * Erzeugt die Searchview und  zeigt sie an.
	 */
	private static void createAndShowView(){
		//Erzeugen des Frames
		JFrame search = new JFrame("Erweiterte Suche");
		search.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		//Registrationview in das Frame laden
		JComponent newContentPane = new SearchView();
		newContentPane.setOpaque(true);
		search.setContentPane(newContentPane);
		        
		//Groesse des Frames festlegen
        search.setPreferredSize(new Dimension(800,400));
        //Groesse des Frames soll nicht veraenderbar sein
        search.setResizable(false);
			
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
