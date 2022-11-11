package com.student.ust.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int tutorId;
    private String tutorName;

    @ManyToMany
    private List<Student> studentList;
}
