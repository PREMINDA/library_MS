package org.example.entity;

import lombok.*;

import javax.persistence.*;

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
    private boolean isBorrowed;

}
