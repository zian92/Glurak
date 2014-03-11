package de.glurak.frontend.mainFrame.playQueue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import de.glurak.Query;
import de.glurak.data.Playqueue;
import de.glurak.feature.IconLoader;

/**
 * Gui-Panel für die gerade zu spielende Playlist
 * Anzeigen der Medien
 * Markieren des aktuellen Mediums
 * @author MMÜhlenjost
 *
 */
@SuppressWarnings("serial")
public class QueuePanel extends JPanel{
	
	private	JPanel[] 		mediumPanelArray;
	private JPanel			firstPanel;
	private Playqueue 		playqueue;
	private ImageIcon 			currentIcon;
	private ImageIcon			standardIcon;
	
	/**
	 * Konstrukter des QueuePanel
	 * @param playqueue die abzuspielende Playqueue
	 */
	public QueuePanel (Playqueue playqueue){
		super();
		//Laden der Icons
		currentIcon = new IconLoader(60,60,Query.FOLDER_PICTURE_ICONS+"playQueueIconCurrent.jpg").getIcon();
		standardIcon= new IconLoader(60,60,Query.FOLDER_PICTURE_ICONS+"playQueueIcon.jpg").getIcon();
		this.setPreferredSize(new Dimension(this.getMaximumSize().width,this.getMaximumSize().height));
		
		initComponents(playqueue);		
		
	}
	
	/**
	 * initialisiert View für die angegebene Playqueue
	 */
	public void initComponents(Playqueue playqueue){
		this.removeAll();
		if(playqueue!=null){
		setPlayqueue(playqueue);
		firstPanel= new JPanel();
		firstPanel.setLayout(new GridLayout(1,getPlayqueue().getPlaylist().getMediumList().size()));
		mediumPanelArray = new JPanel[getPlayqueue().getPlaylist().getMediumList().size()];
		
		this.setLayout(new BorderLayout());
		this.setBackground(Color.GRAY);

		for(int i=0;i<getPlayqueue().getPlaylist().getMediumList().size();i++){
			mediumPanelArray[i]	= new JPanel(new BorderLayout());
			mediumPanelArray[i].setBackground(Color.WHITE);
			firstPanel.add(mediumPanelArray[i]);
			this.add(firstPanel,BorderLayout.NORTH);
		}
		resetButton();
		
		}else{
		for(int i=0;i<mediumPanelArray.length;i++){
			mediumPanelArray[i].removeAll();
			mediumPanelArray[i].validate();
		}
		}
	}
	
	/** 
	 * Button für Medien der Playlist werden neu gezeichnet(bei Änderungen des CurrentMedium)
	 */
	public void resetButton(){
		JLabel icon;
		for(int i=0;i<getPlayqueue().getPlaylist().getMediumList().size();i++){
			mediumPanelArray[i].removeAll();
			if(getPlayqueue().getPlaylist().getMediumList().get(i).equals(getPlayqueue().getCurrent())){
				icon= new JLabel(currentIcon);
			}else{
				icon=new JLabel(standardIcon);
			}
			icon.setText(getPlayqueue().getPlaylist().getMediumList().get(i).getTitel());
			icon.setHorizontalTextPosition(JLabel.CENTER);
	    	icon.setVerticalTextPosition(JLabel.TOP);
	    	icon.setPreferredSize(new Dimension(120	,120));
			mediumPanelArray[i].add(icon,BorderLayout.NORTH);
			
			this.validate();
			}
			firstPanel.validate();
		}
	
	public JPanel getFirstPanel(){
		return firstPanel;
	}
	
	public ImageIcon getCurrentIcon() {
		return currentIcon;
	}

	public void setCurrentIcon(ImageIcon currentIcon) {
		this.currentIcon = currentIcon;
	}

	public ImageIcon getStandardIcon() {
		return standardIcon;
	}

	public void setStandardIcon(ImageIcon standardIcon) {
		this.standardIcon = standardIcon;
	}
	
	public JPanel[] getMediumPanelArray() {
		return mediumPanelArray;
	}

	public void setMediumPanelArray(JPanel[] mediumArray) {
		this.mediumPanelArray = mediumArray;
	}

	public Playqueue getPlayqueue() {
		return playqueue;
	}

	public void setPlayqueue(Playqueue playqueue) {
		this.playqueue = playqueue;
	}
}
