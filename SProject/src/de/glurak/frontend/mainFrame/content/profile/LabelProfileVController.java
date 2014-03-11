package de.glurak.frontend.mainFrame.content.profile;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import javax.swing.*;

import de.glurak.data.Album;
import de.glurak.data.Playlist;
import de.glurak.data.User.ArtistProfile;
import de.glurak.data.User.Label;
import de.glurak.data.User.LabelManagerProfile;
import de.glurak.data.User.User;
import de.glurak.frontend.SessionThing;
import de.glurak.frontend.mainFrame.ContentController;
import de.glurak.frontend.mainFrame.NextContent;
import de.glurak.frontend.mainFrame.content.message.ApplicationVController;
import de.glurak.frontend.mainFrame.content.playlist.AlbumVController;
import de.glurak.frontend.mainFrame.content.playlist.PlaylistEditVController;
import de.glurak.frontend.mainFrame.content.playlist.PlaylistVController;
import de.glurak.frontend.mainFrame.content.profile.ProfileEditVController;

/**
 * Diese Klasse stellt dem LabelProfileView die Funktionalität zur Verfügung.
 * @author Christopher Distelkämper
 * Date: 28.02.2014
 */
//TODO ÜBERARBEITEN
public class LabelProfileVController extends Observable implements ContentController, ActionListener, NextContent{

    private LabelProfileView view;
    private Label label;
    private ContentController nextContent;

    /**
     *
     * @param l
     */
    public LabelProfileVController(Label label){
    	this.label = label;
    	boolean edit = false;
    	
    	// Überprüfen ob aktueller user LabelManager des Labels ist
    	for (LabelManagerProfile m: label.getManager()) {
    		System.out.println(m.getFirstname());
    		if (SessionThing.getInstance().getSessionUser().equals(m.belongTo())) {
    			edit = true;
    		}
    	}
    	
        view = new LabelProfileView(label, getTop5Albums(), getTop5Artists(), edit);
        view.b_application.addActionListener(this);
        
        for (int i=0;i<view.getB_artistArray().length; i++) {
        	view.getB_artistArray()[i].addActionListener(this);
		}
    }

    public void actionPerformed(ActionEvent e){
        Object obj = e.getSource();

        if (obj == view.b_edit){
        	nextContent = new LabelProfileVController(this.label);
			setChanged();
			notifyObservers();
        } else if (obj == view.b_application){
            // TODO nur ausführen, wenn artist
            setChanged();
            notifyObservers(new ApplicationVController(this.label));
            
//        } else if (obj == view.b_apply){
            //  	setContentController(new ApplicationVController());
        } else {
        	for (int i=0;i<view.getB_artistArray().length; i++) {
				if (obj == view.getB_artistArray()[i]) {
					nextContent = new ProfileVController(getTop5Artists().get(i).belongTo());
					setChanged();
					notifyObservers();
				}
			}
        	
        	for (int i=0;i<view.getB_playlistArray().length; i++) {
				if (obj == view.getB_playlistArray()[i]) {
					nextContent = new AlbumVController((Album) getTop5Albums().get(i),this);
					setChanged();
					notifyObservers();
				}
			}
        }
    }
    
    
    public List<Playlist> getTop5Albums() {
		List<Playlist> myAlbums = this.label.getMyPlaylists();
		
		Collections.sort(myAlbums);
		
		List<Playlist> returnList = new ArrayList<Playlist>();
		
		for (int i=0; i<myAlbums.size(); i++) {
			if (myAlbums.get(i)!= null) {
				returnList.add(myAlbums.get(i));
			}
		}
		
		return returnList;
    }
    
    public List<ArtistProfile> getTop5Artists() {
		List<ArtistProfile> myArtists = this.label.getProfile().getMyartists();
		
		Collections.sort(myArtists);
		
		List<ArtistProfile> returnList = new ArrayList<ArtistProfile>();
		
		for (int i=0; i<myArtists.size(); i++) {
			if (myArtists.get(i)!= null) {
				returnList.add(myArtists.get(i));
			}
		}
		return returnList;
    }
    
    public JComponent getView() {
        return view;
    }

	public ContentController getNextContent() {
		return nextContent;
		
	}
	
	public void reload() {
		// TODO Auto-generated method stub
		
	}
}
