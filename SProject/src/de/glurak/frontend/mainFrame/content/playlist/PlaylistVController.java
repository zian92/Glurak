package de.glurak.frontend.mainFrame.content.playlist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JComponent;
import javax.swing.JTable;

import de.glurak.data.Playlist;
import de.glurak.frontend.mainFrame.ContentController;

public class PlaylistVController extends Observable implements ActionListener, ContentController {

    private PlaylistView view;
    private Playlist pList;
    private final String[] tableHeader = { "asdf", "sadf", "asdf", };

    /**
     * Konstruktor
     */
    public PlaylistVController() {
        view = new PlaylistView();
        view.getBtNext().addActionListener(this);
        view.getBtPrev().addActionListener(this);
        view.getBtNew().addActionListener(this);
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
    		System.out.println("PlaylistVCOntr: new List catched");
    		Playlist p = new Playlist(5, "Pokemon");
    		view.addPlaylist(p);
    	}else if(e.getActionCommand().equals("editList")){
    		
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
}