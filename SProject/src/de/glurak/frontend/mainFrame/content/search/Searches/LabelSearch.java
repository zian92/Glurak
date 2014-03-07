package de.glurak.frontend.mainFrame.content.search.Searches;

import de.glurak.data.Genre;
import de.glurak.data.User.Label;
import de.glurak.data.User.LabelProfile;
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
public class LabelSearch implements Searchable<LabelProfile> {
    @Override
    public List<LabelProfile> searchFor(String s) {
        DBSearch db = new DBSearch(SessionThing.getInstance().getDatabase());
        List<LabelProfile> g =db.searchLabelByName(s);
        return g;
    }

    @Override
    public ListCellRenderer<LabelProfile> getRenderer() {
        return new ListCellRenderer<LabelProfile>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends LabelProfile> list, LabelProfile value, int index, boolean isSelected, boolean cellHasFocus) {
                JPanel res = new JPanel();
                if (isSelected)
                    res.setBackground(Color.GRAY);
                res.setLayout(new BorderLayout());
                JLabel name = new JLabel();
                name.setText(value.getName());
                res.add(name,BorderLayout.CENTER);
                return res;
            }
        };
    }

    @Override
    public ContentController getChangeController(LabelProfile field) {
        return null;
    }
}
