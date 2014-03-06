package de.glurak.frontend.mainFrame.content.search;

import de.glurak.frontend.mainFrame.content.search.Searches.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

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
		tabs.add(new SearchTab(new GenreSearch(), "Genre"));
		tabs.add(new SearchTab(new MusicSearch(), "Musik"));
		tabs.add(new SearchTab(new UserSearch(), "User"));
		tabs.add(new SearchTab(new PlaylistSearch(), "Playlist"));
		tabs.add(new SearchTab(new LabelSearch(), "Label"));
		
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
