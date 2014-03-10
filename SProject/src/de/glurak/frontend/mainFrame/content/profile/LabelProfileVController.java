package de.glurak.frontend.mainFrame.content.profile;

import java.awt.event.*;
import javax.swing.*;

import de.glurak.data.User.Label;
import de.glurak.frontend.SessionThing;
import de.glurak.frontend.mainFrame.ContentController;
import de.glurak.frontend.mainFrame.content.message.ApplicationVController;
import de.glurak.frontend.mainFrame.content.playlist.PlaylistVController;
import de.glurak.frontend.mainFrame.content.profile.ProfileEditVController;

/**
 * Diese Klasse stellt dem LabelProfileView die Funktionalität zur Verfügung.
 * @author Christopher Distelkämper
 * Date: 28.02.2014
 */
//TODO ÜBERARBEITEN
public class LabelProfileVController implements ContentController, ActionListener{

    private LabelProfileView labelProfileView;

    /**
     *
     * @param l
     */
    public LabelProfileVController(Label l){

        labelProfileView = new LabelProfileView(SessionThing.getInstance().getSessionUser(),l,this);

        labelProfileView.reload();

    }

    public void actionPerformed(ActionEvent e){
        Object obj = e.getSource();

        if (obj == labelProfileView.b_follow){

        } else if (obj == labelProfileView.b_moreplaylists){
            //		setContentController(new PlaylistVController());
        } else if (obj == labelProfileView.b_edit){
            // 		setContentController(new ProfileEditVController());
        } else if (obj == labelProfileView.b_apply){
            //  	setContentController(new ApplicationVController());
        }

    }
    public JComponent getView() {
        return labelProfileView;
    }
}
