package de.glurak.data.User;

/**
 * Hier sind alle Rechte gesammelt die es gibt...
 * @author Entscheider
 */
public class Rights {
    public static final String MANAGE_OWN_MEDIEN="own_medien_manage";
    public static final String LISTEN_RIGHT="listen";
    public static final String APPLICATION_TO_LABEL="applicaton_label";
    public static final String APPLICATION_TO_ADMIN="application_admin";
    public static final String MANAGE_PLAYLIST="playlist_manage";
    public static final String MANAGE_ALBUM="album_manage";
    public static final String MANAGE_OTHER_USER="manage_other_user";
    public static final String LOCK_OTHER_USER="lock_other_user";
    public static final String UNLOCK_OTHER_USER="unlock_other_user";
    public static final String LOCK_OTHER_MEDIEN="lock_medien";
    public static final String UNLOCK_OTHER_MEDIEN="unlock_medien";
    public static final String MAIL_SEND="send_mail";
    public static final String FOLLOW_USER="follow_user";
    public static final String DO_MESSAGE="messaging";
    public static final String GENRE_ADD="add_genre";
    public static final String ASSIGN_RIGHTS="assign_rights";
    public static final String ANOUNCEMENTS_RIGHTS="anouncement_right";
    public static final String ANOUNCEMENTS_FOR_LABEL_RIGHTS="anouncement_for_label_right";



    
    public static final String[] LISTENER_RIGHTS ={LISTEN_RIGHT,APPLICATION_TO_ADMIN,MANAGE_PLAYLIST,FOLLOW_USER,DO_MESSAGE};
    public static final String[] ARTIST_RIGHTS = {LISTEN_RIGHT,MANAGE_OWN_MEDIEN,APPLICATION_TO_LABEL,MANAGE_PLAYLIST,MANAGE_ALBUM,
                                    LOCK_OTHER_MEDIEN,LOCK_OTHER_USER,UNLOCK_OTHER_MEDIEN,UNLOCK_OTHER_USER,
                                    MAIL_SEND,FOLLOW_USER,DO_MESSAGE,GENRE_ADD,
                                    ANOUNCEMENTS_RIGHTS
                                };
    public static final String[] ADMIN_RIGHTS={LISTEN_RIGHT,DO_MESSAGE,GENRE_ADD,ASSIGN_RIGHTS};
    public static final String[] LABELMANAGER_RIGHTS={LISTEN_RIGHT,MANAGE_OTHER_USER,MANAGE_PLAYLIST,MANAGE_ALBUM,FOLLOW_USER,DO_MESSAGE,ANOUNCEMENTS_FOR_LABEL_RIGHTS};

}
