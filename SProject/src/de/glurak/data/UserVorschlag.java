package de.glurak.data;

public class Rights{
    public final static String playListCreate = "playlist_create";
    public final static String uploadSong="song_upload";
    //....
    public final static String[] artistRights={playListCreate,uploadSong};
    //public final static String[] adminRights={...}
}

abstract public class Profil{
    private String address;
    private String kreditkartennummr;
    //...
    abstract public String roleName(); //Taucht nachher nicht in der Datenbank auf
}

abstract public class UserProfil extends Profil{
    abstract public String[] myRights(); //Taucht nachher nicht in der Datenbank auf-> einfache Rechteänderung
}

public class ListenerProfil extends UserProfil{
    //...
    public String roleName(){return "Listener";}
    public String[] myRights(){return Rights.ListenerRights;}
}

public class ArtistProfil extends ListenerProfil{
    //...
    public String roleName(){return "Artist";}
    public String[] myRights(){return Rights.ArtistRights;}
    private LabelProfil myLabel; // = null, falls kein Label
    public static ArtistProfil upgradeListener(ListenerProfil prof){/*...*/}
}

public class LabelProfil extends Profil{ //Profil des Labels
    //...
}

public class LabelManagerProfil extends ListenerProfil{
    //...
    private LabelProfil myLabel;
    public String[] myRights(){return Rights.LabelManagerRights;}
}

public class User {
    private UserProfil myprofil;
    private String passwordHash;
    private boolean gesperrt;
    //....
    public boolean checkPassword(String passw); //md5 Hash vergleichen
    public void hashPassword(String newPassword); //md5 Hash erzeugen und zuweisen
} //Keine Artist/Admin/... mehr. Wird durch Profil und myRights gemanagt.

/*
Vorteile:
- Listener zum Artist, einfach Profil kopieren ins ArtistProfil und rechte ändern. (Theoretisch auch einfach Rückgängigmachen)
  die ID zum User bleibt gleich. Das ist notwendig für Following, Messaging, ....
- Rechte einfach abfragen durch userX.myRights.contains("genre_add").
  Nicht notwendig userX instanceof LabelManager || userX instanceOf Admin || ...
  -> Naträgliche Änderung sehr einfach.
- Password wird nicht in Klartext gespeichert.
- TheLabel wird auch gleich in ein Profil gespeichert und den LabelManager zugewiesen
  => Keine doppelte Methoden bei User und LabelProfil
 */