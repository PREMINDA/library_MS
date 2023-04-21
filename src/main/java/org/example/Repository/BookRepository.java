package org.example.Repository;

import org.example.entity.Book;

import java.util.List;

public interface BookRepository extends Repository <Book, Integer> {
    List<Book> getAllBorrowedBook();
    List<Book> getAllNonBorrowedBook();
}
