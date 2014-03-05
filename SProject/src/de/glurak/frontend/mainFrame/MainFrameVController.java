package de.glurak.frontend.mainFrame;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import de.glurak.Query;
import de.glurak.data.Medium;
import de.glurak.data.Playlist;
import de.glurak.frontend.mainFrame.content.news.PromotionVController;
import de.glurak.frontend.mainFrame.header.HeaderVController;
import de.glurak.frontend.mainFrame.navigation.NavigationVController;
import de.glurak.frontend.mainFrame.playQueue.PlayQueueViewController;

public class MainFrameVController implements Observer{

	private MainFrameView view;
	private HeaderVController headerController;
	private PlayQueueViewController playerController;
	private NavigationVController navigationController;
	private ContentController contentController;
	private PromotionVController promotionController;
	
	
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
			Medium m1 = new Medium(1,"Blah Blah Gay","test.mp3", null);
			Medium m2 = new Medium(2,"POKEMOOOOON","test2.mp3", null);
			pl.getMediumList().add(m1);
			pl.getMediumList().add(m2);
		
		// andere Controller laden
		contentController = new PromotionVController();
		playerController= new PlayQueueViewController(pl);
		headerController= new HeaderVController();
		navigationController = new NavigationVController(contentController);
		
		// Observer hinzufügen
		navigationController.addObserver(this);
		
		// Views anhängen
		view.getContent().add( contentController.getView());
		view.getHeader().add(headerController.getView());
		view.getPlayer().add( playerController.getView());
		view.getNavigation().add(navigationController.getView());
	}


	// View Anpassen
	public void update(Observable o, Object arg) {
		view.getContent().removeAll();
		view.getContent().add(navigationController.getContentController().getView());
		view.getContent().repaint();
		view.getContent().revalidate();
	}
	
}
