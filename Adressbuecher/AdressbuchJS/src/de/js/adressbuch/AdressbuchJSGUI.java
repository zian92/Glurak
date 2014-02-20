package de.js.adressbuch;

import java.awt.*;
import javax.swing.*;

public class AdressbuchJSGUI {
	public static void main(String args[]) {
		Object rows[][] = {  {"","","","","","","","","",""},{"","","","","","","","","",""},{"","","","","","","","","",""},{"","","","","","","","","",""},{"","","","","","","","","",""},{"","","","","","","","","",""},{"","","","","","","","","",""},{"","","","","","","","","",""},{"","","","","","","","","",""},{"","","","","","","","","",""},};
		Object headers[] = { "Vorname", "Anrede", "Nachname", "Strasse", "PLZ", "Ort", "Telefon", "Handy", "EMail", "Land" };
		JTable table = new JTable(rows, headers);
		JScrollPane scrollPane = new JScrollPane(table);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.setSize(600, 150);
		frame.setTitle("Adressbuch JS");
		frame.setVisible(true);
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
