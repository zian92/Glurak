package de.glurak.frontend.mainFrame.content.search.Searches;

import de.glurak.data.Genre;
import de.glurak.database.DBSearch;
import de.glurak.frontend.SessionThing;
import de.glurak.frontend.mainFrame.ContentController;
import de.glurak.frontend.mainFrame.content.search.Searchable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Entscheider.
 */
public class GenreSearch implements Searchable<Genre> {
    public List<Genre> searchFor(String s) {
        DBSearch db = new DBSearch(SessionThing.getInstance().getDatabase());
        List<Genre> g =db.searchForGenre(s);
        return g;
    }

    public ListCellRenderer<Genre> getRenderer() {
        return new ListCellRenderer<Genre>() {
            public Component getListCellRendererComponent(JList<? extends Genre> list, Genre value, int index, boolean isSelected, boolean cellHasFocus) {
                JPanel res = new JPanel();
                res.setLayout(new BorderLayout());
                JLabel l = new JLabel();
                l.setText("<html><b>"+value.getTitle()+"<b/></html>");
                res.add(l,BorderLayout.CENTER);
                JLabel other = new JLabel();
                if (value.getParentGenre()!=null)
                    other.setText("<html>Parent Genre: <i>"+value.getParentGenre().getTitle()+"</i></html>");
                res.add(other,BorderLayout.SOUTH);
                if (isSelected){
                    res.setBackground(Color.GRAY);
                }
                return res;
            }
        };
    }

    public ContentController getChangeController(Genre field) {
        return null;
    }
}
