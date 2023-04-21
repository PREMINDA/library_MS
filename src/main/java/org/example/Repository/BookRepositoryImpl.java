package org.example.Repository;

import org.example.entity.Book;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;

public class BookRepositoryImpl extends RepositoryImpl<Book,Integer> implements BookRepository{
    public BookRepositoryImpl() {
        super(Book.class);
    }
    @Override
    public List<Book> getAllBorrowedBook(){
        String hql = "FROM Book B WHERE B.isBorrowed = :isBorrowed";
        HashMap<String,Object> map = new HashMap<>();
        map.put("isBorrowed",false);
        return runCustomQuery(hql,map);
    }

    @Override
    public List<Book> getAllNonBorrowedBook() {
        String hql = "FROM Book B WHERE B.isBorrowed = :isBorrowed";
        HashMap<String,Object> map = new HashMap<>();
        map.put("isBorrowed",false);
        return runCustomQuery(hql,map);
    }
}
