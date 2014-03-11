package de.glurak.frontend.mainFrame.content.playlist;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import de.glurak.FrontendColors;
import de.glurak.Query;
import de.glurak.data.Playlist;
import de.glurak.feature.IconLoader;
import de.glurak.feature.SliderPanel;
import de.glurak.frontend.SessionThing;


/**
 * In der dieser View wird eine erstellte Playlist von einem User angezeigt. Die Playlist besteht aus Musikdateien.
 * 
 * @author Simon
 * 
 */
public class PlaylistView extends JPanel {
    
    private JLabel 		lab_name;
	private JPanel 		pan_header, pan_lowButtons;
	private SliderPanel pan_content;
	private ImageIcon 	ico_playList;
	private JButton 	bt_new,  bt_next, bt_prev;

	private List<SmartPage> pageArray = new ArrayList<SmartPage>();	
	private List<PlaylistLabel> IconArray = new ArrayList<PlaylistLabel>();	
	
	private int currentPage = -1;
	private ActionListener actionRef;
	private MouseListener mouseRef;
	
    /**
     * Konstruktor
     */
    public PlaylistView(ActionListener a, MouseListener m) {
    	super();
    	this.setLayout(new BorderLayout());
    	this.actionRef = a;
    	this.mouseRef = m;
    	this.setPreferredSize(new Dimension(825, 545));
    	lab_name = new JLabel();
    	lab_name.setBounds(10, 10, 80, 50);
    	lab_name.setForeground(Color.white);
    	
     	// Buttonpanel for the main functions of this view
    	pan_header = new JPanel(new BorderLayout());
    	//pan_buttons.setPreferredSize(new Dimension(600, 80));
    	pan_header.setBounds(0, 10, 600, 80);
    	pan_header.setBackground(Color.BLACK);
    	
        // Buttonpanel for the navigation within this Panel
    	pan_lowButtons = new JPanel(new BorderLayout());
    	pan_lowButtons.setBounds(0, 10, 600, 80);
    	pan_lowButtons.setBackground(Color.BLACK);
    	
    	// Main Buttons
    	bt_new = new JButton("Neue Playlist erstellen");
    	bt_new.setActionCommand("newList");
    	bt_new.addActionListener(actionRef);
    //	bt_edit = new JButton("Playlist bearbeiten");
    //	bt_edit.setActionCommand("editList");
   // 	bt_edit.addActionListener(actionRef);
    	
    	JLabel lab_text = new JLabel(" Deine Playlists");
	  	//field_name.set
    	lab_text.setBackground(FrontendColors.DARK_GREY);
    	lab_text.setHorizontalAlignment(JTextField.RIGHT);
    	lab_text.setForeground(Color.WHITE);
    	lab_text.setFont(Query.VERDANA.deriveFont(28f));
    	
    	
    	pan_header.add(bt_new, BorderLayout.WEST);
    	pan_header.add(lab_text, BorderLayout.CENTER);
  //  	pan_header.add(bt_edit);
    	
    	// Navigation Buttons
    	bt_next = new JButton("next");
    	bt_next.setActionCommand("nextSlide");
    	bt_next.addActionListener(actionRef);
    	bt_prev = new JButton("previous");
    	bt_prev.setActionCommand("prevSlide");
    	bt_prev.addActionListener(actionRef);
    	    	
       	pan_content = new SliderPanel();
    	pan_content.setPreferredSize(new Dimension(600,300));
    	
    	//Load default playlist icon
    	ico_playList = new IconLoader(50, 50, Query.FOLDER_PICTURE_ICONS + "pll.png").getIcon();
		
		//create the first Page of the PlaylistView
		SmartPage jp = new SmartPage();
    	jp.setBackground(FrontendColors.DARK_GREY); 
    	pageArray.add(jp);
   		pan_content.addSliderComponent(pageArray.get(pageArray.size()-1));
   		currentPage = 0;
   		
   		// Linking all the components together
    	pan_lowButtons.add(bt_next, BorderLayout.EAST);
    	pan_lowButtons.add(bt_prev, BorderLayout.WEST);
    	pan_lowButtons.add(lab_name, BorderLayout.CENTER);
   		pan_lowButtons.setVisible(false);
    	
    	add(pan_header, BorderLayout.NORTH);
    	add(pan_content, BorderLayout.CENTER);
    	add(pan_lowButtons, BorderLayout.SOUTH);
    	
    	
    	//fillView();
    	setVisible(true);
    }

    public void nextPage(){ 
    	if (pan_content.getItemCount() > 0){
    		pan_content.next();
    		currentPage++;
    		if (currentPage > pan_content.getItemCount())
    			currentPage = 0;
    	}	
    }
    
    public void prevPage(){ 
    	if (pan_content.getItemCount() > 0){
    		pan_content.previous();
    		currentPage--;
  			if (currentPage < 0)
			currentPage = pan_content.getItemCount();
		}	
    }
    
