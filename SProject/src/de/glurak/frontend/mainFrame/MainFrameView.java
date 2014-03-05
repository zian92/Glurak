package de.glurak.frontend.mainFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import de.glurak.Query;


public class MainFrameView extends JFrame {
	
	private JPanel header;
	private JPanel navigation;
	private JPanel player;
	private JPanel content;
	
	
	public MainFrameView(){
		setTitle(Query.APPLICATION_NAME);
		header = new JPanel(new BorderLayout());
		header.setPreferredSize(new Dimension(1024, 35));
		header.setBackground(Color.GREEN);
		navigation = new JPanel(new BorderLayout());
		navigation.setPreferredSize(new Dimension(220, 545));
		navigation.setBackground(Color.GRAY);
		player = new JPanel(new BorderLayout());
		player.setPreferredSize(new Dimension(1024, 120));
		player.setBackground(Color.BLACK);
		content = new JPanel(new BorderLayout());
		content.setPreferredSize(new Dimension(724, 545));
		content.setBackground(Color.BLUE);
			
		add(header, BorderLayout.NORTH);
		add(navigation, BorderLayout.WEST);
		add(player, BorderLayout.SOUTH);
		add(content, BorderLayout.CENTER);
		
		setMinimumSize(new Dimension(1060,740));
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	public JPanel getContent() {
		return content;
	}

	public void setContent(JPanel content) {
		this.content = content;
	}

	public JPanel getHeader() {
		return header;
	}

	public JPanel getNavigation() {
		return navigation;
	}

	public JPanel getPlayer() {
		return player;
	}

	public void setHeader(JPanel header) {
		this.header = header;
	}

	public void setNavigation(JPanel navigation) {
		this.navigation = navigation;
	}

	public void setPlayer(JPanel player) {
		this.player = player;
	}
	
	
}
