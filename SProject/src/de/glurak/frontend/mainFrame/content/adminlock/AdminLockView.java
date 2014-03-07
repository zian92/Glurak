package de.glurak.frontend.mainFrame.content.adminlock;

import de.glurak.data.Medium;
import de.glurak.frontend.mainFrame.content.search.SearchTab;
import de.glurak.frontend.mainFrame.content.search.Searches.MusicSearch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

/**
 * @author Entscheider
 */
public class AdminLockView extends JPanel {
    private SearchTab<Medium> musicSearchTab;

    public AdminLockView(ActionListener l, MouseListener m){
        setLayout(new BorderLayout());
        musicSearchTab = new SearchTab<Medium>(new AdminMusicSearch(),"Musik");
        if (m!=null)
            musicSearchTab.addMouseListener(m);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton lock = new JButton("Sperren");
        lock.setActionCommand("lock");
        if (l!=null)
            lock.addActionListener(l);
        JButton unlock = new JButton("Entsperren");
        unlock.setActionCommand("unlock");
        if (l!=null)
            unlock.addActionListener(l);
        buttonPanel.add(lock);
        buttonPanel.add(unlock) ;
        add(musicSearchTab,BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public Medium getSelectedMedium(){
        return musicSearchTab.getSelectedItem();
    }
}
