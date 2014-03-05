package de.glurak.frontend.mainFrame.navigation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import de.glurak.frontend.mainFrame.ContentController;
import de.glurak.frontend.mainFrame.content.message.MessageVController;
import de.glurak.frontend.mainFrame.content.news.PromotionVController;
import de.glurak.frontend.mainFrame.content.playlist.PlaylistVController;
import de.glurak.frontend.mainFrame.content.profile.ProfileVController;
import de.glurak.frontend.mainFrame.content.upload.UploadVController;

public class NavigationVController extends Observable {

	private NavigationView view;
	private ContentController contentController;
	private PromotionVController promotionVController;
	
	/**
	 * Konstruktor
	 */
	public NavigationVController(ContentController promoVContr){
		view = new NavigationView();
		promotionVController = (PromotionVController) promoVContr;
		
		ActionListener a = new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Object src = e.getSource();
				
				if (src == view.getEditProfile()) {
					setContentController(new ProfileVController(true));		
				} else if (src == view.getShowNews()) {
					setContentController(promotionVController);		
				} else if (src == view.getShowPlaylists()) {
					setContentController(new PlaylistVController());	
				} else if (src == view.getShowMessages()) {
					setContentController(new MessageVController());
				} else if (src == view.getUpload()) {
					setContentController(new UploadVController());
				}
			}
		};
		
		view.getEditProfile().addActionListener(a);
		view.getShowNews().addActionListener(a);
		view.getShowPlaylists().addActionListener(a);
		view.getShowMessages().addActionListener(a);
		view.getUpload().addActionListener(a);
	}

	public NavigationView getView() {
		return view;
	}

	public void setView(NavigationView view) {
		this.view = view;
	}


	public ContentController getContentController() {
		return contentController;
	}


	public void setContentController(ContentController contentController) {
		this.contentController = contentController;
		setChanged();
		notifyObservers();
	}

}
