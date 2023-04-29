package org.example.commands;

import org.example.Repository.LibraryRepository;
import org.example.Services.LibraryService;
import org.example.entity.Book;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;

public class CliCommands implements Commands{

    private final LibraryService libraryService;
    private final Scanner scanner;
    public CliCommands(LibraryService libraryService, Scanner scanner) {
        this.scanner = scanner;
        this.libraryService = libraryService;
    }

    @Override
    public void addBook() {
        System.out.print("Enter the book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter the book author: ");
        String author = scanner.nextLine();
        Book b = new Book.BookBuilder().title(title).author(author).isAvailable(true).build();
        Book isSuccess = libraryService.saveBook(b);
        if (isSuccess != null) {
            System.out.println("The book has been added to the library.");
        }
    }

    @Override
    public void removeBook() {
        System.out.print("Enter the book id: ");
        int id = Integer.parseInt(scanner.nextLine());
        Book b= libraryService.findById(id);
        if(b != null){
            libraryService.deleteById(id);
            System.out.println("The book has been removed from the library.");
        }else{
            System.out.println("The book is not in the library.");
        }
    }

    @Override
    public void borrowBook() {
        System.out.println("To DO");
    }

    @Override
    public void returnBook() {
        System.out.println("To DO");
    }

    @Override
    public void overdueBooks() {
        List<Book> allBooks = libraryService.getAllNonAvailableBook();
        Predicate<Book> isOverDue = book -> book.getDueDate().isBefore(LocalDate.now());
        List<Book> overDue = allBooks.stream().filter(isOverDue).toList();
        System.out.format("%-5s %-30s %-20s %-10s\n", "ID", "Title", "Author", "Available");
        for (Book book : overDue) {
            System.out.format("%-5s %-30s %-20s %-10s\n", book.getId(), book.getTitle(),
                    book.getAuthor(), book.isAvailable() ? "Yes" : "No");
        }
    }

    @Override
    public void allNonAvailableBook() {
        List<Book> allBooks = libraryService.getAllNonAvailableBook();
        System.out.format("%-5s %-30s %-20s %-10s\n", "ID", "Title", "Author", "Available");
        for (Book book : allBooks) {
            System.out.format("%-5s %-30s %-20s %-10s\n", book.getId(), book.getTitle(),
                    book.getAuthor(), book.isAvailable() ? "Yes" : "No");
        }
    }

    @Override
    public void allAvailableBook() {
        List<Book> allBooks = libraryService.getAllAvailableBook();
        System.out.format("%-5s %-30s %-20s %-10s\n", "ID", "Title", "Author", "Available");
        for (Book book : allBooks) {
            System.out.format("%-5s %-30s %-20s %-10s\n", book.getId(), book.getTitle(),
                    book.getAuthor(), book.isAvailable() ? "Yes" : "No");
        }
    }

    @Override
    public void allBooks() {
        List<Book> allBooks = libraryService.getAllBook();
        System.out.format("%-5s %-30s %-20s %-10s\n", "ID", "Title", "Author", "Available");
        for (Book book : allBooks) {
            System.out.format("%-5s %-30s %-20s %-10s\n", book.getId(), book.getTitle(),
                    book.getAuthor(), book.isAvailable() ? "Yes" : "No");
        }
    }
}
