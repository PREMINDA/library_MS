package org.example.ui.gui;

import org.example.Services.LibraryService;
import org.example.commands.Commander;

import javax.swing.*;
import java.awt.*;


public class BurrowBooks extends LibraryGUIHelper {


    public BurrowBooks(LibraryService libraryService, Commander commander) {
        super(libraryService,commander);
        createGUI();
    }

    private void createGUI() {

        JButton addButton = new JButton("Borrowing");
        addButton.addActionListener(e -> commander.exe(guiCommands::borrowBook));
        addButton.setPreferredSize(size);
        controlPanel.add(addButton);

        JButton removeButton = new JButton("Return Book");
        removeButton.addActionListener(e -> commander.exe(guiCommands::returnBook));
        removeButton.setPreferredSize(size);
        controlPanel.add(removeButton);

        JButton allButton = new JButton("All Non Available");
        allButton.addActionListener(e -> commander.exe(guiCommands::allNonAvailableBook));
        allButton.setPreferredSize(size);
        controlPanel.add(allButton);

        JButton burrowedButton = new JButton("Available Books");
        burrowedButton.addActionListener(e ->  commander.exe(guiCommands::allAvailableBook));
        burrowedButton.setPreferredSize(size);
        controlPanel.add(burrowedButton);

        JButton overDueButton = new JButton("Overdue Books");
        overDueButton.addActionListener(e -> commander.exe(guiCommands::overdueBooks));
        overDueButton.setPreferredSize(size);
        controlPanel.add(overDueButton);

        controlPanel.setPreferredSize(new Dimension(180,440));
        jPanel.add(controlPanel);
        jPanel.add(scrollPane);

        guiCommands.allAvailableBook();
    }
}
