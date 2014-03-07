package de.glurak.frontend.mainFrame;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import de.glurak.Query;
import de.glurak.data.Medium;
import de.glurak.data.Playlist;
import de.glurak.data.Playqueue;
import de.glurak.frontend.mainFrame.content.news.PromotionVController;
import de.glurak.frontend.mainFrame.content.search.SearchVController;
import de.glurak.frontend.mainFrame.header.HeaderVController;
import de.glurak.frontend.mainFrame.navigation.NavigationVController;
import de.glurak.frontend.mainFrame.playQueue.PlayQueueViewController;

/**
 * @author Zengo
 *
 */
public class MainFrameVController implements Observer{

	private MainFrameView view;
	private HeaderVController headerController;
	private PlayQueueViewController playerController;
	private NavigationVController navigationController;
	private ContentController contentController;
	
	/**
	 * Konstruktor
	 */
	public MainFrameVController(){
		// view anlegen
		view = new MainFrameView();
		
		
		// test daten für den Player
			LinkedList<Medium> mediumList= new LinkedList<Medium>();
			Playlist pl= new Playlist();
			pl.setMediumList(mediumList);
			Medium m1 = new Medium(1,"Go Far Kid",Query.FOLDER_MUSIC+"Go Far Kid.mp3", null);
			Medium m2 = new Medium(2,"Pokemon Theme",Query.FOLDER_MUSIC+"Pokemon Theme.mp3", null);
			Medium m3 = new Medium(1,"PinkFluffyUnicorns",Query.FOLDER_MUSIC+"Pink Fluffy Unicorns.mp3", null);

			pl.getMediumList().add(m1);
			pl.getMediumList().add(m2);
			pl.getMediumList().add(m3);
				
		
		// andere Controller laden
		contentController = new PromotionVController();
		playerController= new PlayQueueViewController(new Playqueue(pl));
		headerController= new HeaderVController();
		navigationController = new NavigationVController(contentController);
		
		// Observer hinzufügen
		navigationController.addObserver(this);
		headerController.addObserver(this);
		((Observable) contentController).addObserver(this);
		
		// Views anhängen
		view.getContent().add( contentController.getView());
		view.getHeader().add(headerController.getView());
		view.getPlayer().add( playerController.getView());
		view.getNavigation().add(navigationController.getView());
	}


	// View Anpassen
	public void update(Observable o, Object arg) {
		view.getContent().removeAll();
		
		// Woher kommt das Update?
		if (o.equals(headerController)) {
			SearchVController tmp = new SearchVController();
			contentController = tmp;
			tmp.getView().setAllText(headerController.getSearchKey());
			
		} else if (o.equals(navigationController)){
			contentController = navigationController.getContentController();
			
		} else if (o.equals(contentController)) {
			contentController = ((NextContent) contentController).getNextContent();
		}
		
		
		if (contentController instanceof Observable) {
			((Observable) contentController).addObserver(this);
		}
		view.getContent().add(contentController.getView());
		view.getContent().repaint();
		view.getContent().revalidate();
	}
	
}
