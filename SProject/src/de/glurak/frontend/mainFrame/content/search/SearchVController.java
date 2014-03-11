package de.glurak.frontend.mainFrame.content.search;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Observable;

import javax.swing.JComponent;

import de.glurak.frontend.mainFrame.ContentController;
import de.glurak.frontend.mainFrame.NextContent;

/**
 * Diese Klasse stellt dem SearchView ihre Funktionalität zur Verfügung.
 * @author Christopher Distelkämper, Entscheider
 * Date: 06.03.2014
 */
public class SearchVController extends Observable implements  ContentController , NewControllerArrivedListener{

	private SearchView searchview;
	
	/**
	 * Konstruktor
	 */
	public SearchVController() {
		searchview = new SearchView();
		searchview.addNewControllerArrivedListener(this);
	}

	public SearchView getView() {
		return searchview;
	}

    /**
     * Gibt alle Suchtabs zu verstehen zu suchen
     */
    public void searchAll(){
        searchview.searchAll();
    }

    public void gotNewController(ContentController c) {
        this.setChanged();
        this.notifyObservers(c);
    }

	public void reload() {
		searchview.searchAll();
	}
}
