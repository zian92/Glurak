package de.glurak.frontend.mainFrame.content.adminlock;

import de.glurak.frontend.mainFrame.ContentController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

/**
 * Der Controller um Medien zu sperren
 * @author Entscheider
 */
public class AdminLockViewController extends Observable implements ContentController, ActionListener{


    private AdminLockView view;
    public AdminLockViewController(){
        view = new AdminLockView(this,null);
    }

    public JComponent getView() {
        return view;
    }
    public void actionPerformed(ActionEvent e) {
        if (view.getSelectedMedium()==null) return;
        if (e.getActionCommand().equals("lock")){
            view.getSelectedMedium().setLocked(true);
        }else if (e.getActionCommand().equals("unlock")){
            view.getSelectedMedium().setLocked(false);
        }
        view.search();
    }

	public void reload() {
        view.search();
	}
}
