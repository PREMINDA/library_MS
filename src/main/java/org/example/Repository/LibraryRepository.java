package org.example.Repository;

import org.example.entity.Book;

import java.util.List;

public interface LibraryRepository extends Repository <Book, Integer> {
    List<Book> getAllAvailableBook();
    List<Book> getAllNonAvailableBook();
}
