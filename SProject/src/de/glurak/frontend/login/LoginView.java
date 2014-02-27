package de.glurak.frontend.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.corba.se.impl.interceptors.PICurrent;

import de.glurak.frontend.registration.RegistrationView;

/**
 * Diese View stellt den Zugang zum Programm da.
 * Existiert noch kein Account, verweist diese View auf
 * die Registrierung
 * 
 * @author MxB
 * @version 0.1
 */
public class LoginView extends JFrame{
	
	private JTextField t_name;
	private JTextField t_passwort;
	private JLayeredPane pan_content;
	
	private JButton bt_login;
	private JButton bt_register;
	
	public LoginView(String Titel){
		super(Titel);
		
		// set layout
		//setLayout(new BorderLayout());
		
		// create swing components
		java.awt.Container content = getContentPane();
		
		pan_content = new JLayeredPane();
		
		JPanel pan_bg 		= new JPanel();
		
		JPanel pan_logframe	= new JPanel(new GridBagLayout());
		JPanel pan_bt		= new JPanel(new FlowLayout());
		JPanel pan_login	= new JPanel(new GridLayout(4,1,10,10));
		
				
		bt_login = new JButton("Einloggen");
		bt_register = new JButton("Registrieren");
		
		
		JLabel l_name 		= new JLabel("Name", JLabel.LEFT);
		JLabel l_passwort 	= new JLabel("Passwort", JLabel.LEFT);

		t_name 				= new JTextField("", 20);
		t_passwort 			= new JTextField("", 20);
				
		//create Backgroundimage
		try {
			BufferedImage loginBGImage = ImageIO.read(new File("loginscreen.jpg"));
			JLabel picLabel = new JLabel(new ImageIcon(loginBGImage));
			pan_bg.add(picLabel);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// create layout constrains
		pan_logframe.setBounds(700,150,280,320);
		pan_logframe.setBackground(Color.lightGray);
		pan_logframe.setOpaque(true);
		
		pan_login.setPreferredSize(new Dimension(150, 130));
		pan_login.setOpaque(true);
		pan_login.setBackground(Color.lightGray);
		pan_bt.setBackground(Color.lightGray);
	
		pan_bg.setBounds(0, 0, 1024, 700);
		pan_bg.setOpaque(true);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		//build constrain for the textfieldpnel
		c.gridx = 0;
		c.gridy = 0;
		pan_logframe.add(pan_login, c);
		
		c.gridx = 0;
		c.gridy = 1;
		pan_logframe.add(pan_bt, c);
		
		
		// add swing components to their panels
		pan_login.add(l_name);
		pan_login.add(t_name);
		pan_login.add(l_passwort);
		pan_login.add(t_passwort);
		
		pan_bt.add(bt_login);
		pan_bt.add(bt_register);
		
		/*
		RegistrationView reg = new RegistrationView();
		c.gridx = 0;
        c.gridy = 0;
        pan_logframe.add(reg, c);
        */
		
        pan_content.add(pan_bg, JLayeredPane.DEFAULT_LAYER, 0);
        pan_content.add(pan_logframe, JLayeredPane.PALETTE_LAYER, 0);
      
		content.add(pan_content);//, BorderLayout.CENTER);
			
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	public void openRegistrationPanel(){
		RegistrationView pan_reg = new RegistrationView();
		pan_reg.setBounds(600,200,280,280);
		pan_reg.setOpaque(true);
		
		pan_content.add(pan_reg, JLayeredPane.PALETTE_LAYER + 1, 0);
		//repaint();
	}

}
