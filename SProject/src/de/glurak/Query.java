package de.glurak;

public class Query {

	public final static String APPLICATION_NAME = "Glurak rules the world";
	public final static String ROOTPATH = "./";
	public final static String[] SUPPORTED_PICTURE_TYPES = { "png", "jpg", "bmp" };
	public final static String[] SUPPORTED_MUSIC_TYPES = { "mp3", };
	// Path-Variablen
	public final static String DATAPATH = "./../FILES/";
	public final static String FOLDER_MUSIC = DATAPATH + "music/";
	public final static String FOLDER_PICTURE = DATAPATH + "pictures/";
	public final static String FOLDER_PICTURE_PROFILE = FOLDER_PICTURE + "profile/";
	public final static String FOLDER_PICTURE_COVER = FOLDER_PICTURE + "cover/";
	public final static String FOLDER_PICTURE_SLIDER = FOLDER_PICTURE + "slider/";

	public final static String[] FOLDERS = { DATAPATH, FOLDER_MUSIC, FOLDER_PICTURE, FOLDER_PICTURE_PROFILE, FOLDER_PICTURE_COVER, FOLDER_PICTURE_SLIDER };
}