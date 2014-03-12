package de.glurak.frontend.mainFrame.header;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;

import javax.swing.JComponent;

import de.glurak.frontend.mainFrame.playQueue.PlayQueueViewController;

/**
 * Repräsentiert einen HeaderVController, der dafür zuständig ist die Funktionalität für den Header bereit zu stellen
 * 
 */
public class HeaderVController extends Observable implements ActionListener, MouseListener {

    private HeaderView headview;
    // Der Suchbegriff, der in die Suchmaske des Headers eingegeben wird
    private String searchKey;
    // Wird auf true gesetzt, wenn logout-Button bet�tigt wurde
    private boolean logout = false;

    /**
     * Konstruktor legt die View an und hängt ActionListener and view components
     */
    public HeaderVController() {
        setHeadview(new HeaderView());
        headview.getSearchField().addMouseListener(this);
        headview.getSearchField().addActionListener(this);
        headview.getSearchButton().addActionListener(this);
        headview.getLogoutButton().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        // JButton für Suche oder Enter-Taste betätigt?
        if (e.getSource() == headview.getSearchButton() || e.getSource() == headview.getSearchField()) {
            setChanged();
            this.searchKey = headview.getSearchField().getText();
            notifyObservers();
        }

        // Logout-Button betätigt?
        if (e.getSource() == headview.getLogoutButton()) {
        	//PLayer schließen
        	PlayQueueViewController.getInstance().close();
        	PlayQueueViewController.getInstance().deleteObservers();
            setChanged();
            logout = true;
            notifyObservers();
        }

    }

    /**
     * Getter für Logout
     * 
     * @return boolean logout
     */
    public boolean getLogout() {
        return logout;
    }

    /**
     * Getter für View
     * 
     * @return JComponent view
     */
    public JComponent getView() {
        return headview;
    }

    /**
     * Getter für SearchKey
     * 
     * @return String searchKey
     */
    public String getSearchKey() {
        return searchKey;
    }

    /**
     * Setter für searchKey
     * 
     * @param searchKey
     */
    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    /**
     * Setter für HeadView
     * 
     * @param headview
     */
    public void setHeadview(HeaderView headview) {
        this.headview = headview;
    }

    /**
     * Beim in das Element Klicken wird das Textfeld geleert
     */
    // Bei Mausklick wird default-Inhalt des Suchfeldes gelöscht.
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == headview.getSearchField()) {
            headview.getSearchField().setText("");
        }

    }

    /**
     * keine Funktion implementiert
     */
    public void mouseEntered(MouseEvent arg0) {

    }

    /**
     * keine Funktion implementiert
     */
    public void mouseExited(MouseEvent arg0) {

    }

    /**
     * keine Funktion implementiert
     */
    public void mousePressed(MouseEvent arg0) {

    }

    /**
     * keine Funktion implementiert
     */
    public void mouseReleased(MouseEvent arg0) {

    }

}
