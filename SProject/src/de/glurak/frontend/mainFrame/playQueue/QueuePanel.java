package de.glurak.frontend.mainFrame.playQueue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import de.glurak.data.Medium;
import de.glurak.data.Playlist;

public class QueuePanel extends JPanel{
	
	private	JPanel[] 		mediumPanelArray;
	private	JButton[] 		mediumButtonArray;
	private JPanel			m;
	private Playlist 		playlist;
	private	Medium 			current;
	
	private JScrollPane scrollbar;
	
	
	public QueuePanel (){
		
	}
	
	/**
	 * @param playlist die abzuspielende Playlist
	 * @param current  das gerade abgespielte Medium
	 */
	public QueuePanel (Playlist playlist,Medium current){
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
			
		this.setLayout(new BorderLayout());
		this.setBackground(Color.GRAY);

		for(int i=0;i<getPlaylist().getMediumList().size();i++){
			mediumButtonArray[i] = new JButton(getPlaylist().getMediumList().get(i).getTitel()); 
			mediumPanelArray[i]	= new JPanel();
			mediumPanelArray[i].add(mediumButtonArray[i]);
			mediumPanelArray[i].setBackground(Color.LIGHT_GRAY);
			if(getPlaylist().getMediumList().get(i).equals(current)){
				mediumPanelArray[i].setBackground(Color.BLUE);
				
			}
			m.add(mediumPanelArray[i]);
			this.add(m,BorderLayout.CENTER);
			
		}
	}
	public void resetButton(){
		for(int i=0;i<getPlaylist().getMediumList().size();i++){
			mediumPanelArray[i].setBackground(Color.LIGHT_GRAY);
			if(getPlaylist().getMediumList().get(i).equals(getPlaylist().getCurrent())){
				mediumPanelArray[i].setBackground(Color.BLUE);				
			}
		}
		this.validate();
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

	public JButton[] getMediumButtonArray(){
		return mediumButtonArray;
	}
	public void setMediumButtonArray(JButton[] mediumButtonArray){
		this.mediumButtonArray = mediumButtonArray;
	}
}
