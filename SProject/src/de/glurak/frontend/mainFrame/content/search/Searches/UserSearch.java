package de.glurak.frontend.mainFrame.content.search.Searches;

import de.glurak.data.Genre;
import de.glurak.data.User.User;
import de.glurak.database.DBSearch;
import de.glurak.frontend.SessionThing;
import de.glurak.frontend.mainFrame.content.search.Searchable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rnr on 06.03.14.
 */
public class UserSearch implements Searchable {
    @Override
    public List<String> searchFor(String s) {
    DBSearch db = new DBSearch(SessionThing.getInstance().getDatabase());
    List<User> g =db.searchForMusicByUsername(s);
    List<String> res = new ArrayList<String>();

    for (User ss : g){
        res.add(ss.getUsername());
    }
    return res;
}
}
