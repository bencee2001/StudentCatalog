package com.syscode.StudentCatalog.service;

import com.syscode.StudentCatalog.model.Student;
import com.syscode.StudentCatalog.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Log4j2
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getStudents() {
        return null;
    }

    public String saveStudent(Student student) {
        Student savedStudent = studentRepository.save(student);
        log.info(savedStudent.getName()+" was saved with the id "+savedStudent.getId());
        return savedStudent.getName()+" was saved successfully.";
    }

}
