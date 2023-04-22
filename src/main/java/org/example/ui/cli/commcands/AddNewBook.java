package org.example.ui.cli.commcands;

import org.example.Repository.LibraryRepository;
import org.example.entity.Book;
import org.example.ui.cli.interfaces.CLICommand;

import java.util.Scanner;

public class AddNewBook implements CLICommand {

    private final LibraryRepository libraryRepository;
    private final Scanner scanner;

    public AddNewBook(LibraryRepository libraryRepository, Scanner scanner) {
        this.libraryRepository = libraryRepository;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        System.out.print("Enter the book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter the book author: ");
        String author = scanner.nextLine();
        Book b = new Book();
        b.setTitle(title);
        b.setAuthor(author);
        b.setAvailable(true);
        Book isSuccess = libraryRepository.save(b);
        if (isSuccess != null) {
            System.out.println("The book has been added to the library.");
        }
    }

    @Override
    public String optionDes() {
        return "Add New Book to Library";
    }
}
