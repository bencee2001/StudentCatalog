package com.syscode.StudentCatalog.service;

import com.syscode.StudentCatalog.dto.StudentUpdateDto;
import com.syscode.StudentCatalog.libs.errors.NoSuchStudentException;
import com.syscode.StudentCatalog.model.Student;
import com.syscode.StudentCatalog.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class StudentService {

    private final StudentRepository studentRepository;

    /**
     * Gives back the list of Students
     * @return list of students
     */
    public List<Student> getStudents() {
        log.info("The list has been requested");
        return studentRepository.findAll();
    }

    /**
     * Saves the Student
     * @param student Valid Student object
     * @return Saved Student(with generated UUID)
     */
    public Student saveStudent(Student student) {
        Student savedStudent = studentRepository.save(student);
        log.info(savedStudent.getName() + " was saved with the id " + savedStudent.getId());
        return savedStudent;
    }

    /**
     * Deletes the student by id
     * @param id id that's refers to a student
     * @throws NoSuchStudentException thrown if id not refers to a student
     */
    public void deleteStudentById(String id) throws NoSuchStudentException {
        if(studentRepository.findById(id).isPresent()) {
            studentRepository.deleteById(id);
            log.info("Student with id " + id + " has been deleted.");
        }else
            throw new NoSuchStudentException("No student on id "+id);
    }

    /**
     * Updates the student data. Can update one or both attribute
     * @param id id that's refers to a student
     * @param newStudentData new data for student
     * @return updates Student
     * @throws NoSuchStudentException thrown if id not refers to a student
     */
    public Student updateStudent(String id, StudentUpdateDto newStudentData) throws NoSuchStudentException {
        Optional<Student> optStudent = studentRepository.findById(id);
        if(optStudent.isPresent()){
            return savingChanges(newStudentData, optStudent);
        }else
            throw new NoSuchStudentException("No student on id "+id);
    }

    private Student savingChanges(StudentUpdateDto newStudentData, Optional<Student> optStudent) {
        Student student = optStudent.get();
        if(newStudentData.getName()!=null && !newStudentData.getName().equals(student.getName())){
            student.setName(newStudentData.getName());
        }
        if(newStudentData.getEmail()!=null && !newStudentData.getEmail().equals(student.getEmail())){
            student.setEmail(newStudentData.getEmail());
        }
        Student savedStudent = studentRepository.save(student);
        log.info("Student is successfully updated.");
        return savedStudent;
    }
}