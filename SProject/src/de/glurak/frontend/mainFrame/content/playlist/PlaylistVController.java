package de.glurak.frontend.mainFrame.content.playlist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JTable;

import de.glurak.data.Playlist;
import de.glurak.frontend.mainFrame.ContentController;

public class PlaylistVController implements ActionListener, ContentController {

    private PlaylistView view;
    private Playlist pList;
    private final String[] tableHeader = { "asdf", "sadf", "asdf", };

    /**
     * Konstruktor
     */
    public PlaylistVController() {
        view = new PlaylistView();
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