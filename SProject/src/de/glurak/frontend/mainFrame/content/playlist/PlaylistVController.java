package de.glurak.frontend.mainFrame.content.playlist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;

import de.glurak.data.Playlist;
import de.glurak.frontend.SessionThing;
import de.glurak.frontend.mainFrame.ContentController;

public class PlaylistVController extends Observable implements MouseListener, ActionListener, ContentController {

    private PlaylistView view;
    private Playlist pList;
    private final String[] tableHeader = { "asdf", "sadf", "asdf", };

    /**
     * Konstruktor
     */
    public PlaylistVController() {
        view = new PlaylistView(this,this);

      //  view.setjT(this.fillTable());
        view.setVisible(true);
    }

    private JTable fillTable() {
        Object[][] o = { { "sadf", "asdf", "asdf" }, { "sadf", "asdf", "asdf" }, { "sadf", "asdf", "asdf" } };
        JTable j = new JTable(o, tableHeader);
        return j;
    }

    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    	if (e.getActionCommand().equals("nextSlide")){
    		view.nextPage();
    	}else if (e.getActionCommand().equals("prevSlide")){
    		view.prevPage();
    	}else if(e.getActionCommand().equals("newList")){
    		Playlist p = new Playlist();
    		p.setName("Pokemon");
    		SessionThing.getInstance().getDatabase().addPlaylist(p, null);
    		view.addPlaylist(p);
    	}else if(e.getActionCommand().equals("editList")){
    		Playlist p = new Playlist();
    		p.setName("Gaga");
    		SessionThing.getInstance().getDatabase().addPlaylist(p, null);
    		view.addPlaylist(p);
    	}
    	
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

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel l = (JLabel) e.getSource();
		
		view.getTextLabel().setText(l.getText());
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
}