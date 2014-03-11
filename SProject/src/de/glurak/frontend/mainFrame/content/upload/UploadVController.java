package de.glurak.frontend.mainFrame.content.upload;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.glurak.data.Medium;
import de.glurak.data.NewsEntry;
import de.glurak.feature.Uploader;
import de.glurak.frontend.SessionThing;
import de.glurak.frontend.mainFrame.ContentController;

/**
 * Der Kontroller der Uploadview
 * @author Simon
 *
 */
public class UploadVController implements ActionListener, ContentController {

    private UploadView upview;
    private Uploader uploader;
    private File music_file;
    private SessionThing session;

    /**
     * Konstruktor
     */
    public UploadVController() {
        upview = new UploadView();
        upview.b_choosefile.addActionListener(this);
        upview.b_cancel.addActionListener(this);
        upview.b_upload.addActionListener(this);
        session = SessionThing.getInstance();
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == upview.b_choosefile) {
            uploader = Uploader.getInstance();
            try {
                music_file = uploader.selectSingleMusic(upview);
                upview.t_file.setText(music_file.getName());
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else
            if (ae.getSource() == upview.b_upload) {
                if ((upview.t_title.getText().isEmpty()) || (session.getSessionUser().getProfile().equals("LabelManager") && upview.d_artist.getSelectedIndex() == 0) || (upview.t_album.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(upview, "Bitte füllen sie alle Felder aus, um das Medium hochzuladen!", "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
                } else
                    if ((music_file == null)) {
                        JOptionPane.showMessageDialog(upview, "Bitte wählen sie eine Datei aus, die hochgeladen werden soll!", "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
                    } else {
                        Medium musicFile = new Medium();
                        musicFile.setFileName(music_file.getAbsolutePath());
                        musicFile.setTitel(upview.t_title.getText());
                        musicFile.setOwner(session.getDatabase().getUserByUsername((String) upview.d_artist.getSelectedItem()));
                        musicFile.setMyGenre(session.getDatabase().genreByTitle((String) upview.d_genre.getSelectedItem()));

                        session.getDatabase().registrateMedium(musicFile, null);
                        
                        //Create new NewsEntry
                        NewsEntry entry = new NewsEntry(musicFile);
                        session.getDatabase().addNewsEntry(entry, null);
                        
                        // TODO Album!
                        uploader.saveMusic(new Medium[] { musicFile, }, "");
                        music_file=null;
                        upview.t_file.setText("");
                        upview.t_title.setText("");
                        upview.t_album.setText("");
                        upview.d_genre.setSelectedIndex(0);
                        upview.d_artist.setSelectedIndex(upview.d_artist.getItemCount()-1);
                    }
            } else
                if (ae.getSource() == upview.b_cancel) {
                    if (music_file == null) {
                        upview.t_file.setText("");
                        upview.t_title.setText("");
                        upview.t_album.setText("");
                        upview.d_genre.setSelectedIndex(0);
                        upview.d_artist.setSelectedIndex(upview.d_artist.getItemCount()-1);
                    } else {
                        music_file=null;
                        upview.t_file.setText("");
                        upview.t_title.setText("");
                        upview.t_album.setText("");
                        upview.d_genre.setSelectedIndex(0);
                        upview.d_artist.setSelectedIndex(upview.d_artist.getItemCount()-1);
                    }
                }
    }

    /* (non-Javadoc)
     * @see de.glurak.frontend.mainFrame.ContentController#getView()
     */
    public JPanel getView() {
        return upview;
    }

	/* (non-Javadoc)
	 * @see de.glurak.frontend.mainFrame.ContentController#reload()
	 */
	public void reload() {
		// TODO Auto-generated method stub
		
	}

}
