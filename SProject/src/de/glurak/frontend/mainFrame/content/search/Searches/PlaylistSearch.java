package de.glurak.frontend.mainFrame.content.search.Searches;

import de.glurak.data.Genre;
import de.glurak.data.Playlist;
import de.glurak.database.DBSearch;
import de.glurak.frontend.SessionThing;
import de.glurak.frontend.mainFrame.ContentController;
import de.glurak.frontend.mainFrame.content.search.Searchable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Entscheider
 */
public class PlaylistSearch implements Searchable<Playlist> {
    public List<Playlist> searchFor(String s) {
        DBSearch db = new DBSearch(SessionThing.getInstance().getDatabase());
        List<Playlist> g =db.searchPlaylistByName(s);
        return g;
    }

    public ListCellRenderer<Playlist> getRenderer() {
        return new ListCellRenderer<Playlist>() {
            public Component getListCellRendererComponent(JList<? extends Playlist> list, Playlist value, int index, boolean isSelected, boolean cellHasFocus) {
                JPanel res = new JPanel();
                res.setLayout(new BorderLayout());
                if (isSelected)
                    res.setBackground(Color.GRAY);
                JLabel l = new JLabel();
                l.setText(value.getName());
                res.add(l, BorderLayout.CENTER);
                return res;
            }
        };
    }

    public ContentController getChangeController(Playlist field) {
        return null;
    }
}
