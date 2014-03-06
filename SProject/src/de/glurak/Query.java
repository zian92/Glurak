package de.glurak;

public class Query {

    public static final String APPLICATION_NAME = "Application Name comming soon";
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
    public static final String[] FOLDERS = { DATAPATH, FOLDER_MUSIC, FOLDER_PICTURE, FOLDER_PICTURE_PROFILE, FOLDER_PICTURE_COVER, FOLDER_PICTURE_SLIDER };
    
    private Query() {

    }

}