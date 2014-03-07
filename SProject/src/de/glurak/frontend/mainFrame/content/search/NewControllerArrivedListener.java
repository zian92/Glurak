package de.glurak.frontend.mainFrame.content.search;

import de.glurak.frontend.mainFrame.ContentController;

/**
 * Dieses Listener wird ausgelöst, falls bei der Suche das View geändert wird
 * @author Entscheider
 */
public interface NewControllerArrivedListener {
    public void gotNewController(ContentController c);
}
