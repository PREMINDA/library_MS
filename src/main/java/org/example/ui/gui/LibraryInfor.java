package org.example.ui.gui;

import org.example.Repository.LibraryRepository;
import org.example.entity.Book;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LibraryInfor extends LibraryGUIDataHandler {

    public LibraryInfor(LibraryRepository libraryRepository) {
        super(libraryRepository);
        createGUI();
    }

    private void createGUI() {

        JButton addButton = new JButton("Add Book");
        addButton.addActionListener(e -> addBook());
        addButton.setPreferredSize(size);
        controlPanel.add(addButton);

        JButton removeButton = new JButton("Remove Book");
        removeButton.addActionListener(e -> removeBook());
        removeButton.setPreferredSize(size);
        controlPanel.add(removeButton);

        JButton allButton = new JButton("All Books");
        allButton.addActionListener(e -> getAllBooks());
        allButton.setPreferredSize(size);
        controlPanel.add(allButton);

        controlPanel.setPreferredSize(new Dimension(180,440));
        jPanel.add(controlPanel);
        jPanel.add(scrollPane);

        getAllBooks();
    }

    private void removeBook() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            messageShower("Please select a book to remove.");
            return;
        }
        int bookId = (int) table.getValueAt(selectedRow, 0);
        int result = jOptionCreate("Are you sure you want to remove this book?", "Remove Book", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            libraryRepository.deleteById(bookId);
            getAllBooks();
        }
    }

    private void addBook() {
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
            libraryRepository.save(b);
            getAllBooks();
        }
    }

    private Book objectCreate(JTextField titleF, JTextField authorF){
        Book b = new Book();
        b.setTitle(titleF.getText());
        b.setAuthor(authorF.getText());
        return b;
    }

    private void getAllBooks() {
        java.util.List<Book> allBooks = libraryRepository.findAll();
        tableDataMapper(allBooks);
    }

}
