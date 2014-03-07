package de.glurak;

import java.security.NoSuchAlgorithmException;

import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;

import de.glurak.data.Album;
import de.glurak.data.Genre;
import de.glurak.data.Medium;
import de.glurak.data.User.AdminProfile;
import de.glurak.data.User.ListenerProfile;
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

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
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
                SessionThing.getInstance().getDatabase().save();
            }
        });
    }

    private void initialisiereDB(HibernateDB db) {
        Genre baseGenre = db.addGenre("Ohne Genre", null, null);
        for (int i = 0; i < Query.INITIALE_GENRE.length; i++) {
            db.addGenre(Query.INITIALE_GENRE[i], baseGenre, null);
        }

        // userA
        User userA = new User();
        userA.setUsername("Olaf");
        try {
            userA.setPassword("olaf");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        db.registrateUser(userA, null);
        AdminProfile profileA = new AdminProfile();
        db.registrateProfile(profileA, null);
        profileA.setFemale(false);
        profileA.setFirstname("Olaf");
        profileA.setLastname("Koehler");
        userA.setProfile(profileA);

        // UserB
        User userB = new User();
        userB.setUsername("Creator");
        db.registrateUser(userB, null);
        ListenerProfile profileB = new ListenerProfile();
        db.registrateProfile(profileB, null);
        profileB.setFemale(true);
        profileB.setFirstname("Emma");
        profileB.setUser(userB);

        // musik
        Album a = new Album();
        a.setName("Unicorn Rainbow");
        a.setOwner(userB);
        a.setYearOfPublication("1999");

        Medium m = new Medium();
        m.setFileName(Query.FOLDER_MUSIC + "Pink Fluffy Unicorns.mp3");
        m.setMyGenre(db.genreByTitle(Query.INITIALE_GENRE[2]));
        m.setTitel("Pink Fluffy Unicorns");
        db.registrateMedium(m, null);
        a.getMediumList().add(m);

        m = new Medium();
        m.setFileName(Query.FOLDER_MUSIC + "Go Far Kid.mp3");
        m.setMyGenre(db.genreByTitle(Query.INITIALE_GENRE[2]));
        m.setTitel("Go Far Kid");
        db.registrateMedium(m, null);
        a.getMediumList().add(m);

        m = new Medium();
        m.setFileName(Query.FOLDER_MUSIC + "Pokemon Theme.mp3");
        m.setMyGenre(db.genreByTitle(Query.INITIALE_GENRE[2]));
        m.setTitel("Pokemon Theme");
        db.registrateMedium(m, null);
        a.getMediumList().add(m);
        // register Medien
        db.addPlaylist(a, null);
    }
}
