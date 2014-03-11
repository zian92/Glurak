package de.glurak.frontend.mainFrame.content.search.Searches;

import de.glurak.data.Medium;
import de.glurak.database.DBSearch;
import de.glurak.frontend.SessionThing;

import java.util.List;

/**
 * @author Entscheider
 */
public class MusicGenreSearch extends MusicSearch{
    @Override
    public List<Medium> searchFor(String str) {
        DBSearch s = new DBSearch(SessionThing.getInstance().getDatabase());
        return s.searchForMusicByGenre(str);
    }
}
