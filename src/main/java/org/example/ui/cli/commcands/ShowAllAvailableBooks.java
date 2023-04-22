package org.example.ui.cli.commcands;

import org.example.Repository.LibraryRepository;
import org.example.entity.Book;
import org.example.ui.cli.interfaces.CLICommand;

import java.util.List;

public class ShowAllAvailableBooks implements CLICommand {

    private final LibraryRepository libraryRepository;
    public ShowAllAvailableBooks(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @Override
    public void run() {
        List<Book> allBooks = libraryRepository.getAllAvailableBook();
        System.out.format("%-5s %-30s %-20s %-10s\n", "ID", "Title", "Author", "Available");
        for (Book book : allBooks) {
            System.out.format("%-5s %-30s %-20s %-10s\n", book.getId(), book.getTitle(),
                    book.getAuthor(), book.isAvailable() ? "Yes" : "No");
        }
    }

    @Override
    public String optionDes() {
        return "Show All Available Book in Library";
    }
}
