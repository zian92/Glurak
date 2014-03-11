package de.glurak;

import java.awt.Font;

public class Query {

    public static final String APPLICATION_NAME = "HateTunes";
    public static final String ROOTPATH = "./";
    public static final String[] SUPPORTED_PICTURE_TYPES = { "png", "jpg", "bmp" };
    public static final String[] SUPPORTED_MUSIC_TYPES = { "mp3", };
    // Path-Variablen
    public static final String DATAPATH = ROOTPATH + "/../FILES/";
    public static final String FOLDER_MUSIC = DATAPATH + "music/";
    public static final String FOLDER_PICTURE = DATAPATH + "pictures/";
    public static final String FOLDER_PICTURE_PROFILE = FOLDER_PICTURE + "profile/";
    public static final String FOLDER_PICTURE_COVER = FOLDER_PICTURE + "cover/";
    public static final String FOLDER_PICTURE_SLIDER = FOLDER_PICTURE + "slider/";
    public static final String FOLDER_PICTURE_ICONS = FOLDER_PICTURE + "defaulticons/";
    public static final String FOLDER_MISC = DATAPATH + "misc/";
    public static final String FOLDER_FONT = FOLDER_MISC + "fonts/";
    public static final String[] FOLDERS = { DATAPATH, FOLDER_MUSIC, FOLDER_PICTURE, FOLDER_PICTURE_PROFILE, FOLDER_PICTURE_COVER, FOLDER_PICTURE_SLIDER, FOLDER_MISC, FOLDER_FONT };
    // specil files
    public static final String ICON_HATE = FOLDER_PICTURE_ICONS + "hate.png";
    public static final String ICON_LIKE = FOLDER_PICTURE_ICONS + "rock.png";
    public static final String ICON_LOGO = FOLDER_PICTURE_ICONS + "htlogo.png";
    public static final String ICON_LOGOW = FOLDER_PICTURE_ICONS + "htlogow.png";
    public static final String ICON_LOGOICON = FOLDER_PICTURE_ICONS + "htlogoicon.png";
    public static final String SPLASHSCREEN_IMAGE = FOLDER_PICTURE_ICONS + "splash.gif";
    public static final String LOGIN_BACKGROUND = FOLDER_PICTURE_ICONS + "loginscreen.jpg";
    public static final String SPLASHSCREEN_FONT = FOLDER_FONT + "PAPYRUS.TTF";
    // Genres
    public static final String[] INITIALE_GENRE = { "Metal", "Rock", "Pop", "Punk", "Weltmusik", "Elektro", "Hip-Hop", "RnB", };
    // Styles
    public static final Font VERDANA = new Font("Verdana", Font.BOLD, 8);
    
    
    private Query() {

    }

}