package de.glurak.frontend.mainFrame.content.playlist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.SoftBevelBorder;

import de.glurak.data.Medium;
import de.glurak.data.Playlist;
import de.glurak.data.User.ListenerProfile;
import de.glurak.data.User.User;
import de.glurak.feature.SliderPanel;
import de.glurak.frontend.SessionThing;
import de.glurak.frontend.mainFrame.ContentController;
import de.glurak.frontend.mainFrame.NextContent;
import de.glurak.frontend.mainFrame.content.profile.ProfileVController;

public class PlaylistVController extends Observable implements MouseListener, ActionListener, ContentController, NextContent {

    private PlaylistView view;
 //   private PlaylistEditVController contr_edit;
    private ContentController nextContent;
    private Playlist pList;
    private final String[] tableHeader = { "asdf", "sadf", "asdf", };
    
    /**
     * Konstruktor
     */
    public PlaylistVController() {
        view = new PlaylistView(this,this);
        //contr_edit = new PlaylistEditVController();
        
      //  view.setjT(this.fillTable());
        view.setVisible(true);
        initfillView();
    }

    public JComponent getView() {
        return view;
    }

    public Playlist getPlaylist() {
        return pList;
    }

    public void setPlaylist(Playlist pList) {
        this.pList = pList;
    }

    public String[] getTableheader() {
        return tableHeader;
    }
    
	public ContentController getNextContent() {
		return nextContent;
	}
	
	public void setNextContent(ContentController nextContent) {
		this.nextContent = nextContent;
	}
	
	public void initfillView(){
		List<Playlist> list = SessionThing.getInstance().getSessionUser().getMyPlaylists();
		view.fillView(list);
	}
	
	public void refreshView(){
		List<Playlist> list = SessionThing.getInstance().getSessionUser().getMyPlaylists();
		view.refreshView(list);
	}

	  /* ====================================================================================================
     *											 Test-Functions
     * ====================================================================================================
     */
	private Playlist createTestPlaylist(int i){
		// neuer Test Owner
		Playlist p = new Playlist();
		switch (i){
		case 1:
			User u1 = new User();
			u1.setUsername("Horst");
			u1.setProfile(new ListenerProfile());
			// neue Test Medien
			Medium m1 = new Medium( "PokeTheme", "", u1);
			Medium m2 = new Medium( "Pokemon Endingtheme","", u1);
			// neue Playlist

			p.setName("Pokemon");
			p.addMedium(m1);
			p.addMedium(m2);
			break;
		case 2:
			User u2 = new User();
			u2.setUsername("Maya");
			u2.setProfile(new ListenerProfile());
			// neue Test Medien
			Medium m21 = new Medium( "PonySong", "", u2);
			Medium m22 = new Medium( "Stiupid PonySong","", u2);
			// neue Playlist
			p.setName("Pony Tales");
			p.addMedium(m21);
			p.addMedium(m22);
			break;
		default:
			p = null;
			break;
		}


		return p;
	}

    /* ====================================================================================================
     *											 Action-Handling
     * ====================================================================================================
     */
	public void actionPerformed(ActionEvent e) {
	        // TODO Auto-generated method stub
	    	if (e.getActionCommand().equals("nextSlide")){
	    		view.nextPage();
	    		
	    	}else if (e.getActionCommand().equals("prevSlide")){
	    		view.prevPage();
	    		
	    	}else if(e.getActionCommand().equals("newList")){
	    		
	    		Playlist p = createTestPlaylist(0);
	    		    		
	    		nextContent = new PlaylistEditVController(null, this);
				setChanged();
				notifyObservers();
	    		
	    		//view.setPan_Content( (SliderPanel) contr_edit.getEditView(p));
	    		//view.add(contr_edit.getEditView(p));
	    		/*
	    		Playlist p = new Playlist();
	    		p.setName("Pokemon");
	    		SessionThing.getInstance().getDatabase().addPlaylist(p, null);
	    		view.addPlaylist(p); */
	    		
	    	}else if(e.getActionCommand().equals("editList")){
	    		Playlist p = null;
	    		if (Math.random()*100 < 50){
	    			p = createTestPlaylist(1);
	    		}else{
	    			p = createTestPlaylist(2);
	    		}
	    		
	    		//SessionThing.getInstance().getDatabase().addPlaylist(p, null);
	    		view.addPlaylist(p);
	    	}
	    	
	    }
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		PlaylistView.PlaylistLabel  l = (PlaylistView.PlaylistLabel) e.getSource();
		if (e.getClickCount() > 1){
			nextContent = new PlaylistEditVController(l.getPlaylist(), this);
			setChanged();
			notifyObservers();
			/*
			l.setBorder(BorderFactory.createRaisedBevelBorder());
			view.getTextLabel().setText(l.getText() );
			*/
		}else {
			
			//view.getTextLabel().setText("" + l.getPlaylistID());
		}
		
		
	}

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

	public void reload() {
		// TODO Auto-generated method stub
		
	}

}