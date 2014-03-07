package de.glurak.frontend.mainFrame.content.search.Searches;

import de.glurak.data.Genre;
import de.glurak.data.User.User;
import de.glurak.database.DBSearch;
import de.glurak.frontend.SessionThing;
import de.glurak.frontend.mainFrame.ContentController;
import de.glurak.frontend.mainFrame.content.profile.ProfileVController;
import de.glurak.frontend.mainFrame.content.search.Searchable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *@author Entscheider
 */
public class UserSearch implements Searchable<User> {
    public List<User> searchFor(String s) {
        DBSearch db = new DBSearch(SessionThing.getInstance().getDatabase());
        List<User> g =db.searchForMusicByUsername(s);
        return g;
    }

    public ListCellRenderer<User> getRenderer() {
        return new ListCellRenderer<User>() {
            public Component getListCellRendererComponent(JList<? extends User> list, User value, int index, boolean isSelected, boolean cellHasFocus) {
                JPanel res = new JPanel();
                if (isSelected)
                    res.setBackground(Color.GRAY);
                res.setLayout(new BorderLayout());
                JLabel name = new JLabel();
                name.setText("<html> Name: <b>"+value.getProfile().getFirstname()+" "+value.getProfile().getLastname()+"</b></html>");
                res.add(name, BorderLayout.CENTER);
                JLabel username = new JLabel();
                username.setText("<html>Username:<i>"+value.getUsername()+"</i></html>");
                res.add(username, BorderLayout.SOUTH);
                return res;
            }
        };
    }

    public ContentController getChangeController(User field) {
        return null;
    }
}
