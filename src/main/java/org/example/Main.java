package org.example;

import org.example.Repository.Repository;
import org.example.Repository.RepositoryImpl;
import org.example.database.DataBase;
import org.example.entity.Book;

public class Main {
    public static void main(String[] args) {
        Repository<Book,Integer> bo = new RepositoryImpl<>(Book.class);
        Book b1= new Book();
        b1.setAuthor("Preminda1");
        b1.setTitle("Loard");
        b1.setBorrowed(false);

        bo.save(b1);

    }
}