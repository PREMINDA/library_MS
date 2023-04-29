package org.example.ui.gui;

import org.example.Repository.LibraryRepository;
import org.example.Services.LibraryService;
import org.example.commands.Commands;
import org.example.commands.GuiCommands;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LibraryGUIHelper {

    protected final LibraryService libraryService;

    protected JTable table;
    protected JPanel jPanel;
    protected JScrollPane scrollPane ;
    protected JPanel controlPanel;
    protected Dimension size;
    protected Commands guiCommands;

    public LibraryGUIHelper(LibraryService libraryService) {
        this.libraryService = libraryService;
        intView();
    }

    private void intView(){
        DefaultTableModel model = new DefaultTableModel();
        jPanel = new JPanel();
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        controlPanel = new JPanel(new GridLayout(5, 1,0,10));
        controlPanel.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(10, 10, 10, 10),
                BorderFactory.createEtchedBorder()));

        size = new Dimension(150,20);
        guiCommands = new GuiCommands(jPanel,table,libraryService, model);
    }

    public JPanel getjPanel(){
        return jPanel;
    }
}
