package de.glurak.frontend.mainFrame.content.search.Searches;

import de.glurak.data.Genre;
import de.glurak.data.Medium;
import de.glurak.data.Playqueue;
import de.glurak.database.DBSearch;
import de.glurak.frontend.SessionThing;
import de.glurak.frontend.mainFrame.ContentController;
import de.glurak.frontend.mainFrame.content.search.Searchable;
import de.glurak.frontend.mainFrame.playQueue.PlayQueueView;
import de.glurak.frontend.mainFrame.playQueue.PlayQueueViewController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Suche für das Medium
 * @author Entscheider
 */
public class MusicSearch implements Searchable<Medium> {
    public List<Medium> searchFor(String s) {
        DBSearch db = new DBSearch(SessionThing.getInstance().getDatabase());
        List<Medium> g =db.searchForMusicByTitle(s);

        return filterUnlocked(g);
    }

    protected List<Medium> filterUnlocked(List<Medium> allMedien){
        List<Medium> unlockedMedium=new ArrayList<Medium>();
        for (Medium m: allMedien){
            if (!m.isLocked())
                unlockedMedium.add(m);
        }
        return unlockedMedium;
    }

    public ListCellRenderer<Medium> getRenderer() {
        return new ListCellRenderer<Medium>() {
            public Component getListCellRendererComponent(JList<? extends Medium> list, Medium value, int index, boolean isSelected, boolean cellHasFocus) {
                JPanel res = new JPanel();
                if (isSelected)
                    res.setBackground(Color.GRAY);
                res.setLayout(new BorderLayout());
                JLabel l = new JLabel();
                l.setText("<html><b>"+value.getTitel()+"</b></html>");
                res.add(l,BorderLayout.CENTER);
                JLabel interpret = new JLabel();
                if (value.getOwner()!=null)
                    interpret.setText("<html>Von: <i>"+value.getOwner().getUsername()+"</i>  Genre:<i>"+value.getMyGenre().getTitle()+"</i></html>");
                res.add(interpret,BorderLayout.SOUTH);
                return res;
            }
        };
    }

    public ContentController getChangeController(Medium field) {
        return null;
    }
    
    public void otherDoubleClickAction(Medium value) {
        PlayQueueViewController.getInstance().addMedium(value);
    }
}
