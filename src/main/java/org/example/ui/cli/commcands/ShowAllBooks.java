package org.example.ui.cli.commcands;

import org.example.Repository.LibraryRepository;
import org.example.entity.Book;
import org.example.ui.cli.interfaces.CLICommand;

import java.util.List;

public class ShowAllBooks implements CLICommand {

    private final LibraryRepository libraryRepository;

    public ShowAllBooks(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @Override
    public void run() {
        List<Book> allBooks = libraryRepository.getAll();
        System.out.format("%-5s %-30s %-20s %-10s\n", "ID", "Title", "Author", "Available");
        for (Book book : allBooks) {
            System.out.format("%-5s %-30s %-20s %-10s\n", book.getId(), book.getTitle(),
                    book.getAuthor(), book.isAvailable() ? "Yes" : "No");
        }
    }

    @Override
    public String optionDes() {
        return "Display all books in library";
    }
}



