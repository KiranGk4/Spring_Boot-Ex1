package com.student.ust.service;

import com.student.ust.entity.Student;
import com.student.ust.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    public Student getStudentById(Integer id) {

       // Student studentById= studentRepository.findById(id).orElse(null);
        //getStudentByName("Student name is ....."+ studentById.getName());
        //return studentById;
        return studentRepository.findById(id).orElseThrow(()-> new NoSuchElementException());
    }

    private void getStudentByName(String s) {
        Student studentByName=studentRepository.findByName(s);
        System.out.printf("Student name is >>>>>>>>>>>> " +studentByName.getName());
        System.out.printf("Student age is >>>>>>>>>>>> " +studentByName.getAge());
    }

    public void saveStudent(Student student) {
        student.setCreateDateTime(LocalDateTime.now());
        student.setModifiedDateTime(student.getCreateDateTime());
        studentRepository.save(student);
    }

    public List<Student> getAllStudent() {

        return studentRepository.findAll();
    }

    public void deleteStudent(Integer id) {

        studentRepository.deleteById(id);
    }

    public Student updateStudent(Student student){
        Student updateStudent = studentRepository.findById(student.getStudentId()).orElseThrow(()-> new NoSuchElementException());
        updateStudent.setAge(student.getAge());
        updateStudent.setRollNumber(student.getRollNumber());
        updateStudent.setModifiedDateTime(LocalDateTime.now());
        studentRepository.save(updateStudent);
        return updateStudent;
    }

    public Student studentByName(String name){
        System.out.println("Hi");
        System.out.println(studentRepository.findByNameEquals("Kiran").getName());
        List<Student> studentList = studentRepository.findByNameStartingWith("N");
        Iterator<Student> itr = studentList.iterator();
        while(itr.hasNext())
            System.out.println(itr.next());

        System.out.println(studentRepository.findStudentById(2));

        //System.out.println(studentRepository.findStudent(id).getName());
        //System.out.printf(studentRepository.findByNameEndingWith("an"));
        return studentRepository.findByName(name);
    }


   // public List<Book> getStudentBook(Student student){
       // List<Book> book = bookRepository.findById(student.getStudentId()).orElseThrow(()->new NoSuchElementException());
    //}
}
