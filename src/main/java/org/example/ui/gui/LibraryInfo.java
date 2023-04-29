package org.example.ui.gui;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.example.Services.LibraryService;
import javax.swing.*;
import java.awt.*;

public class LibraryInfo extends LibraryGUIHelper {

    public LibraryInfo(LibraryService libraryService) {
        super(libraryService);
        createGUI();
    }

    private void createGUI() {
        JButton addButton = new JButton("Add Book");
        addButton.addActionListener(e -> guiCommands.addBook());
        addButton.setPreferredSize(size);
        controlPanel.add(addButton);

        JButton removeButton = new JButton("Remove Book");
        removeButton.addActionListener(e -> guiCommands.removeBook());
        removeButton.setPreferredSize(size);
        controlPanel.add(removeButton);

        JButton allButton = new JButton("All Books");
        allButton.addActionListener(e -> guiCommands.allBooks());
        allButton.setPreferredSize(size);
        controlPanel.add(allButton);

        controlPanel.setPreferredSize(new Dimension(180,440));
        jPanel.add(controlPanel);
        jPanel.add(scrollPane);

        guiCommands.allBooks();
    }
}
