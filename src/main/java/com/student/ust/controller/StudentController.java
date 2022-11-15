package com.student.ust.controller;

import com.student.ust.dto.StudentDTO;
import com.student.ust.entity.Student;
import com.student.ust.exceptions.BusinessException;
import com.student.ust.exceptions.InvalidEmail;
import com.student.ust.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The type Student controller.
 */
@RestController
public class StudentController {

    /**
     * The Student service.
     */
    @Autowired
    StudentService studentService;

    @Autowired
    ModelMapper modelMapper;

    /**
     * Get response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/student/{id}")
    public ResponseEntity<StudentDTO> get(@PathVariable Integer id)
    {
        try
        {
            Student student = studentService.getStudentById(id);
            StudentDTO studentDTO = studentService.convertEntityToDTO(student);
            return new ResponseEntity<StudentDTO>(studentDTO, HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<StudentDTO>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Gets r.
     *
     * @param id the id
     * @return the r
     */
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

    /**
     * Add response entity.
     *
     * @param student the student
     * @return the response entity
     */
    @PostMapping("/student")
    public ResponseEntity<Student> add(@RequestBody Student student)
    {
        try
        {
            Student student1 = studentService.saveStudent(student);
            return new ResponseEntity<Student>(student1, HttpStatus.OK);
        }
        catch (BusinessException e)
        {
            return new ResponseEntity<Student>(HttpStatus.PRECONDITION_FAILED);
        }
        catch (NoSuchAlgorithmException e)
        {
            return new ResponseEntity<Student>(HttpStatus.PRECONDITION_FAILED);
        }
        //studentService.saveStudent(student);
    }

    /**
     * Get response entity.
     *
     * @return the response entity
     */
    @GetMapping("/studentAll")
    public ResponseEntity<List<StudentDTO>> get()
    {
        try
        {
            List<Student> studentListFull = studentService.getAllStudent();
            List<StudentDTO> studentDTOList = studentService.convertEntityToDTO(studentListFull);
            return new ResponseEntity<List<StudentDTO>>(studentDTOList, HttpStatus.OK);
        }
        catch(NoSuchElementException e)
        {
            return new ResponseEntity<List<StudentDTO>>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    @DeleteMapping("/studentDelete/{id}")
    public void delete(@PathVariable Integer id)
    {
        studentService.deleteStudent(id);
    }

    /**
     * Update response entity.
     *
     * @param student the student
     * @return the response entity
     */
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

    /**
     * Get response entity.
     *
     * @param name the name
     * @return the response entity
     */
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
