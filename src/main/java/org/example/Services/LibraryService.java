package org.example.Services;

import org.example.Repository.LibraryRepository;
import org.example.entity.Book;

import java.util.List;
import java.util.Optional;

public interface LibraryService  {

    public Book findById(Integer id);
    public  void deleteById(Integer id);
    public Book saveBook(Book entity);
    public List<Book> getAllBook();
    public void updateBook(Book entity);
    List<Book> getAllAvailableBook();
    List<Book> getAllNonAvailableBook();

}
