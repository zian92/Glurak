package de.glurak.frontend.mainFrame.header;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

import de.glurak.frontend.mainFrame.content.search.SearchVController;

public class HeaderVController implements ActionListener, MouseListener{

	private HeaderView headview;
	
	/**
	 * Konstruktor
	 */
	public HeaderVController(){
		setHeadview(new HeaderView());
		headview.getSearchField().addMouseListener(this);
		headview.getSearchButton().addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == headview.getSearchButton()) {
			SearchVController searchController = new SearchVController();
		}
		
	}

	public JComponent getView() {
		return headview;
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
