package de.glurak.frontend.mainFrame.content.search;

import de.glurak.frontend.mainFrame.ContentController;

import javax.swing.*;
import java.util.List;

/**
 * Dieses Interface gibt die Funktionen und Daten für ein SearchTab, auf das es richtig funktioniert
 * @param <T> der Typ nach den gesucht werden kann
 */
public interface Searchable<T> {
    /**
     * Sucht mittels den String s
     * @param s wonach gesucht werden soll
     * @return die Liste mit den Ergebnissen
     */
	public List<T> searchFor(String s);

    /**
     * Gibt den ListCellRenderer für die Ergebnisse zurücl
     * @return der Renderer
     */
    public ListCellRenderer<T> getRenderer();

    /**
     * Gibt einen ContentController zurück, der für das
     * nährere Betrachten der Ergebnisse zuständig ist
     * @param field was soll genauer angezeigt werden
     * @return den ContentController
     */
    public ContentController getChangeController(T field);

    /**
     * Sonstige Aktion die bei einen Doppelklick auf einen Listelement durchgeführt wird
     * @param value
     */
    public void otherDoubleClickAction (T value);
}
