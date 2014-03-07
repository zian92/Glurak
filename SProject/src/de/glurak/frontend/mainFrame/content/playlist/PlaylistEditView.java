package de.glurak.frontend.mainFrame.content.playlist;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import de.glurak.FrontendColors;
import de.glurak.Query;
import de.glurak.data.Playlist;

public class PlaylistEditView extends JPanel{
	
	private JLabel 				lab_itemCount, lab_;
    private JTextField			field_name;
	private JTable 				tab_media;
	private String[] 			columnNames = {" Titel ", "Künstler"};
	private DefaultTableModel 	tabmod_address;
	private String 				tableEntries[][];
	
	private JButton bt_cancle, bt_delete, bt_save;
	
	private Playlist 		plRef = null;
	private ActionListener 	lisRef;
	
	public PlaylistEditView(ActionListener l){
		super();
		lisRef = l;
		buildView();
		
	}
	
	private void buildView(){
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(600,500));
		
		tabmod_address = new DefaultTableModel(tableEntries, columnNames);
		tab_media = new JTable(tabmod_address);
		tab_media.setFillsViewportHeight(true);
		tab_media.setBackground(FrontendColors.DARK_GREY);
		tab_media.setFont(Query.VERDANA.deriveFont(12f));
		tab_media.setForeground(Color.WHITE);
		
		JPanel pan_header = new JPanel(new GridBagLayout());
		GridBagConstraints constr = new GridBagConstraints();
		//constr.
		pan_header.setBackground(Color.GREEN);
		pan_header.setBounds(0, 0, 600, 150);
		
	  	field_name = new JTextField("\"Name eingeben\" ", 15);
	  	//field_name.set
	  	field_name.setBackground(FrontendColors.DARK_GREY);
	  	field_name.setForeground(Color.WHITE);
	  //	field_name.setOpaque(false);
	  	field_name.setBorder(null);

    	//field_name.setForeground(Color.WHITE);
    	field_name.setFont(Query.VERDANA.deriveFont(28f));;
		
		
		//lab_playlistname = new JLabel();
    	//lab_playlistname.setForeground(Color.WHITE);
    	//lab_playlistname.setFont(Query.VERDANA.deriveFont(24f));;
    	
    	lab_itemCount = new JLabel("Anzahl Songs: 0");
    	lab_itemCount.setForeground(Color.WHITE);
    	lab_itemCount.setFont(Query.VERDANA.deriveFont(12f));
    	
    	bt_cancle = new JButton(" Abbrechen ");
    	bt_cancle.setActionCommand("cancel");
    	bt_cancle.addActionListener(lisRef);
    	
    	bt_save = new JButton(" Speichern ");
    	bt_save.setActionCommand("save");
    	bt_save.addActionListener(lisRef);
    	
    	bt_delete = new JButton(" Löschen ");
    	bt_delete.setActionCommand("delete");
    	bt_delete.addActionListener(lisRef);
    	
    	//pan_header.add(bt_cancle, BorderLayout.WEST);
    	constr.gridx = 0;
    	constr.gridy = 0;
    	pan_header.add(bt_save, constr);
    	constr.gridx = 0;
    	constr.gridy = 1;
    	pan_header.add(bt_delete, constr);
    	constr.gridx = 0;
    	constr.gridy = 2;
    	pan_header.add(bt_cancle, constr);
    	constr.gridx = 1;
    	constr.gridy = 0;
    	constr.gridheight = 2;
    	pan_header.add(field_name, constr);
    	constr.gridx = 1;
    	constr.gridy = 2;
    	pan_header.add(lab_itemCount, constr);
       	pan_header.setVisible(true);
		
		JScrollPane pan_tab = new JScrollPane(tab_media);
       	
		this.add(pan_tab, BorderLayout.CENTER);
    	this.add(pan_header, BorderLayout.NORTH);

    	this.setVisible(true);
	}
	
	public void setPlaylist(Playlist p){
		if (p != null){
			plRef = p;
			fillView();
		}
	}
		
	/**
	 * Füllt die Tabelle der PlaylistEditView mit Daten aus Medien
	 * der Playlist dieser Klasseninstanz;
	 * @inv Wird nur aufgeruffen, wenn eine Playlist gesetzt ist.
	 */
	private void fillView(){
		field_name.setText(plRef.getName());
		//field_name.setEditable(false);
		lab_itemCount.setText("Anzahl Songs: " + plRef.getMediumList().size());
		if (plRef.getMediumList().size() > 0){
			
			for (int i = 0; i <plRef.getMediumList().size(); i++){
				String[] newEntrie = new String[2];
				newEntrie[0] = plRef.getMediumList().get(i).getTitel();
				newEntrie[1] = plRef.getMediumList().get(i).getOwner().getUsername();
				//newEntrie[1] = "niemand";
				tabmod_address.addRow(newEntrie);
			}
			
		}
		
	}
	
	public Playlist getPlaylist(){
		return plRef;
	}
	
	public String getPlaylistName(){
		return field_name.getText();
	}
	
	public void refreshName(){
		field_name.setText(" \"Name eingeben\" ");
	}
	
	/**
	 * Prüft, ob eine Änderung des Playlistnamens erwünscht ist.
	 * @return Falsch, wenn den Playlistname leer, unverändert oder == " Name eingeben " ist.
	 */
	public boolean NameAppropriate(){
		if (field_name.getText().isEmpty() ||
				field_name.getText().contains("Name eingeben") ||
				field_name.getText().contains("Penis") ||
				field_name.getText().contains("penis")){
			return false;
		}else{
			return true;
		}
	}
}
