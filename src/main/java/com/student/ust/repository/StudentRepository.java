package com.student.ust.repository;

import com.student.ust.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Student repository.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

    /**
     * Find by name student.
     *
     * @param name the name
     * @return the student
     */
    Student findByName(String name);

    /**
     * Find by name equals student.
     *
     * @param k the k
     * @return the student
     */
    Student findByNameEquals(String k);

    /**
     * Find by name starting with list.
     *
     * @param nam the nam
     * @return the list
     */
    List<Student> findByNameStartingWith(String nam);

    /**
     * Find student by id student.
     *
     * @param id the id
     * @return the student
     */
//String findByNameEndingWith(String an);
    @Query("SELECT s FROM Student s WHERE s.studentId=?1")
    Student findStudentById(Integer id);
}
