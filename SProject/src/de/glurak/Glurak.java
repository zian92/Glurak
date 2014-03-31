package de.glurak;

import java.security.NoSuchAlgorithmException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.glurak.data.Album;
import de.glurak.data.Genre;
import de.glurak.data.Medium;
import de.glurak.data.User.AdminProfile;
import de.glurak.data.User.ArtistProfile;
import de.glurak.data.User.Label;
import de.glurak.data.User.LabelManagerProfile;
import de.glurak.data.User.LabelProfile;
import de.glurak.data.User.User;
import de.glurak.database.HibernateDB;
import de.glurak.frontend.SessionThing;
import de.glurak.frontend.login.LoginVController;

public class Glurak {
    private static Glurak glumanda;
    private static SplashScreen splash;

    private Glurak() {
    }

    private void openInterface() {
        // Create GUI
        @SuppressWarnings("unused")
        LoginVController logControll = new LoginVController(Query.APPLICATION_NAME);
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                splash = new SplashScreen();
                glumanda = new Glurak();
            }
        });
        HibernateDB db = SessionThing.getInstance().getDatabase();
        if (db.allGenres().isEmpty()) {
            glumanda.initialisiereDB(db);
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                splash.hideSplashScreen();
                glumanda.openInterface();
            }
        });

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                SessionThing.getInstance().getDatabase().close();
            }
        });
    }

    private void initialisiereDB(HibernateDB db) throws NoSuchAlgorithmException {
        Genre baseGenre = db.addGenre("Ohne Genre", null, null);
        for (int i = 0; i < Query.INITIALE_GENRE.length; i++) {
            db.addGenre(Query.INITIALE_GENRE[i], baseGenre, null);
        }

        // userA
        User userA = new User();
        userA.setUsername("Olaf");
        userA.setPassword("olaf");
        db.registrateUser(userA, null);
        AdminProfile profileA = new AdminProfile();
        profileA.setFemale(false);
        profileA.setFirstname("Olaf");
        profileA.setLastname("Koehler");
        db.registrateProfile(profileA, null);
        userA.setProfile(profileA);

        // UserB
        User userB = new User();
        userB.setPassword("creator");
        userB.setUsername("Creator");
        db.registrateUser(userB, null);
        ArtistProfile profileB = new ArtistProfile();
        profileB.setFemale(true);
        profileB.setFirstname("Emma");
        profileB.setLastname("Statibo");
        db.registrateProfile(profileB, null);
        profileB.setUser(userB);

        // labelmanager
        User lblManager = new User();
        lblManager.setPassword("manager");
        lblManager.setUsername("Manager");
        db.registrateUser(lblManager, null);
        LabelManagerProfile lblProfile = new LabelManagerProfile();
        lblProfile.setFemale(true);
        lblProfile.setFirstname("Peter");
        lblProfile.setLastname("Lustig");
        db.registrateProfile(lblProfile, null);
        lblProfile.setUser(lblManager);

        // musik
        Album a = new Album();
        a.setName("Unicorn Rainbow");
        a.setOwner(userB);
        a.setFilename(Query.FOLDER_PICTURE_ICONS + "splash.png");
        a.setYearOfPublication("1999");

        Medium m = new Medium();
        m.setFileName(Query.FOLDER_MUSIC + "Kookooo_Kitchen_-_13_-_Rats_Blossom_Into_Boys.mp3");
        m.setMyGenre(db.genreByTitle(Query.INITIALE_GENRE[2]));
        m.setTitel("Rats Blossom Into Boys");
        db.registrateMedium(m, null);
        a.getMediumList().add(m);
        m.setOwner(userB);

        m = new Medium();
        m.setFileName(Query.FOLDER_MUSIC + "Aint_No_Love_-_01_-_Gone_Already.mp3");
        m.setMyGenre(db.genreByTitle(Query.INITIALE_GENRE[2]));
        m.setTitel("Gone Already");
        db.registrateMedium(m, null);
        a.getMediumList().add(m);
        m.setOwner(userB);

        m = new Medium();
        m.setFileName(Query.FOLDER_MUSIC + "Baffin_Island_-_11_-_Darling_Please_Come_Home.mp3");
        m.setMyGenre(db.genreByTitle(Query.INITIALE_GENRE[2]));
        m.setTitel("Darling Please Come Home");
        db.registrateMedium(m, null);
        a.getMediumList().add(m);
        m.setOwner(userB);
        // register Medien
        db.addPlaylist(a, null);

        // Label Profil
        Label l = new Label();
        db.registrateReachable(l, null);
        LabelProfile lp = new LabelProfile();
        lp.setName("Label from Hell");
        db.registrateProfile(lp, null);
        lp.setLabel(l);
        lp.addArtist(profileB);
        // lbl Manager
        lblProfile.setMyLabel(l);
    }
}
