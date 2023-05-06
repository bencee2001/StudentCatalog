package com.syscode.StudentCatalog.web;

import com.syscode.StudentCatalog.libs.errors.StudentCreateException;
import com.syscode.StudentCatalog.model.Student;
import com.syscode.StudentCatalog.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/student")
@AllArgsConstructor
@Log4j2
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/list")
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping("/new")
    public ResponseEntity<String> postStudent(@Valid @RequestBody Student student, BindingResult bindingResult) throws StudentCreateException {
        if(bindingResult.hasErrors()){
            throw new StudentCreateException(bindingResult);
        }else{
            return new ResponseEntity<>(studentService.saveStudent(student), HttpStatus.CREATED);
        }
    }

}
