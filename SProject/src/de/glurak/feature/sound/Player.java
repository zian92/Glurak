package de.glurak.feature.sound;
import de.glurak.data.Medium;
/**
 * Klasse die sich darüm kümmert, dass die Musik abgespielt wird
 * @author Entscheider
 */
public interface Player {

    /**
     * Spielt das Medium m ab
     * @param m das abzuspielende Medium
     */
    public void play(Medium m);


    /**
     * Spielt das aktuelle Medium ab. Falls kein akteulles Medium vorhanden,
     * wird nichts abgespielt.
     */
    public void play();

    /**
     * Gibt zurück ob Player gerade abspielt
     * @return true, falls abspielt, false sont
     */
    public boolean isPlaying();

    /**
     * Gibt zurück ob Player gestoppt ist
     * @return true, falls gestoppt, false sont
     */
    public boolean isStopped();

    /**
     * Gibt zurück ob Player pausert
     * @return true falls pausiert, false sonst.
     */
    public boolean isPaused();

    /**
     * Pausiert die Musikausgabe
     */
    public void pause();

    /**
     * Stopt die Musikausgabe und das aktuel abgespielte Medium wird null.
     */
    public void stop();

    /**
     * Gibt das gerade gespielte Musikstück zurück
     * @return null, falls nichts abegspielt, sonst das Medium
     */
    public Medium getCurrentMedium();
}
