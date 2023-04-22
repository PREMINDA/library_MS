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

}
