package de.glurak.frontend.mainFrame;

import java.util.LinkedList;

import de.glurak.data.Medium;
import de.glurak.data.Playlist;
import de.glurak.feature.SliderPanelController;
import de.glurak.frontend.login.PromotionPanel;
import de.glurak.frontend.mainFrame.header.HeaderVController;
import de.glurak.frontend.mainFrame.playQueue.PlayQueueViewController;

public class MainFrameVController {

	private MainFrameView view;
	private HeaderVController headerController;
	private PlayQueueViewController playerController;
	SliderPanelController con_slider;
	
	
	public MainFrameVController(){
		view = new MainFrameView();
		// testcases
	
		LinkedList<Medium> mediumList= new LinkedList<Medium>();

		Playlist pl= new Playlist();
		pl.setMediumList(mediumList);
		Medium m1 = new Medium(1,"PinkFluffyUniconrs","test2.mp3", null);
		Medium m2 = new Medium(2,"Blah","test.mp3", null);
		pl.getMediumList().add(m1);
		pl.getMediumList().add(m2);
		
		con_slider = new SliderPanelController();
		
		playerController= new PlayQueueViewController(pl);
		headerController= new HeaderVController();
		
		view.getContent().add(con_slider.getView());
		view.getHeader().add(headerController.getView());
		view.getPlayer().add( playerController.getView());
	}
	
}
