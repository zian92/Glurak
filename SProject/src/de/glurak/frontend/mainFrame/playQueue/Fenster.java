package de.glurak.frontend.mainFrame.playQueue;

import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import de.glurak.data.Medium;
import de.glurak.data.Playlist;


public class Fenster extends JFrame{
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Fenster();

	}
	private Medium[] m;
	
	
	private Playlist pl;
	private LinkedList<Medium> mediumList;
	
	public Fenster(){
		this.setTitle("MegaView");
		this.setSize(200, 300);
		this.setResizable(true);
		mediumList= new LinkedList<Medium>();

		pl= new Playlist();
		pl.setMediumList(mediumList);
		Medium m1 = new Medium(1,"PinkFluffyUniconrs","test2.mp3", null);
		Medium m2 = new Medium(2,"Blah","test.mp3", null);
		pl.getMediumList().add(m1);
		pl.getMediumList().add(m2);
		
		
		PlayQueueViewController c = new PlayQueueViewController(pl);
		this.add(c.getView());
		
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
	}
	
	}


