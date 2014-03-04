package de.glurak.frontend.mainFrame;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import de.glurak.data.Medium;
import de.glurak.data.Playlist;
<<<<<<< HEAD
import de.glurak.frontend.mainFrame.content.news.SliderPanelController;
=======
import de.glurak.feature.SliderPanelController;
>>>>>>> 9ea001f658c59487256b0b4638a10c9953dbce93
import de.glurak.frontend.mainFrame.header.HeaderVController;
import de.glurak.frontend.mainFrame.navigation.NavigationVController;
import de.glurak.frontend.mainFrame.playQueue.PlayQueueViewController;

public class MainFrameVController implements Observer {

	private MainFrameView view;
	private HeaderVController headerController;
	private PlayQueueViewController playerController;
	private NavigationVController navigationController;
<<<<<<< HEAD
	private ContentController contentController;
	//SliderPanelController con_slider;
	
	
	public MainFrameVController(){
=======
	SliderPanelController con_slider;

	public MainFrameVController() {
>>>>>>> 9ea001f658c59487256b0b4638a10c9953dbce93
		view = new MainFrameView();
		// testcases

		LinkedList<Medium> mediumList = new LinkedList<Medium>();

		Playlist pl = new Playlist();
		pl.setMediumList(mediumList);
		Medium m1 = new Medium(1, "PinkFluffyUniconrs", "test2.mp3", null);
		Medium m2 = new Medium(2, "Blah", "test.mp3", null);
		pl.getMediumList().add(m1);
		pl.getMediumList().add(m2);
<<<<<<< HEAD
		
		contentController = new SliderPanelController();
		
		playerController= new PlayQueueViewController(pl);
		headerController= new HeaderVController();
		navigationController = new NavigationVController();
		
		view.getContent().add( contentController.getView());
=======

		con_slider = new SliderPanelController();

		playerController = new PlayQueueViewController(pl);
		headerController = new HeaderVController();
		navigationController = new NavigationVController();

		view.getContent().add(con_slider.getView());
>>>>>>> 9ea001f658c59487256b0b4638a10c9953dbce93
		view.getHeader().add(headerController.getView());
		view.getPlayer().add(playerController.getView());
		view.getNavigation().add(navigationController.getView());
	}

	// View Anpassen
	public void update(Observable o, Object arg) {
		System.out.println("UPADTE");
	}

}
