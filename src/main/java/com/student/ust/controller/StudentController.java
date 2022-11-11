package com.student.ust.controller;

import com.student.ust.entity.Student;
import com.student.ust.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> get(@PathVariable Integer id)
    {
        try
        {
            Student student = studentService.getStudentById(id);
            return new ResponseEntity<Student>(student, HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/studentsRequestParam")
    public ResponseEntity<Student> getR(@RequestParam(name="id") Integer id)
    {
        try
        {
            Student student = studentService.getStudentById(id);
            return new ResponseEntity<Student>(student, HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/student")
    public void add(@RequestBody Student student)
    {
        studentService.saveStudent(student);
    }

    @GetMapping("/studentAll")
    public ResponseEntity<List<Student>> get()
    {
        try
        {
            List<Student> studentListFull = studentService.getAllStudent();
            return new ResponseEntity<List<Student>>(studentListFull, HttpStatus.OK);
        }
        catch(NoSuchElementException e)
        {
            return new ResponseEntity<List<Student>>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/studentDelete/{id}")
    public void delete(@PathVariable Integer id)
    {
        studentService.deleteStudent(id);
    }

    @PutMapping("/studentUpdate")
    public ResponseEntity<Student> update(@RequestBody Student student) //@PathVariable Integer id)
    {
        try
        {
            //Student student = studentService.getStudentById(id);
            Student updateStudent = studentService.updateStudent(student);
            return new ResponseEntity<Student>(updateStudent, HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/student-by-name/{name}")
    public ResponseEntity<Student> get(@PathVariable String name)
    {
        try
        {
            Student student = studentService.studentByName(name);
            return new ResponseEntity<Student>(student, HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
    }


}
