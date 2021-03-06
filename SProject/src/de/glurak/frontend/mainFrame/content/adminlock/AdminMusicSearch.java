package de.glurak.frontend.mainFrame.content.adminlock;

import de.glurak.data.Medium;
import de.glurak.database.DBSearch;
import de.glurak.frontend.SessionThing;
import de.glurak.frontend.mainFrame.content.search.Searches.MusicSearch;

import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.util.*;

/**
 * Klasse die Suche nach alle Musik (ob gesperrt oder nicht) ermöglicht
 * und sie dementsprechend darstellen kann
 * @author Entscheider
 */
public class AdminMusicSearch extends MusicSearch {

   @Override
    public java.util.List<Medium> searchFor(String s) {
        DBSearch db = new DBSearch(SessionThing.getInstance().getDatabase());
        java.util.List<Medium> g =db.searchForMusicByTitle(s);
        return g;
    }
    @Override
    public ListCellRenderer<Medium> getRenderer() {
        return new ListCellRenderer<Medium>() {
            public Component getListCellRendererComponent(JList<? extends Medium> list, Medium value, int index, boolean isSelected, boolean cellHasFocus) {
                JPanel res = new JPanel();
                if (isSelected)
                    res.setBackground(Color.GRAY);
                res.setLayout(new BorderLayout());
                JLabel l = new JLabel();
                l.setText(value.getTitel());
                res.add(l,BorderLayout.CENTER);
                JLabel info=new JLabel();
                if (value.isLocked()){
                    info.setText("Gesperrt");
                    info.setForeground(Color.RED);
                    l.setForeground(Color.RED);
                }else
                    info.setText("Nicht gesperrt");
                res.add(info,BorderLayout.EAST);
                return res;
            }
        };
    }

    @Override
    public void otherDoubleClickAction(Medium value) {
        //Mach nichts
    }
}
