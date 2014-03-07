package de.glurak;

import javax.swing.*;

import de.glurak.data.Genre;
import de.glurak.data.User.AdminProfile;
import de.glurak.data.User.User;
import de.glurak.database.HibernateDB;
import de.glurak.frontend.SessionThing;
import de.glurak.frontend.login.LoginVController;

public class Glurak {

    private Glurak() {
        System.out.println("Started");
    }

    private void OpenInterface() {
        // Create GUI
        @SuppressWarnings("unused")
        LoginVController logControll = new LoginVController(Query.APPLICATION_NAME);
    }

    private static Glurak glumanda;
    private static SplashScreen splash;

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
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
                glumanda.OpenInterface();
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
        /*
         * try { userA.setPassword("olaf"); } catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
         */
        db.registrateUser(userA, null);
        AdminProfile profileA = new AdminProfile();
        db.registrateProfile(profileA, null);
        profileA.setFemale(false);
        profileA.setFirstname("Olaf");
        profileA.setLastname("Koehler");
        userA.setProfile(profileA);
    }
}
