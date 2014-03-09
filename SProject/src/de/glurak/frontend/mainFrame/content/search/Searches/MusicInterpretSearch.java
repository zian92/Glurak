package de.glurak.frontend.mainFrame.content.search.Searches;

import de.glurak.data.Medium;
import de.glurak.database.DBSearch;
import de.glurak.frontend.SessionThing;

import java.util.List;

/**
 * @author Entscheider.
 */
public class MusicInterpretSearch extends MusicSearch {
    @Override
    public List<Medium> searchFor(String s) {
        DBSearch db = new DBSearch(SessionThing.getInstance().getDatabase());
        return db.searchForMusicByInterpret(s);
    }
}
