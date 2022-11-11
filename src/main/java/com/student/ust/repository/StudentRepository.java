package com.student.ust.repository;

import com.student.ust.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

    Student findByName(String name);

    Student findByNameEquals(String k);

    List<Student> findByNameStartingWith(String nam);

    //String findByNameEndingWith(String an);
    @Query("SELECT s FROM Student s WHERE s.studentId=?1")
    Student findStudentById(Integer id);
}
