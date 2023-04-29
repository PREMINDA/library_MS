package org.example.ui.gui;

import org.example.Services.LibraryService;
import org.example.ui.interfaces.UI;
import javax.swing.*;


public class LibraryGUI implements UI {

    private final LibraryService libraryService;
    public  LibraryGUI(LibraryService libraryService){
        this.libraryService = libraryService;
    }
    @Override
    public void show() {
        SwingUtilities.invokeLater(this::createAndShowGUI);
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Tabbed Pane Example");
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Library Data", new LibraryInfor(libraryService).getjPanel());
        tabbedPane.addTab("Burrow Handler", new BurrowBooks(libraryService).getjPanel());

        frame.add(tabbedPane);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
