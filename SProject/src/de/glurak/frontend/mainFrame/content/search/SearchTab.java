package de.glurak.frontend.mainFrame.content.search;

import java.util.List;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SearchTab extends JPanel {

	private JPanel pan_searchview;
	private JPanel pan_advancedSearch;
	private JPanel pan_results;

	//Tabelle in der die Ergebnisse geladen werden
	private DefaultTableModel searchtable_model = new DefaultTableModel();
	private JTable searchtable = new JTable(searchtable_model);
	//Scrollpanel in das die Tabelle geladen wird
	private JScrollPane scroll_searchtable = new JScrollPane(searchtable);

	// Suchfelde
	protected JTextField t_search = new JTextField(10);

	// Button zur Suche
	protected JButton b_search = new JButton("Suchen!");
	
	private Searchable searchKey;
	private String name;
	
	/**
	 * Konstruktor
	 * @param sk
	 */
	public SearchTab(Searchable sk, String name) {
		
		this.name = name;
		this.searchKey = sk;
		
		setLayout(new BorderLayout());
		JPanel northPane = new JPanel();
		northPane.setLayout(new FlowLayout());
		northPane.add(new JLabel("Suche"));
		northPane.add(t_search);
		northPane.add(b_search);

        b_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> res = searchKey.searchFor(t_search.getText());
                for (String k: res){
                    String[] data = new String[4];
                    data[0]=k;
                    searchtable_model.addRow(data);
                }
            }
        });
		
		add(northPane, BorderLayout.NORTH);
		

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
		
		add(new JScrollPane(searchtable), BorderLayout.CENTER);
	}
	
	public void setSearchText(String searchText) {
		t_search.setText(searchText);
	}
	
	public String getName() {
		return name;
	}
	
	/* public static void main(String [] args) {
		
		SearchTab t = new SearchTab(null);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(600, 400));
		frame.setContentPane(t);
		frame.pack();
		frame.setVisible(true);
		
		
	} */
}




