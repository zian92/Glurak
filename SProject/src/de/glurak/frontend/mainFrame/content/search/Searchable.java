package de.glurak.frontend.mainFrame.content.search;

import de.glurak.frontend.mainFrame.ContentController;

import javax.swing.*;
import java.util.List;

public interface Searchable<T> {
	
	public List<T> searchFor(String s);
    public ListCellRenderer<T> getRenderer();

    public ContentController getChangeController(T field);

}
