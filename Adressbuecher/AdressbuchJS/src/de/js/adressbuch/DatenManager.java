package de.js.adressbuch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class DatenManager {
	private String filePathString = "./AdressBuch.txt";

	public DatenManager() {

	}

	public void saveToFile(String inhalt) {
		PrintWriter writer = null;

		File f = new File(filePathString);
		if (f.exists()) {
			f.delete();
		}
		try {
			writer = new PrintWriter(filePathString, "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		writer.println(inhalt);
		writer.close();
	}

	@SuppressWarnings("resource")
	public String readFromFile() throws IOException {
		File f = new File(filePathString);
		BufferedReader reader = new BufferedReader(new FileReader(f));
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");

		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append(ls);
		}
		return stringBuilder.toString();
	}
}
