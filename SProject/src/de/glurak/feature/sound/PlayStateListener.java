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
}
