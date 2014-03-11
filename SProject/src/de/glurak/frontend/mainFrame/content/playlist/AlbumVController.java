package de.glurak.frontend.mainFrame.content.playlist;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.IOException;
import java.util.Observable;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import de.glurak.data.Album;
import de.glurak.feature.Uploader;
import de.glurak.frontend.SessionThing;
import de.glurak.frontend.mainFrame.ContentController;
import de.glurak.frontend.mainFrame.NextContent;

/**
 * Diese Klasse implementiert den AlbumVController.
 * @author Christopher Distelkämper
 * Date: 10.03.2014
 */
public class AlbumVController extends Observable implements ActionListener, ContentController, NextContent{

	private AlbumView albumview;
	private ContentController nextContent;
	/**
	 * Konstruktor
	 */
	public AlbumVController(Album p, ContentController c){
		albumview = new AlbumView(this);
		albumview.setAlbum(p);
	//	albumview.field_name.addFocusListener(this);
		albumview.field_name.addFocusListener(null);
		nextContent = c;
	}
	
	public JComponent getView(){
		return albumview;
	}
	/*
	public JComponent getView(Album p){
		albumview.setPlaylist(p);
		return albumview;
	} */
	
	public ContentController getNextContent() {
		return nextContent;
	}
	public void actionPerformed(ActionEvent e) {
		String filestring;
		filestring = "";
		if (e.getActionCommand().equals("cancel")){
			//nextContent = c;
			setChanged();
			notifyObservers();
		}else if (e.getActionCommand().equals("save")){
			if (!albumview.NameAppropriate()){
				JOptionPane.showMessageDialog(albumview,"Ihr Playlistname ist unangemessen.","Fehlerhafte Eingabe",JOptionPane.ERROR_MESSAGE);
				albumview.refreshName();
			}else if (e.getActionCommand().equals("upload")){
					
					Uploader u = Uploader.getInstance();
					File file = u.selectSinglePicture(this.albumview);
					try {
		                u.saveProfilePicture(file);
		                filestring = file.toString();
		            } catch (IOException e1) {
		                JOptionPane.showMessageDialog(this.albumview, "Bitte versuch es mit einer anderen Datei.", "Fehler", JOptionPane.ERROR_MESSAGE);
		            }
					this.albumview.repaint();
					this.albumview.revalidate();
					setChanged();
					notifyObservers();			
			
				}else{				
				
					// Save Album
					if ( albumview.getAlbum() == null) {
						Album npl = new Album();
						npl.setName(albumview.getAlbumName());
						npl.setFilename(filestring);
						npl.setOwner(SessionThing.getInstance().getSessionUser());
						SessionThing.getInstance().getDatabase().addPlaylist(npl, null);
						if (nextContent instanceof AlbumVController){
						//	((AlbumVController) nextContent).refreshView();
						}
					
						setChanged();
						notifyObservers();
					}
				
			}
		}
	}

	public void focusGained(FocusEvent e) {
		albumview.field_name.selectAll();
	}

	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void reload() {
		// TODO Auto-generated method stub
		
	}
}
