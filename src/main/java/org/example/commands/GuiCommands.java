package org.example.commands;

import org.example.Services.LibraryService;
import org.example.entity.Book;
import org.example.enums.OptionState;
import org.example.enums.Selector;
import org.jdatepicker.DateModel;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

public class GuiCommands implements Commands{

    private final JPanel jPanel;
    private final JTable table;
    private final LibraryService libraryService;
    private final DefaultTableModel model;
    private OptionState optionState;


    public GuiCommands(JPanel jPanel, JTable table,LibraryService libraryService,DefaultTableModel model) {
        this.jPanel = jPanel;
        this.table = table;
        this.libraryService = libraryService;
        this.model = model;
        this.optionState = OptionState.BURROW;
    }
    @Override
    public void addBook() {
        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        Object[] inputFields = {
                "Title:", titleField,
                "Author:", authorField,
        };
        int result = jOptionCreate(inputFields, "Add Book", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            Book b = objectCreate(titleField,authorField);
            b.setAvailable(true);
            libraryService.saveBook(b);
            allBooks();
        }
    }

    @Override
    public void removeBook() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            messageShower("Please select a book to remove.");
            return;
        }
        int bookId = (int) table.getValueAt(selectedRow, 0);
        int result = jOptionCreate("Are you sure you want to remove this book?", "Remove Book", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            libraryService.deleteById(bookId);
            allBooks();
        }
    }

    @Override
    public void borrowBook() {
        if(optionState != OptionState.BURROW){
            JOptionPane.showMessageDialog(jPanel, "Please select available books first.");
            return;
        }
        int selectedRow = getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(jPanel, "Please select a book.");
            return;
        }
        int bookId = getBookIdFromSelectedRow(selectedRow);
        JDatePicker dueDate = new JDatePicker();
        Object[] inputFields = {
                "Due Date", dueDate
        };
        int result = jOptionCreate(inputFields, "Add Book", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            Book ob = libraryService.findById(bookId);
            if(ob != null){
                Book b = objectCreate(ob,dueDate.getModel());
                libraryService.updateBook(b);
            }else return;
            allAvailableBook();
        }
    }

    @Override
    public void returnBook() {
        if(optionState != OptionState.RETURN){
            JOptionPane.showMessageDialog(jPanel, "Please select non available books first.");
            return;
        }
        int selectedRow = getSelectedRow();
        if (selectedRow == -1) {
            messageShower("Please select a book");
            return;
        }
        int bookId = getBookIdFromSelectedRow(selectedRow);
        int result = jOptionCreate("Make sure you received a book", "Add Book", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            Book b = libraryService.findById(bookId);
            if(b != null){
                b.setAvailable(true);
                libraryService.updateBook(b);
            }else return;
            allNonAvailableBook();
        }
    }

    @Override
    public void overdueBooks() {
        optionState = OptionState.RETURN;
        tableFieldGenerator(Selector.WITH_DATE);
        Predicate<Book> isOverDue = book -> book.getDueDate().isBefore(LocalDate.now());
        tableDataMapper(libraryService.getAllNonAvailableBook(),isOverDue);
    }

    @Override
    public void allNonAvailableBook() {
        optionState = OptionState.RETURN;
        tableFieldGenerator(Selector.WITH_DATE);
        tableDataMapper(libraryService.getAllNonAvailableBook());
    }

    @Override
    public void allAvailableBook() {
        optionState = OptionState.BURROW;
        tableFieldGenerator(Selector.WITHOUT_DATE);
        tableDataMapper(libraryService.getAllAvailableBook());
    }

    @Override
    public void allBooks() {
        tableFieldGenerator(Selector.WITHOUT_DATE);
        java.util.List<Book> allBooks = libraryService.getAllBook();
        tableDataMapper(allBooks);
    }

    private int getSelectedRow(){
        return table.getSelectedRow();
    }

    private int getBookIdFromSelectedRow(int selectedRow){
        return  (int) table.getValueAt(selectedRow, 0);
    }

    private int jOptionCreate(String inputFields,String title,int jOptionPane){
        return JOptionPane.showConfirmDialog(jPanel, inputFields, "Add Book", jOptionPane);
    }

    private int jOptionCreate(Object [] inputFields,String title,int jOptionPane){
        return JOptionPane.showConfirmDialog(jPanel, inputFields, title, jOptionPane);
    }

    private Book objectCreate(JTextField titleF, JTextField authorF){
        return new Book.BookBuilder().title(titleF.getText()).author(authorF.getText()).build();
    }

    private Book objectCreate(Book b, DateModel<?> model){
        LocalDate ld = LocalDate.of(model.getYear(),model.getMonth()+1,model.getDay());
        b.setDueDate(ld);
        b.setAvailable(false);
        return b;
    }

    private void messageShower(String message){
        JOptionPane.showMessageDialog(jPanel, message);
    }

    private void tableFieldGenerator(Selector selector){
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

    private void tableDataMapper(List<Book> allBooks){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (Book book : allBooks) {
            model.addRow(new Object[] { book.getId(), book.getTitle(), book.getAuthor(), book.isAvailable(),book.getDueDate() });
        }
    }

    private void tableDataMapper(List<Book> allBooks, Predicate<Book> filter){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        List<Book> overDue = allBooks.stream().filter(filter).toList();
        model.setRowCount(0);
        for (Book book : overDue) {
            model.addRow(new Object[] { book.getId(), book.getTitle(), book.getAuthor(), book.isAvailable(),book.getDueDate() });
        }
    }
}
