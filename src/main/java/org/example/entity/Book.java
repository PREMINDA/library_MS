package org.example.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="book")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String author;
    private String title;
    private boolean isAvailable;
    private LocalDate dueDate;

    public Book(BookBuilder bookBuilder) {
        this.author = bookBuilder.author;
        this.title = bookBuilder.title;
        this.isAvailable = bookBuilder.isAvailable;
        this.dueDate = bookBuilder.dueDate;

    }

    public static class BookBuilder {
        private String title;
        private String author;
        private boolean isAvailable;

        private LocalDate dueDate;

        public BookBuilder title(String title) {
            this.title = title;
            return this;
        }

        public BookBuilder author(String author) {
            this.author = author;
            return this;
        }

        public BookBuilder isAvailable(boolean isAvailable) {
            this.isAvailable = isAvailable;
            return this;
        }

        public BookBuilder dueDate(LocalDate dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }
}
