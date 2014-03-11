package de.glurak.frontend.mainFrame.content.follower;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import de.glurak.FrontendColors;
import de.glurak.Query;
import de.glurak.data.User.User;
import de.glurak.feature.IconLoader;

public class FollowerView extends JPanel {
	private int favoCount = 0, iconSize = 200;
	private JPanel pan_content, pan_header;
	private JLabel lab_headerText;
	private MouseListener mRef;
	private ActionListener aRef;
	
	public FollowerView(ActionListener a, MouseListener m){
		super();
		this.mRef = m;
		this.aRef = a;
		this.setPreferredSize(new Dimension(600,500));
		setBackground(FrontendColors.DARK_GREY);
		this.setLayout(new BorderLayout());

		pan_header = new JPanel(new FlowLayout());
		pan_header.setPreferredSize(new Dimension(600,80));
		pan_header.setBackground(Color.black);
		JLabel lab_headerText = new JLabel();
		lab_headerText.setForeground(Color.WHITE);
    	lab_headerText.setFont(Query.VERDANA.deriveFont(40f));
    	lab_headerText.setText("Deine Favoriten:");

    	pan_header.add(lab_headerText, BorderLayout.CENTER);
    	
    	pan_content = new JPanel(new GridLayout(0,4));
		pan_content.setBackground(FrontendColors.DARK_GREY);
		
		add(pan_header, BorderLayout.NORTH);
    	JScrollPane pan_scroll  = new JScrollPane(pan_content,   ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);    
		add(pan_scroll, BorderLayout.CENTER);

	}
	
	/**
	 * Plaziert ein Labelelement, welches den übergebenen Favoriten repraesentiert
	 * @param favo Der übergebene Favorit
	 */
	public void addFollower(User favo){
		JLayeredPane pan_bglayer = new JLayeredPane();
		JLabel lab_text = new JLabel();
		FavoriteLabel icon = new FavoriteLabel(new IconLoader(iconSize, iconSize-20, favo.getProfile().getPictureFileNameOrDefaultPictureName()).getIcon());
	
		icon.setFavorite(favo);
		lab_text.setForeground(Color.WHITE);
		lab_text.setFont(new Font("Verdana", Font.BOLD, 20));
    	lab_text.setText(favo.getUsername());
    	lab_text.setHorizontalTextPosition(JLabel.CENTER);
    	lab_text.setVerticalTextPosition(JLabel.BOTTOM);
    	icon.addMouseListener(mRef);
			
		lab_text.setBounds(5,5, iconSize , 30);
		
		JButton bt_like = icon.getLikeBt();
		bt_like.setActionCommand("likeFavo");
		bt_like.addActionListener(aRef);
		JButton bt_hate = icon.getHateBt();
		bt_hate.setActionCommand("hateFavo");
		bt_hate.addActionListener(aRef);
		
	    bt_like.setBounds(iconSize - 35*2, iconSize - 45, 30, 30);
	    bt_hate.setBounds(iconSize - 35, iconSize - 45, 30, 30);
	        
	    icon.setBounds(0, 0, iconSize, iconSize);
		icon.add(bt_like);
		icon.add(bt_hate);
		
		pan_bglayer.setPreferredSize(new Dimension(iconSize, iconSize));
		pan_bglayer.add(icon, JLayeredPane.DEFAULT_LAYER, 0);
		pan_bglayer.add(lab_text, JLayeredPane.DEFAULT_LAYER+1, 0);
	
		pan_bglayer.setVisible(true);
		pan_bglayer.setOpaque(true);
		pan_content.add(pan_bglayer);
		favoCount++;
	}
	
	public void cleanView(){
		pan_content.removeAll();
	}
	
	public void refresh(){
		pan_content.revalidate();
		pan_content.repaint();
	}
	
	public void removeFollower(){
		pan_content.remove(favoCount);
		favoCount--;
		pan_content.repaint();
	}
	
    public void fillView(List<User> list){
    	int pl_count = list.size();
    	
    	for (int i = 0; i < pl_count; i++){
    		
    		addFollower(list.get(i));
    	}
    	refresh();
    }
    
    /**
     * Eine Hidden Class, die das Icon des gefollowten Users, und auch eine Referenz auf dessen Profil speichert
     * @author MxB
     *
     */
    public class FavoriteLabel extends JLabel{
    	private User favor;
    	private JButton hate, like;
    	
    	
     	public FavoriteLabel(ImageIcon icon){
    		super(icon);
    		like = new JButton();
    		hate = new JButton();
    	}
    	
    	public void setFavorite(User u){
    		this.favor = u;
    	}
    	
    	public JButton getLikeBt(){
    		return like;
    	}
    	
    	public JButton getHateBt(){
    		return hate;
    	}
    	
    	public User getFavorite(){
    		return favor;
    	}
    }
}
