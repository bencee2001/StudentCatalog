package com.syscode.StudentCatalog.web;

import com.syscode.StudentCatalog.dto.StudentUpdateDto;
import com.syscode.StudentCatalog.libs.errors.NoSuchStudentException;
import com.syscode.StudentCatalog.libs.errors.StudentCreateException;
import com.syscode.StudentCatalog.libs.errors.StudentUpdateException;
import com.syscode.StudentCatalog.model.Student;
import com.syscode.StudentCatalog.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Student> postStudent(@Valid @RequestBody Student student, BindingResult bindingResult) throws StudentCreateException {
        if(bindingResult.hasErrors())
            throw new StudentCreateException(bindingResult);
        return new ResponseEntity<>(studentService.saveStudent(student), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable String id) throws NoSuchStudentException {
        studentService.deleteStudentById(id);
        return new ResponseEntity<>("Student has been deleted.", HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> putStudent(
            @PathVariable String id,
            @Valid @RequestBody StudentUpdateDto newStudentData,
            BindingResult bindingResult) throws StudentUpdateException, NoSuchStudentException {
        if(bindingResult.hasErrors())
            throw new StudentUpdateException(bindingResult);
        return new ResponseEntity<>(studentService.updateStudent(id,newStudentData), HttpStatus.OK);
    }

}
