package de.glurak.frontend.mainFrame.content.search;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComponent;

import de.glurak.frontend.mainFrame.ContentController;

/**
 * Diese Klasse stellt dem SearchView ihre Funktionalität zur Verfügung.
 * @author Christopher Distelkämper
 * Date: 06.03.2014
 */
public class SearchVController implements  ContentController {

	private SearchView searchview;
	
	/**
	 * Konstruktor
	 */
	public SearchVController() {
		searchview = new SearchView();
		
		
	}


	public SearchView getView() {
		return searchview;
	}
	
	
	/**
	 * Suche
	 * @param genre
	 * @param user
	 * @param music
	 * @param artist
	 * @param playlist
	 */
	public void search(String genre, String user, String music, String artist, String playlist){
		// Suche nach Ergebnissen in der Datenbank
		if (genre != ""){
			
		}
		if (user != ""){
			
		}
		if (music != ""){
			
		}
		if (artist != ""){
			
		}
		if (playlist != ""){
			
		}
	}
}
