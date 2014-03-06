package de.glurak.frontend.mainFrame.header;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;

import javax.swing.JComponent;

import de.glurak.frontend.mainFrame.content.search.SearchVController;

public class HeaderVController extends Observable implements ActionListener, MouseListener{

	private HeaderView headview;
	// Der Suchbegriff, der in die Suchmaske des Headers eingegeben wird
	private String searchKey;
	/**
	 * Konstruktor
	 */
	public HeaderVController(){
		setHeadview(new HeaderView());
		headview.getSearchField().addMouseListener(this);
		headview.getSearchField().addActionListener(this);
		headview.getSearchButton().addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		// JButton für Suche oder Enter-Taste betätigt?
		if (e.getSource() == headview.getSearchButton() || 
				e.getSource() == headview.getSearchField()) {
			setChanged();
			this.searchKey = headview.getSearchField().getText();
			notifyObservers();
		}
		
	}

	public JComponent getView() {
		return headview;
	}
	
	public String getSearchKey() {
		return searchKey;
	}
	
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public void setHeadview(HeaderView headview) {
		this.headview = headview;
	}
	
	
	// Bei Mausklick wird default-Inhalt des Suchfeldes gelöscht.
	public void mouseClicked(MouseEvent me) {
		if (me.getSource() == headview.getSearchField()) {
			headview.getSearchField().setText("");
		}
		
	}
	
	// nicht benötigt
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
