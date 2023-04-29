package org.example.ui.gui;

import org.example.Services.LibraryService;
import org.example.commands.Commander;
import org.example.ui.interfaces.UI;
import javax.swing.*;


public class LibraryGUI implements UI {

    private final LibraryService libraryService;
    private final Commander commander;
    public  LibraryGUI(LibraryService libraryService,Commander commander){
        this.libraryService = libraryService;
        this.commander = commander;
    }
    @Override
    public void show() {
        SwingUtilities.invokeLater(this::createAndShowGUI);
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Tabbed Pane Example");
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Library Data", new LibraryInfo(libraryService,commander).getjPanel());
        tabbedPane.addTab("Burrow Handler", new BurrowBooks(libraryService,commander).getjPanel());

        frame.add(tabbedPane);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