    /**
     * Füllt die Playlistview mit Plalyits des aktuall angemeldeten Benutzers
     */
    public void fillView(List<Playlist> list){
    	int pl_count = list.size();
    	
    	for (int i = 0; i < pl_count; i++){
    		
    		addPlaylist(list.get(i));
    	}
    }
    
    private void cleanView(){
    	int pages = pageArray.size();
		for (int i = 0; i < pages; i++){
			pageArray.get(i).removeItems();
		}
			
    }
    
    public void refreshView(List<Playlist> list){
    	cleanView();
    	fillView(list);
    }
    
    public void removeEmptyPage(){
    	//pan_content.removeSliderCompinent(contentPanArray.get(contentPanArray.size()-1));
    	//contentPanArray.remove(contentPanArray.size()-1);
    }
    
    /**
     * Erzeugt und positioniert ein Icon, das die übergebene Playist repräsentiert
     * @param p Playlistverweis
     */
    public void addPlaylist(Playlist p){
    	int page = currentPage;
    	if (pageFull(page)){
    		//createNewPage();
    
    		page = selectPageForAdd();
    	}
    	
    	PlaylistLabel icon = new PlaylistLabel(ico_playList);
    	icon.setForeground(Color.WHITE);
    	icon.setFont(new Font("Verdana", Font.BOLD, 13));
    	icon.setText(p.getName());
    	icon.setPlaylist(p);
    	icon.setHorizontalTextPosition(JLabel.CENTER);
    	icon.setVerticalTextPosition(JLabel.BOTTOM);
    	icon.setPreferredSize(new Dimension(120	,120));
    	icon.setVisible(true);
    	icon.addMouseListener(mouseRef);
    	
    	IconArray.add(icon);
    	placeIcon(icon, page);
    }
    
    /**
     * Plaziert das übergebene Icon auf dem Panel.
     * @param icon Das aufbereitete Label, welches ein Icon und den Playlistnamen enthält.
     * @param page Der Seitenindex der Seite, auf der das Icon plaziert wird, fall es nicht die aktuelle sein soll.
     */
    private void placeIcon(JLabel icon, int page){
    	pageArray.get(page).addItem(IconArray.get(IconArray.size()-1 ), BorderLayout.CENTER );
    	pan_content.refresh();
    }
    
    
    /**
     * Erschaft eine neue Page und fügt diese dem Panel hinzu
     */
    private void createNewPage(){
       	SmartPage jp = new SmartPage();
    	jp.setBackground(FrontendColors.DARK_GREY);
    	pageArray.add(jp);
   		pan_content.addSliderComponent(pageArray.get(pageArray.size()-1));
   		if (pageArray.size() == 2)
    		pan_lowButtons.setVisible(true);
    }
    
    /**
     * Liefert die Koordinate der ersten nicht vollen Seite.
     * Falls die letzte existierende Seite voll ist, wird eine neue erzeugt.
     * @inv Die gegenwärtige Seite ist voll
     */
    private int selectPageForAdd(){
    	
    	int tempPage = currentPage;
    	// getItemCount counts fromm zero, so +1 is needed for the correct amount of items
    	int maxPages = pan_content.getItemCount()+1;
    	
    	// if the current page is not the last page,
    	for (int c = 0; c < maxPages; c++){
    	
    		if (!pageFull(c)){
    			tempPage = c;
    			break;
    		}
    		// to compare indices maxPage is reduced again
    		if (c == maxPages-1){
    			createNewPage();
    			tempPage = pan_content.getItemCount();
    		}
    	}
    	return tempPage;
    }
    
    public void setPan_Content(SliderPanel sp){
    	pan_content = sp;
    	pan_content.refresh();
    }
    
    private boolean pageFull(int page){
    	if (pageArray.get(page).getItemCount() < 24){
    		return false;
    	}else{
    		return true;
    	}
    }
    
    private boolean pageEmpty(int page){
    	if (pageArray.get(page).getItemCount() < 1){
    		return false;
    	}else{
    		return true;
    	}
    }
    
    public JLabel getTextLabel(){
    	return lab_name;
    }
    
    
    public class PlaylistLabel extends JLabel{
    	private Playlist playlist = null;
    	public PlaylistLabel(ImageIcon icon){
    		super(icon);
    	}
    	
    	public void setPlaylist(Playlist p){
    		this.playlist = p;
    	}
    	
    	public Playlist getPlaylist(){
    		return playlist;
    	}
    }
    
    private class SmartPage extends JPanel{
    	
    	private int itemCount = 0;
    	
    	public SmartPage(){
    		super();
    		itemCount = 0;
    	}
    	
    	public SmartPage(LayoutManager layout){
    		super(layout);
    		itemCount = 0;
    	}
    	
    	public void addItem(JComponent comp, Object constrain){
    		add(comp, constrain);
    		itemCount++;
    	}
    	
    	public void removeItems(){
    		removeAll();
    		itemCount = 0;
    	}
    	
    	public int getItemCount(){ return itemCount; }
    }
}
