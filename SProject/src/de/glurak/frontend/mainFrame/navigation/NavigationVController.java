package de.glurak.frontend.mainFrame.navigation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class NavigationVController extends Observable {

	private NavigationView view;
	private String navigateTo;
	/**
	 * Konstruktor
	 */
	public NavigationVController(){
		navigateTo = null;
		
		view = new NavigationView();		
		
		ActionListener a = new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Object src = e.getSource();
				
				if (src == view.getEditProfile()) {
					navigateTo = "EditProfile";
				} else if (src == view.getShowNews()) {
					navigateTo = "ShowNews";
				} else if (src == view.getShowPlaylists()) {
					navigateTo = "ShowPlaylists";
				} else if (src == view.getShowMessages()) {
					navigateTo = "ShowMessages";
				} else if (src == view.getUpload()) {
					navigateTo = "Upload";
				}
			}
		};
		
		view.getEditProfile().addActionListener(a);
		view.getShowNews().addActionListener(a);
		view.getShowPlaylists().addActionListener(a);
		view.getShowMessages().addActionListener(a);
		view.getUpload().addActionListener(a);
	}
	
	
	public String getNavigateTo() {
		return navigateTo;
	}


	public NavigationView getView() {
		return view;
	}

	public void setView(NavigationView view) {
		this.view = view;
	}

}
