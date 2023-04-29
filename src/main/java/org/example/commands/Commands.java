package org.example.commands;

public interface Commands {
    void addBook();

    void removeBook();

    void borrowBook();

    void returnBook();


    void overdueBooks();

    void allNonAvailableBook();

    void allAvailableBook();

    void allBooks();
}
