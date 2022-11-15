package com.student.ust.entity;

import lombok.Data;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * The type Student.
 */
@Entity
@Data
@Table(name = "student_ust_mappedBy")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;
    private int age;
    private int rollNumber;
    private String name;
    private LocalDateTime createDateTime;
    private LocalDateTime modifiedDateTime;

    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "student")
    @Column(name = "book_id")
    private Set<Book> bookSet;

}
