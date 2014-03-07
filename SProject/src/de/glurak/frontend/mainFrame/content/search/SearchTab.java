package de.glurak.frontend.mainFrame.content.search;

import de.glurak.frontend.mainFrame.ContentController;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

/**
 * @author Entscheider
 * @param <T> Generik der Anzeigedaten
 */
public class SearchTab<T> extends JPanel {

	private JPanel pan_searchview;
	private JPanel pan_advancedSearch;
	private JPanel pan_results;

	//Liste in der die Ergebnisse geladen werden
	private DefaultListModel<T> searchlist_model = new DefaultListModel<T>();
	private JList searchlist= new JList(searchlist_model);

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
	public SearchTab(Searchable<T> sk, String name) {
		
		this.name = name;
		this.searchKey = sk;
		
		setLayout(new BorderLayout());
		JPanel northPane = new JPanel();
		northPane.setLayout(new FlowLayout());
		northPane.add(new JLabel("Suche"));
		northPane.add(t_search);
		northPane.add(b_search);

        b_search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchlist_model.clear();
                List<T> res = searchKey.searchFor(t_search.getText());
                for (T k: res){
                    searchlist_model.addElement(k);
                }
            }
        });
		
		add(northPane, BorderLayout.NORTH);
        if (sk.getRenderer()!=null)
            searchlist.setCellRenderer(sk.getRenderer());


        searchlist.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==2){
                    T val = (T) searchlist.getSelectedValue();
                    ContentController c = searchKey.getChangeController(val);
                    //TODO ContentController c anzeigen
                }
            }
            public void mousePressed(MouseEvent e) {

            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {

            }
        });
		add(new JScrollPane(searchlist), BorderLayout.CENTER);
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




