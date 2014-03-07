package de.glurak.frontend.mainFrame.content.search.Searches;

import de.glurak.data.Genre;
import de.glurak.data.Medium;
import de.glurak.database.DBSearch;
import de.glurak.frontend.SessionThing;
import de.glurak.frontend.mainFrame.ContentController;
import de.glurak.frontend.mainFrame.content.search.Searchable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rnr on 06.03.14.
 */
public class MusicSearch implements Searchable<Medium> {
    @Override
    public List<Medium> searchFor(String s) {
        DBSearch db = new DBSearch(SessionThing.getInstance().getDatabase());
        List<Medium> g =db.searchForMusicByTitle(s);
        return g;
    }

    @Override
    public ListCellRenderer<Medium> getRenderer() {
        return new ListCellRenderer<Medium>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends Medium> list, Medium value, int index, boolean isSelected, boolean cellHasFocus) {
                JPanel res = new JPanel();
                if (isSelected)
                    res.setBackground(Color.GRAY);
                res.setLayout(new BorderLayout());
                JLabel l = new JLabel();
                l.setText(value.getTitel());
                res.add(l,BorderLayout.CENTER);
                return res;
            }
        };
    }

    @Override
    public ContentController getChangeController(Medium field) {
        return null;
    }
}
