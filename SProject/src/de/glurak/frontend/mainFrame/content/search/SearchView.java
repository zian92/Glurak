package de.glurak.frontend.mainFrame.content.search;

import de.glurak.frontend.mainFrame.ContentController;
import de.glurak.frontend.mainFrame.content.search.Searches.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

    private JTabbedPane pane;
	
	public SearchView(){
		
		setLayout(new BorderLayout());
        pane = new JTabbedPane();
        add(pane, BorderLayout.CENTER);

        addEntry(new SearchTab(new GenreSearch(), "Genre"));
        addEntry(new SearchTab(new MusicSearch(), "Musik"));
        addEntry(new SearchTab(new UserSearch(), "User"));
        addEntry(new SearchTab(new PlaylistSearch(), "Playlist"));
        addEntry(new SearchTab(new LabelSearch(), "Label"));
		
	}

    private class TabMouseAdapter<T> extends MouseAdapter{
        private SearchTab<T> myTab;
        public TabMouseAdapter(SearchTab<T> tab){
            myTab=tab;
        }

        @Override
        public void mouseClicked(MouseEvent e){
            if (e.getClickCount()==2 && myTab.getSearchable()!=null){
                T val = myTab.getSelectedItem();
                if (val == null) return;
                myTab.getSearchable().otherDoubleClickAction(val);
                //Controller wechseln falls nötig
                ContentController c = myTab.getSearchable().getChangeController(val);
                if (c!=null)
                    notifyNewControllerArrivedListener(c);
            }
        }

    }

    public <T> void addEntry(SearchTab<T> s){
       tabs.add(s);
       pane.add(s);
       s.addMouseListener(new TabMouseAdapter<T>(s));
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
