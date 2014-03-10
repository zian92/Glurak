package de.glurak.frontend.mainFrame.content.follower;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import de.glurak.FrontendColors;
import de.glurak.data.Playlist;
import de.glurak.data.User.User;
import de.glurak.feature.IconLoader;

public class FollowerView extends JPanel {
	private int favoCount = 0;
	private JPanel pan_content;
	private MouseListener mRef;
	
	public FollowerView(ActionListener a, MouseListener m){
		super();
		this.mRef = m;
		this.setPreferredSize(new Dimension(600,500));
		this.setBackground(FrontendColors.DARK_GREY);
		this.setLayout(new BorderLayout());
		JScrollPane pan_scroll = new JScrollPane();
		// TODO: Dynamisches wachsen bei hinzufügen
		pan_content = new JPanel(new GridLayout(4,5));
    	//parent.add(child, constr);
	}
	
	public void addFollower(User favo){
		JLabel icon = new JLabel(new IconLoader(200, 200, favo.getProfile().getPictureFileNameOrDefaultPictureName()).getIcon());
		
		icon.setForeground(Color.WHITE);
    	icon.setFont(new Font("Verdana", Font.BOLD, 13));
    	icon.setText(favo.getUsername());
    	icon.setHorizontalTextPosition(JLabel.CENTER);
    	icon.setVerticalTextPosition(JLabel.BOTTOM);
    	icon.setPreferredSize(new Dimension(200	,200));
    	icon.setVisible(true);
    	icon.addMouseListener(mRef);
		
		pan_content.add(icon);
		favoCount++;	
		
	}
	
    public void fillView(List<User> list){
    	int pl_count = list.size();
    	
    	for (int i = 0; i < pl_count; i++){
    		
    		addFollower(list.get(i));
    	}
    }
    
    public void refreshView(List<User> list){
    	addFollower(list.get(list.size()-1));
    }
	
}
