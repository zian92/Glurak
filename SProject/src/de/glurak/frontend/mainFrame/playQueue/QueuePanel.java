package de.glurak.frontend.mainFrame.playQueue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import de.glurak.Query;
import de.glurak.data.Medium;
import de.glurak.data.Playlist;
import de.glurak.data.Playqueue;

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
	private Image 			currentIcon;
	private Image			standardIcon;
	
	/**
	 * @param playlist die abzuspielende Playqueue
	 */
	public QueuePanel (Playqueue playqueue){
		super();
		currentIcon = scaleImage(Query.FOLDER_PICTURE_ICONS+"playQueueIconCurrent.jpg",80,80);
		standardIcon= scaleImage(Query.FOLDER_PICTURE_ICONS+"playQueueIcon.jpg",80,80);
		
		
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
			firstPanel.add(mediumPanelArray[i]);
			this.add(firstPanel,BorderLayout.CENTER);
			
		}
		resetButton();
		/*
		String[][] rowData={{"Titel",getPlayqueue().getPlaylist().getMediumList().get(i).getTitel()},{"Hates","0"},{"Likes","0"}};
		String[] columns={"",""};
		mediumTableArray[i] =new JTable(rowData,columns){
			 public boolean isCellEditable(int x, int y) {
		           return false;}
		       };
		mediumTableArray[i].setPreferredSize(new Dimension(100,100));
		*/
	
	}
	
	/** 
	 * Button für Medien der Playlist werden neu gezeichnet(bei Änderungen des CurrentMedium)
	 */
	public void resetButton(){
		JLabel icon;
		for(int i=0;i<getPlayqueue().getPlaylist().getMediumList().size();i++){
			mediumPanelArray[i].setPreferredSize(new Dimension(80,80));
			mediumPanelArray[i].removeAll();
			mediumPanelArray[i].add(mediumLabelArray[i]);
			if(getPlayqueue().getPlaylist().getMediumList().get(i).equals(getPlayqueue().getCurrent())){
				icon= new JLabel(new ImageIcon(currentIcon));
			}else{
				icon=new JLabel(new ImageIcon(standardIcon));
			}
			mediumPanelArray[i].add(icon);
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


	public Image getCurrentIcon() {
		return currentIcon;
	}

	public void setCurrentIcon(Image currentIcon) {
		this.currentIcon = currentIcon;
	}

	public Image getStandardIcon() {
		return standardIcon;
	}

	public void setStandardIcon(Image standardIcon) {
		this.standardIcon = standardIcon;
	}
	/**
     * 
     * 
     * @param filename
     *            Vollqualifizierter Dateiname oder null für DefaultImage
     * @param w
     * @param h
     * @return Skalliertes Bild oder skalliertes Defaultbild
     */
    private Image scaleImage(String filename, int w, int h) {
        Image scaledImg = null;
        if (filename != null) {
            try {
                BufferedImage img = ImageIO.read(new File(filename));
                scaledImg = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            scaledImg = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
        }
        return scaledImg;
    }

	public void showInformations(int i) {
	
		if(mediumPanelArray[i].getComponent(1)==mediumTableArray[i]){
			mediumPanelArray[i].remove(mediumTableArray[i]);
			mediumPanelArray[i].add(mediumLabelArray[i]);
			
		}else{
		mediumPanelArray[i].remove(mediumLabelArray[i]);
		mediumPanelArray[i].add(mediumTableArray[i]);}
		
		mediumPanelArray[i].repaint();
	}
	
}
