package com.student.ust.service;

import com.student.ust.dto.StudentDTO;
import com.student.ust.entity.Student;
import com.student.ust.exceptions.BusinessException;
import com.student.ust.exceptions.InvalidEmail;
import com.student.ust.exceptions.InvalidPassword;
import com.student.ust.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.student.ust.util.UstUtil.*;

/**
 * The type Student service.
 */
@Service
@Slf4j
public class StudentService {

    /**
     * The Student repository.
     */
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ModelMapper modelMapper;

    /**
     * Gets student by id.
     *
     * @param id the id
     * @return the student by id
     */
    public Student getStudentById(Integer id) {

        return studentRepository.findById(id).orElseThrow(()-> new NoSuchElementException());
    }

    private void getStudentByName(String s) {
        Student studentByName=studentRepository.findByName(s);
        log.debug("Student name is >>>>>>>>>>>> " +studentByName.getName());
    }

    /**
     * Save student student.
     *
     * @param student the student
     * @return the student
     * @throws BusinessException the business exception
     */
    public Student saveStudent(Student student) throws BusinessException, NoSuchAlgorithmException {
        boolean validEmail = validateEmail(student.getEmail());
        boolean validPassword = validatePassword(student.getPassword());
        if(validEmail && validPassword) {
            student.setCreateDateTime(LocalDateTime.now());
            student.setModifiedDateTime(student.getCreateDateTime());
            String hashPassword = hashPassword(student.getPassword());
            student.setPassword(hashPassword);
            return studentRepository.save(student);
        }
        else if(!validPassword)
        {
            throw new InvalidPassword();
        }
        else
        {
            throw new InvalidEmail();
        }
    }

    /**
     * Gets all student.
     *
     * @return the all student
     */
    public List<Student> getAllStudent() {
        log.debug("All Students");
        return studentRepository.findAll();
    }

    /**
     * Delete student.
     *
     * @param id the id
     */
    public void deleteStudent(Integer id) {

        studentRepository.deleteById(id);
    }

    /**
     * Update student student.
     *
     * @param student the student
     * @return the student
     */
    public Student updateStudent(Student student){
        Student updateStudent = studentRepository.findById(student.getStudentId()).orElseThrow(()-> new NoSuchElementException());
        updateStudent.setAge(student.getAge());
        updateStudent.setRollNumber(student.getRollNumber());
        updateStudent.setModifiedDateTime(LocalDateTime.now());
        studentRepository.save(updateStudent);
        return updateStudent;
    }

    /**
     * Student by name student.
     *
     * @param name the name
     * @return the student
     */
    public Student studentByName(String name){

        List<Student> studentList = studentRepository.findByNameStartingWith("N");
        Iterator<Student> itr = studentList.iterator();
        return studentRepository.findByName(name);
    }

    /**
     * Convert entity to dto student dto.
     *
     * @param student the student
     * @return the student dto
     */
    public StudentDTO convertEntityToDTO(Student student)
    {
        StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
        return studentDTO;
    }

    /**
     * Convert entity to dto list.
     *
     * @param studentDTOList the student dto list
     * @return the list
     */
    public List<StudentDTO> convertEntityToDTO(List<Student> studentDTOList)
    {
        return studentDTOList.stream().map(e->modelMapper.map(e, StudentDTO.class)).collect(Collectors.toList());
    }

}
