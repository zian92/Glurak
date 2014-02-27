package de.glurak.feature.sound;

/**
 * Listener für Änderungen am Player
 * @author Entscheider
 */
public interface PlayStateListener {
    /**
     * Wird aufgerufen, falls wiedergabe gestoppt.
     * Dies geschieht, wenn stop() im Player aufgerufen wird,
     * oder falls dort zu Ende gespielt.
     */
    public void stopped();

    /**
     * Wird aufgerufen falls sich in Player die position des Musikstücks ändert.
     * Egal ob automatisch oder durch setPosition
     * @param pos
     */
    public void seek(long pos);
}
