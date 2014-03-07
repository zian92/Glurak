package de.glurak.frontend.mainFrame.content.upload;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.io.File;

import de.glurak.feature.Uploader;
import de.glurak.frontend.SessionThing;
import de.glurak.frontend.mainFrame.ContentController;

public class UploadVController implements ActionListener, ContentController{

	private UploadView upview;
	private Uploader uploader;
	private File music_file;
	private SessionThing session;
	
	public UploadVController(){
		upview = new UploadView();
		upview.b_choosefile.addActionListener(this);
		upview.b_cancel.addActionListener(this);
		upview.b_upload.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == upview.b_choosefile){
			uploader = Uploader.getInstance();
			music_file = uploader.selectSingleMusic(upview);
		}else if(ae.getSource() == upview.b_upload){
			if((upview.t_title.getText().isEmpty()) || (upview.t_artist.getText().isEmpty()) || (upview.t_album.getText().isEmpty())){
                JOptionPane.showMessageDialog(upview, "Bitte füllen sie alle Felder aus, um das Medium hochzuladen!", "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
			}else if((music_file == null)){
				JOptionPane.showMessageDialog(upview, "Bitte wählen sie eine Datei aus, die hochgeladen werden soll!", "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
			}else
			{
				//TODO Was soll passieren wenn man upload drueckt?
				//uploader.saveFile(music_file);
			}
		}else if(ae.getSource() == upview.b_cancel){
			if(music_file == null){
				upview.t_file.setText("");
				upview.t_title.setText("");
				upview.t_artist.setText("");
				upview.t_album.setText("");
				upview.d_genre.setSelectedIndex(0);
			}else{
				music_file.delete();
				upview.t_file.setText("");
				upview.t_title.setText("");
				upview.t_artist.setText("");
				upview.t_album.setText("");
				upview.d_genre.setSelectedIndex(0);
			}
		}
	}
	
	public JPanel getView(){
		return upview;
	}
	
}
