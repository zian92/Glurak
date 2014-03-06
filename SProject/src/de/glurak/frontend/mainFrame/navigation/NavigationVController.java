package de.glurak.frontend.mainFrame.navigation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
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
    private Map<String,ContentController> map;
	
	/**
	 * Konstruktor
	 */
	public NavigationVController(ContentController promoVContr){
        map = new HashMap<String, ContentController>();
		promotionVController = (PromotionVController) promoVContr;
		
		ActionListener a = new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
                String name = e.getActionCommand();
                ContentController c = map.get(name);
                setContentController(c);
			}
		};
        view = new NavigationView(a);
        addController(new ProfileVController(true), "Profil");
        addController(new PlaylistVController(),"Playlist");
        addController(promotionVController,"News");
        addController(new MessageVController(),"Narichten");
        addController(new UploadVController(),"Upload");
	}

    public void addController(ContentController c, String name){
        map.put(name,c);
        view.addButton(name);
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
