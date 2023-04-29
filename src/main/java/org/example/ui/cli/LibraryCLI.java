package org.example.ui.cli;

import org.example.Repository.LibraryRepository;
import org.example.ui.interfaces.UI;
import org.example.ui.cli.commcands.*;
import org.example.ui.cli.interfaces.CLICommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LibraryCLI implements UI {

    private final LibraryRepository libraryRepository;
    private final Scanner scanner;
    private final Map<Integer, CLICommand> commands;

    public LibraryCLI(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
        commands = new HashMap<>();
        this.scanner = new Scanner(System.in);
        addCommand();
    }
    @Override
    public void show() {
        startCli();
    }

    public void addCommand() {
        this.commands.put(1, new AddNewBook(libraryRepository, scanner));
        this.commands.put(2, new RemoveBook(libraryRepository, scanner));
        this.commands.put(3, new ShowAllBooks(libraryRepository));
        this.commands.put(4, new ShowAllAvailableBooks(libraryRepository));
        this.commands.put(5, new ShowOptions(commands));
        this.commands.put(6, new ExitCLI());
    }

    private void startCli() {
        System.out.println("\n-------------Welcome to the Library Management System.-----------\n");

        CLICommand command = commands.get(5);
        command.run();
        while (true) {
            int choice = getChoice();
            command = commands.get(choice);
            if (command != null) {
                command.run();
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
            System.out.println("");

            commands.get(5).run();
        }
    }

    private int getChoice() {
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println("");
        return choice;
    }
}



