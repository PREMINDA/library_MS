package org.example.ui.gui;

import org.example.Repository.LibraryRepository;
import org.example.entity.Book;
import org.jdatepicker.DateModel;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class BurrowBooks extends LibraryGUIDataHandler {


    public BurrowBooks(LibraryRepository libraryRepository) {
        super(libraryRepository);
        createGUI();
    }

    private void createGUI() {

        JButton addButton = new JButton("Borrowing");
        addButton.addActionListener(e -> burrowing());
        addButton.setPreferredSize(size);
        controlPanel.add(addButton);

        JButton removeButton = new JButton("Handover");
        removeButton.addActionListener(e -> handover());
        removeButton.setPreferredSize(size);
        controlPanel.add(removeButton);

        JButton allButton = new JButton("All Non Available");
        allButton.addActionListener(e -> mapAllNonAvailableBook());
        allButton.setPreferredSize(size);
        controlPanel.add(allButton);

        JButton burrowedButton = new JButton("Available Books");
        burrowedButton.addActionListener(e -> mapAllAvailable());
        burrowedButton.setPreferredSize(size);
        controlPanel.add(burrowedButton);

        JButton overDueButton = new JButton("Overdue Books");
        overDueButton.addActionListener(e -> getAllOverDueBook());
        overDueButton.setPreferredSize(size);
        controlPanel.add(overDueButton);

        controlPanel.setPreferredSize(new Dimension(180,440));
        jPanel.add(controlPanel);
        jPanel.add(scrollPane);

        mapAllAvailable();
    }

    private void handover() {
        int selectedRow = getSelectedRow();
        if (selectedRow == -1) {
            messageShower("Please select a book");
            return;
        }
        int bookId = getBookIdFromSelectedRow(selectedRow);
        int result = jOptionCreate("Make sure you received a book", "Add Book", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            Optional<Book> ob = libraryRepository.findById(bookId);
            if(ob.isPresent()){
                Book b = ob.get();
                b.setAvailable(true);
                libraryRepository.update(b);
            }else return;
            mapAllNonAvailableBook();
        }
    }

    private void burrowing() {
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
            Optional<Book> ob = libraryRepository.findById(bookId);
            if(ob.isPresent()){
                Book b = objectCreate(ob.get(),dueDate.getModel());
                libraryRepository.update(b);
            }else return;
            mapAllAvailable();
        }
    }

    private Book objectCreate(Book b,DateModel<?> model){
        b.setDueDate(LocalDate.of(model.getYear(),model.getMonth()+1,model.getDay()));
        b.setAvailable(false);
        return b;
    }

    private void mapAllAvailable() {
        tableFieldGenerator(Selector.WITHOUT_DATE);
        List<Book> allBooks = libraryRepository.getAllAvailableBook();
        tableDataMapper(allBooks);
    }

    private void mapAllNonAvailableBook() {
        tableFieldGenerator(Selector.WITH_DATE);
        tableDataMapper(allNonAvailableBook());
    }

    private void getAllOverDueBook(){
        tableFieldGenerator(Selector.WITH_DATE);
        Predicate<Book> isOverDue = book -> book.getDueDate().isBefore(LocalDate.now());
        tableDataMapper(allNonAvailableBook(),isOverDue);
    }

    private List<Book> allNonAvailableBook(){
        return libraryRepository.getAllNonAvailableBook();
    }


}
