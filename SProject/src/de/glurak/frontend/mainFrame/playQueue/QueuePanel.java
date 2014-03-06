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
	private Playlist 		playlist;
	private JScrollPane scrollbar;
	private Image 			currentIcon;
	private Image			standardIcon;
	
	/**
	 * @param playlist die abzuspielende Playlist
	 */
	public QueuePanel (Playlist playlist){
		super();
		currentIcon = scaleImage(Query.FOLDER_PICTURE_ICONS+"playQueueIconCurrent.jpg",80,80);
		standardIcon= scaleImage(Query.FOLDER_PICTURE_ICONS+"playQueueIcon.jpg",80,80);
		
		
		initComponents(playlist);		
		
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
	/**
	 * initialisiert View f�r die angegebene Playlist
	 */
	public void initComponents(Playlist playlist){
		this.removeAll();
		setPlaylist(playlist);
		firstPanel= new JPanel();
		firstPanel.setLayout(new GridLayout(1,getPlaylist().getMediumList().size()));
		mediumPanelArray = new JPanel[getPlaylist().getMediumList().size()];
		mediumTableArray= new JTable[getPlaylist().getMediumList().size()];
		mediumLabelArray= new JLabel[getPlaylist().getMediumList().size()];
			
		this.setLayout(new BorderLayout());
		this.setBackground(Color.GRAY);

		for(int i=0;i<getPlaylist().getMediumList().size();i++){
			mediumPanelArray[i]	= new JPanel();
			mediumLabelArray[i] = new JLabel(getPlaylist().getMediumList().get(i).getTitel());
			firstPanel.add(mediumPanelArray[i]);
			this.add(firstPanel,BorderLayout.CENTER);
			
		}
		resetButton();
	
	}
	
	/** 
	 * Button für Medien der Playlist werden neu gezeichnet(bei Änderungen des CurrentMedium)
	 */
	public void resetButton(){
		JLabel icon;
		for(int i=0;i<getPlaylist().getMediumList().size();i++){
			mediumPanelArray[i].setPreferredSize(new Dimension(80,80));
			mediumPanelArray[i].removeAll();
			mediumPanelArray[i].add(mediumLabelArray[i]);
			if(getPlaylist().getMediumList().get(i).equals(getPlaylist().getCurrent())){
				icon= new JLabel(new ImageIcon(currentIcon));
			}else{
				icon=new JLabel(new ImageIcon(standardIcon));
			}
			mediumPanelArray[i].add(icon);
		
			String[][] rowData={{"Titel",getPlaylist().getMediumList().get(i).getTitel()},{"Hates","0"},{"Likes","0"}};
			String[] columns={"",""};
			mediumTableArray[i] =new JTable(rowData,columns){
				 public boolean isCellEditable(int x, int y) {
			           return false;}
			       };
			mediumTableArray[i].setPreferredSize(new Dimension(100,100));
			
			
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
