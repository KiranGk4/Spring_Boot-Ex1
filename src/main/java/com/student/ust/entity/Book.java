package com.student.ust.entity;

import lombok.Data;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "book_ust_mappedBy")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    private String bookName;
    private String author;
    private long isbn;
    private LocalDateTime createDateTime;
    private LocalDateTime modifiedDateTime;

    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;
}
