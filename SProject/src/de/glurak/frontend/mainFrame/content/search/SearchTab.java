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
import java.util.Observable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Ein Tab in der SearchView
 * @author Entscheider
 * @param <T> Generik der Anzeigedaten
 */
public class SearchTab<T> extends JPanel implements MouseListener {

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
	 * @param sk das Searchable mit den Funktionen und Daten für diese Suche, null falls keine Funktionalität
     * @param name der Name der in Tab angeigt wird
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
		
		t_search.addMouseListener(this);

		ActionListener actionlistener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                search();
            }
        };
		
        b_search.addActionListener(actionlistener);
        t_search.addActionListener(actionlistener);
		
		add(northPane, BorderLayout.NORTH);
        if (sk !=null && sk.getRenderer()!=null)
            searchlist.setCellRenderer(sk.getRenderer());
		add(new JScrollPane(searchlist), BorderLayout.CENTER);
	}

    /**
     * Lässt den Tab mit den Ihnalt des TextEdit suchen
     */
    public void search(){
        searchlist_model.clear();
        if (searchKey==null) return;
        List<T> res = searchKey.searchFor(t_search.getText());
        for (T k: res){
            searchlist_model.addElement(k);
        }
    }

    /**
     * Gibt das selektierte Element zurück
     * @return das selektierte Element
     */
    public T getSelectedItem(){
        T res = ((T) searchlist.getSelectedValue());
        return res;
    }

    public Searchable<T> getSearchable(){
        return this.searchKey;
    }

    public void addMouseListener(MouseListener m){
        searchlist.addMouseListener(m);
    }

    public void removeMouseListener(MouseListener m){
       searchlist.removeMouseListener(m);
    }
	
	public void setSearchText(String searchText) {
		t_search.setText(searchText);
	}
	
	public String getName() {
		return name;
	}

    /**
     * Löscht alles Ergebnisse aus der Liste
     */
    public void clearResult(){
        searchlist_model.clear();
    }

	public void mouseClicked(MouseEvent me) {
		// Bei Klick ins Textfeld wird dessen Inhalt gelöscht
		if (me.getSource() == t_search) {
			t_search.setText("");
		}
		
	}

	public void mouseEntered(MouseEvent arg0) {
		
	}

	public void mouseExited(MouseEvent arg0) {
		
	}

	public void mousePressed(MouseEvent arg0) {
		
	}

	public void mouseReleased(MouseEvent arg0) {
		
	}
}




