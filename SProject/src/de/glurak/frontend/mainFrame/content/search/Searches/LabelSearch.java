package de.glurak.frontend.mainFrame.content.search.Searches;

import de.glurak.data.Genre;
import de.glurak.data.User.Label;
import de.glurak.data.User.LabelProfile;
import de.glurak.database.DBSearch;
import de.glurak.frontend.SessionThing;
import de.glurak.frontend.mainFrame.content.search.Searchable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rnr on 06.03.14.
 */
public class LabelSearch implements Searchable {
    @Override
    public List<String> searchFor(String s) {
        DBSearch db = new DBSearch(SessionThing.getInstance().getDatabase());
        List<LabelProfile> g =db.searchLabelByName(s);
        List<String> res = new ArrayList<String>();

        for (LabelProfile ss : g){
            res.add(ss.getName());
        }
        return res;
    }
}
