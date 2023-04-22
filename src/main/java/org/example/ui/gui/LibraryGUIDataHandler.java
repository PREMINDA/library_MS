package org.example.ui.gui;

import org.example.Repository.LibraryRepository;
import org.example.entity.Book;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

public class LibraryGUIDataHandler {

    protected final LibraryRepository libraryRepository;
    protected JTable table;
    protected JPanel jPanel;
    protected DefaultTableModel model;
    protected JScrollPane scrollPane ;
    protected JPanel controlPanel;
    protected Dimension size;

    public LibraryGUIDataHandler(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
        model = new DefaultTableModel();
        intView();

    }

    private void intView(){
        jPanel = new JPanel();
        tableFieldGenerator(Selector.WITHOUT_DATE);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        controlPanel = new JPanel(new GridLayout(5, 1,0,10));
        controlPanel.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(10, 10, 10, 10),
                BorderFactory.createEtchedBorder()));

        size = new Dimension(150,20);
    }

    protected int getSelectedRow(){
        return table.getSelectedRow();
    }

    protected int getBookIdFromSelectedRow(int selectedRow){
       return  (int) table.getValueAt(selectedRow, 0);
    }

    protected void tableFieldGenerator(Selector selector){
        if(selector == Selector.WITH_DATE){
            model.setColumnCount(0);
            model.addColumn("ID");
            model.addColumn("Title");
            model.addColumn("Author");
            model.addColumn("Available");
            model.addColumn("Due Date");
        }else{
            model.setColumnCount(0);
            model.addColumn("ID");
            model.addColumn("Title");
            model.addColumn("Author");
            model.addColumn("Available");
        }
    }

    protected void tableDataMapper(List<Book> allBooks){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (Book book : allBooks) {
            model.addRow(new Object[] { book.getId(), book.getTitle(), book.getAuthor(), book.isAvailable(),book.getDueDate() });
        }
    }

    protected void tableDataMapper(List<Book> allBooks, Predicate<Book> filter){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        List<Book> overDue = allBooks.stream().filter(filter).toList();
        model.setRowCount(0);
        for (Book book : overDue) {
            model.addRow(new Object[] { book.getId(), book.getTitle(), book.getAuthor(), book.isAvailable(),book.getDueDate() });
        }
    }

    protected int jOptionCreate(Object [] inputFields,String title,int jOptionPane){
        return JOptionPane.showConfirmDialog(jPanel, inputFields, "Add Book", jOptionPane);
    }

    protected int jOptionCreate(String inputFields,String title,int jOptionPane){
        return JOptionPane.showConfirmDialog(jPanel, inputFields, "Add Book", jOptionPane);
    }

    protected void messageShower(String message){
        JOptionPane.showMessageDialog(jPanel, message);
    }

    public JPanel getjPanel(){
        return jPanel;
    }
}
