package de.js.adressbuch;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class AdressbuchJSGUI implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// initalisierung Variablen
	protected final Object headers[] = { "Vorname", "Anrede", "Nachname", "Strasse", "PLZ", "Ort", "Telefon", "Handy", "EMail", "Land" };
	protected String anwendungsname = "Adressbuch JS";
	protected JTable table;
	protected String path = "./Adressbuch.ser";
	protected String[][] emptyRow = new String[][] { { "", "", "", "", "", "", "", "", "", "" }, };

	public static void main(String args[]) {
		AdressbuchJSGUI js = new AdressbuchJSGUI();

	}

	public AdressbuchJSGUI() {
		// Daten Laden
		Object rows[][] = (Object[][]) this.toStringArray();

		// initialisierung GUI
		table = new JTable(new DefaultTableModel(rows, headers));
		JScrollPane scrollPane = new JScrollPane(table);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(scrollPane, BorderLayout.CENTER);
		Button b = new Button("Speichern");
		frame.add(b, BorderLayout.SOUTH);

		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[] { "", "", "", "", "", "", "", "", "", "" });
				saveAdressbuch();
			}
		});

		frame.setSize(600, 500);
		frame.setTitle(anwendungsname);
		frame.setVisible(true);
	}

	public String[][] toStringArray() {
		try {
			AdressEintrag[] a = (AdressEintrag[]) this.readAdressbuch().toArray();
			return convertToString2DArray(a);
		} catch (Exception e) {
			return emptyRow;
		}
	}

	public String[][] convertToString2DArray(AdressEintrag[] a) {
		String[][] s = new String[10][a.length];
		for (int i = 0; i < a.length; i++) {
			String[] temp = a[i].toStringArray();
			for (int j = 0; j < 10; j++) {
				s[j][i] = temp[j];
			}
		}
		return s;
	}

	public AdressEintrag[] getEintraege() {
		AdressEintrag[] a = new AdressEintrag[table.getRowCount()];
		for (int i = 0; i < a.length; i++) {
			a[i] = new AdressEintrag(table.getModel().getValueAt(i, 0).toString(), table.getModel().getValueAt(i, 1).toString(), table.getModel().getValueAt(i, 2).toString(), table.getModel().getValueAt(i, 3).toString(), table.getModel().getValueAt(i, 4).toString(), table.getModel().getValueAt(i, 5).toString(), table.getModel().getValueAt(i, 6).toString(), table.getModel().getValueAt(i, 7).toString(), table.getModel().getValueAt(i, 8).toString(), table.getModel().getValueAt(i, 9).toString());
		}
		return a;
	}

	private ArrayList<AdressEintrag> getArrayListFromArray(AdressEintrag[] ae) {
		ArrayList<AdressEintrag> temp = new ArrayList<AdressEintrag>();
		for (AdressEintrag a : temp) {
			temp.add(a);
		}
		return temp;
	}

	public void saveAdressbuch() {
		this.checkRows();
		ArrayList<AdressEintrag> ae = this.getArrayListFromArray(this.getEintraege());
		try {
			// Serialize data object to a file
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
			out.writeObject(ae);
			out.close();
		} catch (IOException e) {
		}
	}

	public ArrayList<AdressEintrag> readAdressbuch() {
		ArrayList<AdressEintrag> a = new ArrayList<AdressEintrag>();
		ObjectInputStream objIn = null;

		try {
			objIn = new ObjectInputStream(new FileInputStream(path));
			try {
				while (true) {
					a.add((AdressEintrag) objIn.readObject());
				}
			} catch (Exception e) {
			}
			objIn.close();
		} catch (FileNotFoundException e2) {
			// e2.printStackTrace();
		} catch (IOException e2) {
			// e2.printStackTrace();
		}
		return a;
	}

	public void checkRows() {
		AdressEintrag[] a = this.getEintraege();
		ArrayList<AdressEintrag> al = this.getArrayListFromArray(a);

		int rowcount = a.length;
		for (int i = a.length; i <= 0; i--) {
			if (table.getModel().getValueAt(i, 0).toString() == "" & table.getModel().getValueAt(i, 1).toString() == "" & table.getModel().getValueAt(i, 2).toString() == "" & table.getModel().getValueAt(i, 3).toString() == "" & table.getModel().getValueAt(i, 4).toString() == "" & table.getModel().getValueAt(i, 5).toString() == "" & table.getModel().getValueAt(i, 6).toString() == "" & table.getModel().getValueAt(i, 7).toString() == "" & table.getModel().getValueAt(i, 8).toString() == ""
					& table.getModel().getValueAt(i, 9).toString() == "") {
				al.remove(i);
			}
		}
		if (a.length == rowcount) {
			al.add(new AdressEintrag());
		}
		String[][] temp = this.convertToString2DArray((AdressEintrag[]) al.toArray());
		table = new JTable(new DefaultTableModel(temp, headers));
	}
}

@SuppressWarnings("serial")
class HeaderlessTable extends JTable {
	public HeaderlessTable(Object rowData[][], Object columnNames[]) {
		super(rowData, columnNames);
	}

	protected void configureEnclosingScrollPane() {
		Container container = getParent();
		if (container instanceof JViewport) {
			Container viewParent = container.getParent();
			if (viewParent instanceof JScrollPane) {
				JScrollPane scrollPane = (JScrollPane) viewParent;
				JViewport viewport = scrollPane.getViewport();
				if (viewport == null || viewport.getView() != this) {
					return;
				}
				scrollPane.setBorder(UIManager.getBorder("Table.scrollPaneBorder"));
			}
		}
	}
}
