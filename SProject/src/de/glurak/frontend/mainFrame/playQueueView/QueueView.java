package de.glurak.frontend.mainFrame.playQueueView;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import de.glurak.data.Medium;
import de.glurak.data.Playlist;

public class QueueView extends JPanel{
	
	private	JPanel[] 		mediumPanelArray;
	private	JLabel[] 		mediumLabelArray;
	private JSplitPane[]	mediumSplitArray;
	private Playlist 		playlist;
	private	Medium 			current;
	
	private JScrollPane scrollbar;
	
	
	public QueueView(){
		
	}
	
	public QueueView(Playlist playlist,Medium current){
		super();
		setPlaylist(playlist);
		setCurrent(current);
		mediumPanelArray = new JPanel[getPlaylist().getMediumList().size()];
		mediumLabelArray = new JLabel[getPlaylist().getMediumList().size()];
		mediumSplitArray = new JSplitPane[(getPlaylist().getMediumList().size())];
		initComponents();		
		
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

	public void initComponents(){
		this.setLayout(new BorderLayout());
		this.setBackground(Color.GRAY);
		
		for(int i=0;i<getPlaylist().getMediumList().size();i++){
			mediumSplitArray[i]	= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		}
		
		for(int i=0;i<getPlaylist().getMediumList().size();i++){
			mediumLabelArray[i] = new JLabel(getPlaylist().getMediumList().get(i).getTitel()); 
			mediumPanelArray[i]	= new JPanel();
			mediumPanelArray[i].add(mediumLabelArray[i]);
			if(getPlaylist().getMediumList().get(i).equals(current)){
				System.out.println("Yolo");
				mediumPanelArray[i].setBackground(Color.BLUE);
			}
			mediumSplitArray[i].setLeftComponent(mediumPanelArray[i]);
			
			if(i!=getPlaylist().getMediumList().size()-1){
				mediumSplitArray[i].setRightComponent(mediumSplitArray[i+1]);
			}
			else{
				mediumSplitArray[i].setRightComponent(null);
			}
			
		}
		
		this.add(mediumSplitArray[0],BorderLayout.CENTER);
	}

	public JScrollPane getScrollbar() {
		return scrollbar;
	}

	public void setScrollbar(JScrollPane scrollbar) {
		this.scrollbar = scrollbar;
	}

	public JSplitPane[] getMediumSplitArray() {
		return mediumSplitArray;
	}

	public void setMediumSplitArray(JSplitPane[] mediumSplitArray) {
		this.mediumSplitArray = mediumSplitArray;
	}
	

}
