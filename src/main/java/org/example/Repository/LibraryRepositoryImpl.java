package org.example.Repository;

import org.example.entity.Book;

import java.util.HashMap;
import java.util.List;

public class LibraryRepositoryImpl extends RepositoryImpl<Book,Integer> implements LibraryRepository {
    public LibraryRepositoryImpl() {
        super(Book.class);
    }
    @Override
    public List<Book> getAllAvailableBook(){
        String hql = "FROM Book B WHERE B.isAvailable = :isAvailable";
        HashMap<String,Object> map = new HashMap<>();
        map.put("isAvailable",true);
        return runCustomQuery(hql,map);
    }

    @Override
    public List<Book> getAllNonAvailableBook() {
        String hql = "FROM Book B WHERE B.isAvailable = :isAvailable";
        HashMap<String,Object> map = new HashMap<>();
        map.put("isAvailable",false);
        return runCustomQuery(hql,map);
    }
}
