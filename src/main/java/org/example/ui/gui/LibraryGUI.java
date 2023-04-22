package org.example.ui.gui;

import org.example.Repository.LibraryRepository;
import org.example.ui.UI;
import javax.swing.*;


public class LibraryGUI implements UI {

    private final LibraryRepository libraryRepository;
    public  LibraryGUI(LibraryRepository libraryService){
        this.libraryRepository = libraryService;
    }
    @Override
    public void show() {
        SwingUtilities.invokeLater(this::createAndShowGUI);
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Tabbed Pane Example");
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Library Data", new LibraryInfor(libraryRepository).getjPanel());
        tabbedPane.addTab("Burrow Handler", new BurrowBooks(libraryRepository).getjPanel());

        frame.add(tabbedPane);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


}
