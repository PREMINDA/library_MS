package org.example.ui.cli;

import org.example.Services.LibraryService;
import org.example.commands.CliCommands;
import org.example.commands.Commands;
import org.example.constant.CliConstant;
import org.example.ui.interfaces.UI;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LibraryCLI implements UI {

    private final Scanner scanner;
    private final Commands cliCommands;
    int choice;

    public LibraryCLI(LibraryService libraryService) {
        this.scanner = new Scanner(System.in);
        this.cliCommands = new CliCommands(libraryService,scanner);
    }
    @Override
    public void show() {
        startCli();
    }

    private void startCli() {
        System.out.println("\n-------------Welcome to the Library Management System.-----------\n");

        try {
            do {
                System.out.println(CliConstant.PROMPT);
                choice = getChoice();
                switch (choice) {
                    case 1 -> cliCommands.addBook();
                    case 2 -> cliCommands.removeBook();
                    case 3 -> cliCommands.allAvailableBook();
                    case 4 -> cliCommands.borrowBook();
                    case 5 -> cliCommands.returnBook();
                    case 6 -> cliCommands.allNonAvailableBook();
                    case 7 -> cliCommands.allBooks();
                    case 8 -> cliCommands.overdueBooks();
                    case 9 -> System.out.println("Exit from the CLI");
                    default -> System.out.println("Invalid Input");
                }
            } while (choice != 9);
        } catch (NumberFormatException e) {
            e.printStackTrace();
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



