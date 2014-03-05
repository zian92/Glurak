package de.glurak.frontend.mainFrame.content.upload;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import de.glurak.feature.Uploader;
import de.glurak.frontend.mainFrame.ContentController;

public class UploadVController implements ActionListener, ContentController{

	private UploadView upview;
	private Uploader uploader;
	
	public UploadVController(){
		upview = new UploadView();
		upview.b_choosefile.addActionListener(this);
		upview.b_cancel.addActionListener(this);
		upview.b_upload.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == upview.b_choosefile){
			uploader.getInstance();
			uploader.selectMusic(upview);
		}else if(ae.getSource() == upview.b_upload){
			//TODO Was soll passieren wenn man upload drueckt?
		}else if(ae.getSource() == upview.b_cancel){
			//TODO Was soll bei abbrechen passieren? Ist abbrechen unnoetig?
		}
	}
	
	public JPanel getView(){
		return upview;
	}
	
}
