package de.glurak.frontend.mainFrame.content.search;

import de.glurak.frontend.mainFrame.ContentController;
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

    private List<NewControllerArrivedListener> new_controller_listener= new ArrayList<NewControllerArrivedListener>();

    public void addNewControllerArrivedListener(NewControllerArrivedListener ar){
        new_controller_listener.add(ar);
    }

    public void removeNewControllerArrivedListener(NewControllerArrivedListener ar){
        new_controller_listener.remove(ar);
    }

    protected void notifyNewControllerArrivedListener(ContentController nController){
       for (NewControllerArrivedListener a: new_controller_listener){
           a.gotNewController(nController);
       }
    }
	
	public SearchView(){
		
		setLayout(new BorderLayout());
		tabs.add(new SearchTab(new GenreSearch(), "Genre",this));
		tabs.add(new SearchTab(new MusicSearch(), "Musik",this));
		tabs.add(new SearchTab(new UserSearch(), "User",this));
		tabs.add(new SearchTab(new PlaylistSearch(), "Playlist",this));
		tabs.add(new SearchTab(new LabelSearch(), "Label",this));
		
		JTabbedPane pane = new JTabbedPane();
		add(pane, BorderLayout.CENTER);
		
		for (SearchTab s: tabs) {
			pane.addTab(s.getName(), s);
		}
		
	}

    /**
     * Setzt bei jeden SearchTab den Suchtext
     * @param text der neue Suchtext
     */
	public void setAllText(String text) {
		for (SearchTab s : tabs) {
			s.setSearchText(text);
		}
	}
	
}
