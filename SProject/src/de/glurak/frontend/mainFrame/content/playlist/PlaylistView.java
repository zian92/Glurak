package de.glurak.frontend.mainFrame.content.playlist;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 * In der dieser View wird eine erstellte Playlist von einem User angezeigt. Die Playlist besteht aus Musikdateien.
 * 
 * @author Simon
 * 
 */
public class PlaylistView extends JPanel {
    private JTable jT;
    private JLabel playlistName;

    /**
     * Konstruktor
     */
    public PlaylistView() {
        this.setLayout(new FlowLayout());
    }

    public PlaylistView(JTable jT) {
        this();
        this.jT = jT;
        this.playlistName = new JLabel("Playlist test");
        this.createAndShowView();
        
    }

    /**
     * Erzeugt die Playlistview und zeigt sie an.
     */
    private void createAndShowView() {
        this.add(playlistName);
        this.add(jT);
    }

    public JTable getjT() {
        return jT;
    }

    public void setjT(JTable jT) {
        this.jT = jT;
    }

}
