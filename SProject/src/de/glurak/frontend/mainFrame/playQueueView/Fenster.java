package de.glurak.frontend.mainFrame.playQueueView;

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
		this.setResizable(false);
		mediumList= new LinkedList<Medium>();
		m= new Medium[20];
		for(int i=0;i<20;i++){
		m[i]= new Medium(i,"Titel"+i+"","File"+i+"",null);
		mediumList.add(m[i]);
		}
		
		pl= new Playlist();
		pl.setMediumList(mediumList);
		
		PlayQueueView pq = new PlayQueueView(pl,m[5]);
		this.add(pq);
		
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
	}
	
	}


