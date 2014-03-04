package de.glurak.data;

import de.glurak.data.User.User;
import de.glurak.data.User.UserProfile;

/**
 * Exception wird geworfen, wenn nicht genug Rechte existieren
 * @author Entscheider.
 */
public class NotEnoughRightException extends RuntimeException{
    private String right_to_have;
    public NotEnoughRightException(){}

    /**
     * Konstruktor der das notwendige Recht mit annimmt.
     * @param right_to_have das Recht welches gebraucht wird.
     */
    public NotEnoughRightException(String right_to_have){
        super("Needed Right "+right_to_have+" but do not have it");
        this.right_to_have=right_to_have;
    }

    /**
     * Wirft die Exeption falls das UserProfile u das Recht right nicht besitzt
     * @param u das Profil des User
     * @param right das Recht
     * @throws NotEnoughRightException falls das Recht nicht besitzt
     */
    public static void throwIfNot(UserProfile u, String right) throws NotEnoughRightException{
        if (!u.hasRight(right)) throw new NotEnoughRightException(right);
    }



    /**
     * Wirft die Exeption falls das UserProfile u das Recht right nicht besitzt
     * @param u der User
     * @param right das Recht
     * @throws NotEnoughRightException falls das Recht nicht besitzt
     */
    public static void throwIfNot(User u, String right) throws NotEnoughRightException{
        throwIfNot(u.getProfile(),right);
    }

}
