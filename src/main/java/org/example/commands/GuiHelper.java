package org.example.commands;

import org.example.Services.LibraryService;
import org.example.entity.Book;
import org.example.enums.Selector;
import org.jdatepicker.DateModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

public class GuiHelper {
    protected final JPanel jPanel;
    protected final JTable table;
    protected final LibraryService libraryService;
    protected DefaultTableModel model;

    public GuiHelper(JPanel jPanel, JTable table,LibraryService libraryService,DefaultTableModel model) {
        this.jPanel = jPanel;
        this.table = table;
        this.libraryService = libraryService;
        this.model = model;
    }

    protected int getSelectedRow(){
        return table.getSelectedRow();
    }
    protected int getBookIdFromSelectedRow(int selectedRow){
        return  (int) table.getValueAt(selectedRow, 0);
    }

    protected int jOptionCreate(String inputFields,String title,int jOptionPane){
        return JOptionPane.showConfirmDialog(jPanel, inputFields, "Add Book", jOptionPane);
    }

    protected int jOptionCreate(Object [] inputFields,String title,int jOptionPane){
        return JOptionPane.showConfirmDialog(jPanel, inputFields, "Add Book", jOptionPane);
    }

    protected Book objectCreate(JTextField titleF, JTextField authorF){
        Book b = new Book.BookBuilder().title(titleF.getText()).author(authorF.getText()).build();
        return b;
    }

    protected Book objectCreate(Book b, DateModel<?> model){
        LocalDate ld = LocalDate.of(model.getYear(),model.getMonth()+1,model.getDay());
        b.setAvailable(false);
        return b;
    }

    protected void messageShower(String message){
        JOptionPane.showMessageDialog(jPanel, message);
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

}
