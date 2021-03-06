package de.glurak.frontend.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import de.glurak.Query;
import de.glurak.feature.IconLoader;
import de.glurak.feature.SliderPanel;

public class LoginView extends JFrame{
	
	private JTextField t_name;
	private JPasswordField t_passwort;
	
	private JButton bt_login;
	private JButton bt_register;

	private Color  panColor = new Color(7354141);
	
	private JPanel pan_slider;
	private JPanel pan_logframe;
	
	private JLayeredPane pan_content;

    /**
     * Konstruktor
     * @param Titel der Titel des Fensters
     * @param lis der Listener für die Buttons, null für keinen
     */
	public LoginView(String Titel,ActionListener listener){
		super(Titel);
		setLayout(new BorderLayout());
		
		// create swing components
		java.awt.Container content = getContentPane();
		
		pan_content = new JLayeredPane();
		
		JPanel pan_bg 		= new JPanel();
		
		pan_logframe	= new JPanel(new GridBagLayout());
		JPanel pan_bt		= new JPanel(new FlowLayout());
		JPanel pan_login	= new JPanel(new GridLayout(4,1,10,10));
		pan_slider = new JPanel(new BorderLayout());
		

		bt_login = new JButton("Einloggen");
        bt_login.setActionCommand("login");
        if (listener != null)
            bt_login.addActionListener(listener);
		bt_register = new JButton("Registrieren");
        bt_register.setActionCommand("registrate");
        if (listener != null)
            bt_register.addActionListener(listener);
		
		JLabel l_name 		= new JLabel("Benutzername", JLabel.LEFT);
		JLabel l_passwort 	= new JLabel("Passwort", JLabel.LEFT);
		l_name.setForeground(Color.WHITE);
		l_passwort.setForeground(Color.WHITE);

		t_name 				= new JTextField("", 20);
		t_passwort			= new JPasswordField(8);
		t_passwort.setActionCommand("OK");
		t_passwort.addActionListener(listener);

				
		//create Backgroundimage
		try {
			BufferedImage loginBGImage = ImageIO.read(new File(Query.LOGIN_BACKGROUND));
			Image img =  loginBGImage.getScaledInstance(1024, 700, Image.SCALE_SMOOTH);
			JLabel picLabel = new JLabel(new ImageIcon(img));
			pan_bg.add(picLabel);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// create layout constrains
		pan_logframe.setBounds(600,150,320,320);
		//pan_logframe.setBackground(Color.lightGray);
		pan_logframe.setBackground(panColor);
		pan_logframe.setOpaque(true);
		
		pan_slider.setBounds(76, 80, 480, 480);
		pan_slider.setVisible(true);
		
		pan_login.setPreferredSize(new Dimension(150, 130));
		pan_login.setOpaque(true);
		pan_login.setBackground( panColor);
		pan_bt.setBackground( panColor);
	
		pan_bg.setBounds(0, -10, 1024, 700);
		//pan_bg.setOpaque(true);
		
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
			
        pan_content.add(pan_bg, JLayeredPane.DEFAULT_LAYER, 0);
        pan_content.add(pan_logframe, JLayeredPane.PALETTE_LAYER, 0);
        pan_content.add(pan_slider, JLayeredPane.PALETTE_LAYER, 0);
		
        
		content.add(pan_content, BorderLayout.CENTER);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		Image icon = new ImageIcon(Query.ICON_LOGOICON).getImage();
		this.setIconImage(icon);
		setVisible(true);

	}

    /**
     * Gibt das eingegebene Password zurück
     * @return das Password
     */
    public String getPassword(){
        return new String(t_passwort.getPassword());
    }

    /**
     * Gibt den eingegebenen Benutzernamen zurück
     * @return den Benutzernamen
     */
    public String getUsername(){
        return t_name.getText();
    }
	
	public JPanel getSliderPanel(){ return this.pan_slider; }

	public void setPanLogFrame(JPanel lPanel){
		pan_logframe = lPanel;
	}
	
	public JPanel getPanLogFrame(){
		return pan_logframe;
	}
	
	public JLayeredPane getPanContent(){
		return pan_content;
	}
}
