package de.glurak.frontend.mainFrame.content.search;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
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
	
	private List<SearchTab> tabs = new ArrayList<SearchTab>();
	
	public SearchView(){
		
		setLayout(new BorderLayout());
		tabs.add(new SearchTab(null, "Genre"));
		tabs.add(new SearchTab(null, "Musik"));
		tabs.add(new SearchTab(null, "User"));
		tabs.add(new SearchTab(null, "Playlist"));
		tabs.add(new SearchTab(null, "Label"));
		
		JTabbedPane pane = new JTabbedPane();
		add(pane, BorderLayout.CENTER);
		
		for (SearchTab s: tabs) {
			pane.addTab(s.getName(), s);
		}
		
	}
	
	public void setAllText(String text) {
		for (SearchTab s : tabs) {
			s.setSearchText(text);
		}
	}
	
}
