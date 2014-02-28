package de.glurak.frontend.mainFrame.header;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

public class HeaderVController implements ActionListener{

	private HeaderView headview;
	
	/**
	 * Konstruktor
	 */
	public HeaderVController(){
		setHeadview(new HeaderView());
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public JComponent getView() {
		return headview;
	}

	public void setHeadview(HeaderView headview) {
		this.headview = headview;
	}

}
