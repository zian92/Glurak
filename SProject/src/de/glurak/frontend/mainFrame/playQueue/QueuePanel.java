package de.glurak.frontend.mainFrame.playQueue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import de.glurak.Query;
import de.glurak.data.Playqueue;
import de.glurak.feature.IconLoader;

/**
 * Gui-Panel für die gerade zu spielende Playlist
 * 
 * @author MMÜhlenjost
 *
 */
public class QueuePanel extends JPanel{
	
	private	JPanel[] 		mediumPanelArray;
	private JTable[]		mediumTableArray;
	private JLabel[]		mediumLabelArray;
	private JPanel			firstPanel;
	private Playqueue 		playqueue;
	private JScrollPane scrollbar;
	private ImageIcon 			currentIcon;
	private ImageIcon			standardIcon;
	
	/**
	 * @param playlist die abzuspielende Playqueue
	 */
	public QueuePanel (Playqueue playqueue){
		super();
		currentIcon = new IconLoader(80,80,Query.FOLDER_PICTURE_ICONS+"playQueueIconCurrent.jpg").getIcon();
		standardIcon= new IconLoader(80,80,Query.FOLDER_PICTURE_ICONS+"playQueueIcon.jpg").getIcon();
		
		
		initComponents(playqueue);		
		
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
	/**
	 * initialisiert View f�r die angegebene Playqueue
	 */
	public void initComponents(Playqueue playqueue){
		this.removeAll();
		setPlayqueue(playqueue);
		firstPanel= new JPanel();
		firstPanel.setLayout(new GridLayout(1,getPlayqueue().getPlaylist().getMediumList().size()));
		mediumPanelArray = new JPanel[getPlayqueue().getPlaylist().getMediumList().size()];
		mediumTableArray= new JTable[getPlayqueue().getPlaylist().getMediumList().size()];
		mediumLabelArray= new JLabel[getPlayqueue().getPlaylist().getMediumList().size()];
		
		this.setLayout(new BorderLayout());
		this.setBackground(Color.GRAY);

		for(int i=0;i<getPlayqueue().getPlaylist().getMediumList().size();i++){
			mediumPanelArray[i]	= new JPanel();
			mediumLabelArray[i] = new JLabel(getPlayqueue().getPlaylist().getMediumList().get(i).getTitel());
			mediumPanelArray[i].setBackground(Color.WHITE);
			firstPanel.add(mediumPanelArray[i]);
			this.add(firstPanel,BorderLayout.CENTER);
			
			/*String[][] rowData={{"Titel",getPlayqueue().getPlaylist().getMediumList().get(i).getTitel()},{"Hates","0"},{"Likes","0"}};
			String[] columns={"",""};
			mediumTableArray[i] =new JTable(rowData,columns){
				 public boolean isCellEditable(int x, int y) {
			           return false;}
			       };
			mediumTableArray[i].setPreferredSize(new Dimension(100,100));
			*/
		}
		resetButton();
		
		
		
	
	}
	
	/** 
	 * Button für Medien der Playlist werden neu gezeichnet(bei Änderungen des CurrentMedium)
	 */
	public void resetButton(){
		JLabel icon;
		for(int i=0;i<getPlayqueue().getPlaylist().getMediumList().size();i++){
			mediumPanelArray[i].setPreferredSize(new Dimension(80,80));
			mediumPanelArray[i].removeAll();
			if(getPlayqueue().getPlaylist().getMediumList().get(i).equals(getPlayqueue().getCurrent())){
				icon= new JLabel(currentIcon);
			}else{
				icon=new JLabel(standardIcon);
			}
			mediumPanelArray[i].add(icon);
			mediumPanelArray[i].add(mediumLabelArray[i]);
			this.validate();
		
			
			}
		}
	
	public JScrollPane getScrollbar() {
		return scrollbar;
	}
	public JPanel getFirstPanel(){
		return firstPanel;
	}
	
	public void setScrollbar(JScrollPane scrollbar) {
		this.scrollbar = scrollbar;
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

	public void showInformations(int i) {
	
		if(mediumPanelArray[i].getComponent(1)==mediumTableArray[i]){
			mediumPanelArray[i].remove(mediumTableArray[i]);
			mediumPanelArray[i].add(mediumLabelArray[i]);
			
		}else{
		mediumPanelArray[i].remove(mediumLabelArray[i]);
		mediumPanelArray[i].add(mediumTableArray[i]);}
		
		this.validate();
	}
	
}
