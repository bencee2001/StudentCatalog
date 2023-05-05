package com.syscode.StudentCatalog.web;

import com.syscode.StudentCatalog.model.Student;
import com.syscode.StudentCatalog.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/student")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/list")
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping("/new")
    public void postStudent(@RequestBody Student student){
        studentService.saveStudent(student);
    }

}
