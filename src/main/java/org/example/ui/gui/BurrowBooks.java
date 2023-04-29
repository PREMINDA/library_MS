package org.example.ui.gui;

import org.example.Services.LibraryService;
import javax.swing.*;
import java.awt.*;


public class BurrowBooks extends LibraryGUIHelper {


    public BurrowBooks(LibraryService libraryService) {
        super(libraryService);
        createGUI();
    }

    private void createGUI() {

        JButton addButton = new JButton("Borrowing");
        addButton.addActionListener(e -> guiCommands.borrowBook());
        addButton.setPreferredSize(size);
        controlPanel.add(addButton);

        JButton removeButton = new JButton("Return Book");
        removeButton.addActionListener(e -> guiCommands.returnBook());
        removeButton.setPreferredSize(size);
        controlPanel.add(removeButton);

        JButton allButton = new JButton("All Non Available");
        allButton.addActionListener(e -> guiCommands.allNonAvailableBook());
        allButton.setPreferredSize(size);
        controlPanel.add(allButton);

        JButton burrowedButton = new JButton("Available Books");
        burrowedButton.addActionListener(e ->  guiCommands.allAvailableBook());
        burrowedButton.setPreferredSize(size);
        controlPanel.add(burrowedButton);

        JButton overDueButton = new JButton("Overdue Books");
        overDueButton.addActionListener(e -> guiCommands.overdueBooks());
        overDueButton.setPreferredSize(size);
        controlPanel.add(overDueButton);

        controlPanel.setPreferredSize(new Dimension(180,440));
        jPanel.add(controlPanel);
        jPanel.add(scrollPane);

        guiCommands.allAvailableBook();
    }
}
