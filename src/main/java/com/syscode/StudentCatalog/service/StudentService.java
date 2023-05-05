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

    public ResponseEntity<String> saveStudent(Student student, BindingResult bindingResult) {
        log.info("Start saving new student...");
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(buildErrorMessage(bindingResult), HttpStatus.BAD_REQUEST);
        }else {
            studentRepository.save(student);
            log.info("Success");
            return new ResponseEntity<>("Good", HttpStatus.CREATED);
        }
    }

    private static String buildErrorMessage(BindingResult bindingResult) {
        StringBuilder builder = new StringBuilder();
        for(ObjectError error : bindingResult.getAllErrors()){
            builder.append(Objects.requireNonNull(error.getCodes())[0]);
            builder.append(": ");
            builder.append(error.getDefaultMessage());
            builder.append('\n');
        }
        return builder.toString();
    }
}
