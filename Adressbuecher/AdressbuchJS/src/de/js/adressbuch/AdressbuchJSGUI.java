package de.js.adressbuch;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

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
	protected final String anwendungsname = "Adressbuch JS";
	protected JTable table;
	protected final String path = "./Adressbuch.ser";
	protected final Object[] emptyRow = new Object[] { "", "", "", "", "", "", "", "", "", "" };
	protected final Object[][] emptyRows = new String[][] { { "", "", "", "", "", "", "", "", "", "" }, };

	public static void main(String args[]) {
		@SuppressWarnings("unused")
		AdressbuchJSGUI js = new AdressbuchJSGUI();

	}

	/**
	 * Erstellt Die GUI mit allen Elementen
	 */
	public AdressbuchJSGUI() {
		// Daten Laden
		Object rows[][] = this.loadAdressbuch();

		// initialisierung GUI
		table = new JTable(new DefaultTableModel(rows, headers));
		JScrollPane scrollPane = new JScrollPane(table);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(scrollPane, BorderLayout.CENTER);

		// Buttons adden
		Button b = new Button("Speichern");
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveAdressbuch();
			}
		});

		Button b1 = new Button("Beenden");
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		Button b2 = new Button("Neue Zeile");
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addRow();
			}
		});
		Button b3 = new Button("Markierte Zeile loeschen");
		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteRow();
			}
		});
		Panel p = new Panel(new FlowLayout());

		p.add(b2);
		p.add(b3);
		p.add(b);
		p.add(b1);

		frame.add(p, BorderLayout.SOUTH);
		frame.setSize(600, 500);
		frame.setTitle(anwendungsname);
		frame.setVisible(true);
	}

	/**
	 * Gibt den Tabelleninhalt wieder.
	 * 
	 * @return String[][]
	 */
	public String[][] getTableData() {
		String[][] data = new String[10][table.getRowCount()];
		for (int i = 0; i < table.getRowCount(); i++)
			for (int j = 0; j < table.getColumnCount(); j++)
				data[i][j] = (String) table.getValueAt(i, j);
		return data;
	}

	/**
	 * Loescht die ausgewählte Tabelle
	 */
	public void deleteRow() {
		int i = table.getSelectedRow();
		if (i >= 0 && i < table.getRowCount()) {
			((DefaultTableModel) table.getModel()).removeRow(i);
		}
	}

	/**
	 * Fuegt am ende eine Zeile hinzu
	 */
	public void addRow() {
		((DefaultTableModel) table.getModel()).addRow(emptyRow);
	}

	/**
	 * laedt das Adressbuch aus dem speicher, falls möglich
	 * 
	 * @return
	 */
	@SuppressWarnings("resource")
	public String[][] loadAdressbuch() {
		String[][] data = null;
		InputStream fis = null;
		try {
			fis = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fis);
			data = (String[][]) ois.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("Keine Adressdaten vorhanden.");
			System.out.println("Lege neue Datei an...");
			data = (String[][]) emptyRows;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * speichert das Adressbuch lokal.
	 */
	public void saveAdressbuch() {
		String[][] data = this.getTableData();
		FileOutputStream fos = null;
		ObjectOutputStream oos;
		try {
			fos = new FileOutputStream(path);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(data);
		} catch (FileNotFoundException fosException) {
			fosException.printStackTrace();
		} catch (IOException oosException) {
			oosException.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
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
