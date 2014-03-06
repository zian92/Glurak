package de.glurak.frontend.mainFrame.content.search;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComponent;

import de.glurak.frontend.mainFrame.ContentController;

public class SearchVController implements ActionListener, ContentController {

	private SearchView searchview;
	
	/**
	 * Konstruktor
	 */
	public SearchVController() {
		searchview = new SearchView();
		
		
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public JComponent getView() {
		return searchview;
	}
	
}
