package org.example.ui.cli.commcands;

import org.example.Repository.LibraryRepository;
import org.example.entity.Book;
import org.example.ui.cli.interfaces.CLICommand;

import java.util.Optional;
import java.util.Scanner;

public class RemoveBook implements CLICommand {

    private final LibraryRepository libraryRepository;
    private final Scanner scanner;

    public RemoveBook(LibraryRepository libraryRepository, Scanner scanner) {
        this.libraryRepository = libraryRepository;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        System.out.print("Enter the book id: ");
        int id = Integer.parseInt(scanner.nextLine());
        Optional<Book> ob= libraryRepository.findById(id);
        if(ob.isPresent()){
            libraryRepository.deleteById(id);
            System.out.println("The book has been removed from the library.");
        }else{
            System.out.println("The book is not in the library.");
        }

    }

    @Override
    public String optionDes() {
        return "Delete a Book from Library";
    }
}
