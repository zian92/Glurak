package de.glurak.frontend.mainFrame;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import de.glurak.Query;
import de.glurak.data.Medium;
import de.glurak.data.Playlist;
import de.glurak.frontend.login.LoginVController;
import de.glurak.frontend.mainFrame.content.news.PromotionVController;
import de.glurak.frontend.mainFrame.content.profile.ProfileEditVController;
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
	private NavigationVController navigationController;
	private ContentController contentController;
	
	/**
	 * Konstruktor
	 */
	public MainFrameVController(){
		// view anlegen
		view = new MainFrameView();
	
		
		// andere Controller laden
		contentController = new PromotionVController();
		PlayQueueViewController.getInstance();
		PlayQueueViewController.getInstance().setMainController(this);
		//PlayQueueViewController.getInstance().refresh(new Playqueue(pl));
		headerController= new HeaderVController();
		navigationController = new NavigationVController(contentController);
		
		// Observer hinzufügen
		navigationController.addObserver(this);
		headerController.addObserver(this);
		PlayQueueViewController.getInstance().addObserver(this);
		((Observable) contentController).addObserver(this);
		
		// Views anhängen
		view.getContent().add( contentController.getView());
		view.getHeader().add(headerController.getView());
		view.getPlayer().add(PlayQueueViewController.getInstance().getView());
		view.getNavigation().add(navigationController.getView());
	}

    /**
     * Diese Funktion wird aufgerufen, falls ein Obersable Objekt updatet
     * @param o das Observable Objekt
     * @param arg der neue ContentController mit den neuen View.
     *            Falls null und der aktuelle Contentcontroller von Typ NextContent ist,
     *            wird sein nächstes Content angezeigt.
     */
    public void update(Observable o, Object arg) {
        view.getContent().removeAll();

        // Woher kommt das Update?
        if (o.equals(headerController)) {
            SearchVController tmp = new SearchVController();
            contentController = tmp;
            tmp.getView().setAllText(headerController.getSearchKey());
            
            // Logout-Button geklickt?
            if (headerController.getLogout()) {
//           	this.playerController.stop();
//            	this.playerController.getView().;
            	this.view.dispose();
    			LoginVController logControll = new LoginVController(Query.APPLICATION_NAME);
    			
            }

        } else if (o.equals(navigationController)){
            contentController = navigationController.getContentController();

        } else if (o.equals(PlayQueueViewController.getInstance())){
            contentController = PlayQueueViewController.getInstance().getContentController();

        } else if (o.equals(contentController)) {
            if (arg != null) {
                contentController=(ContentController) arg;
            }
            else if (contentController instanceof NextContent) {
            	if (contentController instanceof ProfileEditVController) {
            		if (((ProfileEditVController) contentController).isPicChanged()) {
            			view.getNavigation().removeAll();
            			view.getNavigation().repaint();
            			view.getNavigation().revalidate();

            			navigationController = new NavigationVController(new PromotionVController());
            			view.getNavigation().add(navigationController.getView());
            			
            			navigationController.addObserver(this);
            			
            			contentController = new ProfileEditVController(null);
            		}
            		else {
            			contentController = ((NextContent) contentController).getNextContent();
            		}
            	} else {
            		contentController = ((NextContent) contentController).getNextContent();
            	}
            }
            else return;
        }  else return;


        if (contentController instanceof Observable) {
            ((Observable) contentController).addObserver(this);
        }
        view.getContent().add(contentController.getView());
        view.getContent().repaint();
        view.getContent().revalidate();
    }

	public ContentController getContentController() {
		return contentController;
	}
	
}
