package de.glurak.frontend.mainFrame.playQueueView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import de.glurak.data.Medium;
import de.glurak.data.Playlist;

public class QueueView extends JPanel{
	
	private	JPanel[] 		mediumPanelArray;
	private	JButton[] 		mediumButtonArray;
	private JPanel			m;
	private Playlist 		playlist;
	private	Medium 			current;
	
	private JScrollPane scrollbar;
	
	
	public QueueView(){
		
	}
	
	/**
	 * @param playlist die abzuspielende Playlist
	 * @param current  das gerade abgespielte Medium
	 */
	public QueueView(Playlist playlist,Medium current){
		super();
		initComponents(playlist,current);		
		
	}
	
	public JPanel[] getMediumPanelArray() {
		return mediumPanelArray;
	}

	public void setMediumPanelArray(JPanel[] mediumArray) {
		this.mediumPanelArray = mediumArray;
	}

	public Playlist getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}

	public Medium getCurrent() {
		return current;
	}

	public void setCurrent(Medium current) {
		this.current = current;
	}

	/**
	 * initialisiert View f�r die angegebene Playlist
	 */
	public void initComponents(Playlist playlist,Medium current){
		
		setPlaylist(playlist);
		setCurrent(current);
		m= new JPanel();
		m.setLayout(new GridLayout(1,getPlaylist().getMediumList().size()));
		mediumPanelArray = new JPanel[getPlaylist().getMediumList().size()];
		mediumButtonArray = new JButton[getPlaylist().getMediumList().size()];
		//mediumSplitArray = new JPanel[(getPlaylist().getMediumList().size())];
		
		
		this.setLayout(new BorderLayout());
		this.setBackground(Color.GRAY);
//		this.setBounds(0,0,2000,20000);
//		this.setPreferredSize(new Dimension(2000,100));
	
/*
		for(int i=0;i<getPlaylist().getMediumList().size();i++){
			mediumSplitArray[i]	= new JPanel();
		}
	*/	
		for(int i=0;i<getPlaylist().getMediumList().size();i++){
			mediumButtonArray[i] = new JButton(getPlaylist().getMediumList().get(i).getTitel()); 
			mediumPanelArray[i]	= new JPanel();
			mediumPanelArray[i].add(mediumButtonArray[i]);
			if(getPlaylist().getMediumList().get(i).equals(current)){
				mediumPanelArray[i].setBackground(Color.BLUE);
			}
			m.add(mediumPanelArray[i]);
			this.add(m,BorderLayout.CENTER);
			
		/*	mediumSplitArray[i].add(mediumPanelArray[i]);
			
			if(i!=getPlaylist().getMediumList().size()-1){
				mediumSplitArray[i].add(mediumSplitArray[i+1]);
			}
			*/			
		}
		

		//this.add(mediumSplitArray[0],BorderLayout.CENTER);
		
	}

	public JScrollPane getScrollbar() {
		return scrollbar;
	}
	public JPanel getFirstPanel(){
		return m;
	}
	
	public void setScrollbar(JScrollPane scrollbar) {
		this.scrollbar = scrollbar;
	}
/*
	public JPanel[] getMediumSplitArray() {
		return mediumSplitArray;
	}

	public void setMediumSplitArray(JPanel[] mediumSplitArray) {
		this.mediumSplitArray = mediumSplitArray;
	}
	*/
	public JButton[] getMediumButtonArray(){
		return mediumButtonArray;
	}
	public void setMediumButtonArray(JButton[] mediumButtonArray){
		this.mediumButtonArray = mediumButtonArray;
	}
}
