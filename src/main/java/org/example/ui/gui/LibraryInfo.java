package org.example.ui.gui;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.example.Services.LibraryService;
import org.example.commands.Commander;

import javax.swing.*;
import java.awt.*;

public class LibraryInfo extends LibraryGUIHelper {

    public LibraryInfo(LibraryService libraryService, Commander commander) {
        super(libraryService,commander);
        createGUI();
    }

    private void createGUI() {
        JButton addButton = new JButton("Add Book");
        addButton.addActionListener(e -> commander.exe(guiCommands::addBook));
        addButton.setPreferredSize(size);
        controlPanel.add(addButton);

        JButton removeButton = new JButton("Remove Book");
        removeButton.addActionListener(e -> commander.exe(guiCommands::removeBook));
        removeButton.setPreferredSize(size);
        controlPanel.add(removeButton);

        JButton allButton = new JButton("All Books");
        allButton.addActionListener(e -> commander.exe(guiCommands::allBooks));
        allButton.setPreferredSize(size);
        controlPanel.add(allButton);

        controlPanel.setPreferredSize(new Dimension(180,440));
        jPanel.add(controlPanel);
        jPanel.add(scrollPane);

        guiCommands.allBooks();
    }
}
