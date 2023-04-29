package org.example.commands;

import org.example.Services.LibraryService;
import org.example.entity.Book;
import org.example.enums.Selector;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.function.Predicate;

public class GuiCommands extends GuiHelper implements Commands{

    public GuiCommands(JPanel jPanel, JTable table, LibraryService libraryService, DefaultTableModel model) {
        super(jPanel, table, libraryService,model);
        tableFieldGenerator(Selector.WITHOUT_DATE);
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
        //        state = OptionState.RETURN;
        tableFieldGenerator(Selector.WITH_DATE);
        Predicate<Book> isOverDue = book -> book.getDueDate().isBefore(LocalDate.now());
        tableDataMapper(libraryService.getAllNonAvailableBook(),isOverDue);
    }

    @Override
    public void allNonAvailableBook() {
        //        state = OptionState.RETURN;
        tableFieldGenerator(Selector.WITH_DATE);
        tableDataMapper(libraryService.getAllNonAvailableBook());
    }

    @Override
    public void allAvailableBook() {
        //        state = OptionState.BURROW;
        tableFieldGenerator(Selector.WITHOUT_DATE);
        tableDataMapper(libraryService.getAllAvailableBook());
    }

    @Override
    public void allBooks() {
        java.util.List<Book> allBooks = libraryService.getAllBook();
        tableDataMapper(allBooks);
    }
}
