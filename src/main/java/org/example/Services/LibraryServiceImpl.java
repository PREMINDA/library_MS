package org.example.Services;

import org.example.Repository.LibraryRepository;
import org.example.entity.Book;

import java.util.List;
import java.util.Optional;

public class LibraryServiceImpl implements LibraryService {
    private final LibraryRepository libraryRepository;

    public LibraryServiceImpl(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @Override
    public Book findById(Integer id) {
        Optional<Book> ob = libraryRepository.findById(id);
        return ob.orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        libraryRepository.deleteById(id);
    }

    @Override
    public Book saveBook(Book entity) {
        return libraryRepository.save(entity);
    }

    @Override
    public List<Book> getAllBook() {
        return libraryRepository.getAll();
    }

    @Override
    public void updateBook(Book entity) {
        libraryRepository.update(entity);
    }

    @Override
    public List<Book> getAllAvailableBook() {
        return libraryRepository.getAllAvailableBook();
    }

    @Override
    public List<Book> getAllNonAvailableBook() {
        return libraryRepository.getAllNonAvailableBook();
    }
}
