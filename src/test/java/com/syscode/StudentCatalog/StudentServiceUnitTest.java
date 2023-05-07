package com.syscode.StudentCatalog;

import com.syscode.StudentCatalog.dto.StudentUpdateDto;
import com.syscode.StudentCatalog.libs.errors.NoSuchStudentException;
import com.syscode.StudentCatalog.model.Student;
import com.syscode.StudentCatalog.repository.StudentRepository;
import com.syscode.StudentCatalog.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceUnitTest {

    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    StudentService studentService;

    /**
     * Tests the getStudents() method
     */
    @Test
    void test_Get_Students(){
        Student student = new Student("1","test1","test@gmail.com");

        when(studentRepository.findAll()).thenReturn(List.of(student));
        List<Student> students = this.studentService.getStudents();

        assertEquals(List.of(student), students);
    }

    /**
     * Tests saveStudents method by saving 2 new Student
     */
    @Test
    void test_Save_Student(){
        List<Student> studentList = new ArrayList<>();
        Student student1 = new Student("1","test1","test2@gmail.com");
        Student student2 = new Student("2","test2","test2@gmail.com");

        when(studentRepository.save(Mockito.any(Student.class))).thenAnswer((i -> {
            studentList.add(i.getArgument(0));
            return i.getArgument(0);
        }));

        assertEquals(this.studentService.saveStudent(student1), student1);
        assertEquals(studentList.size(), 1);
        assertEquals(this.studentService.saveStudent(student2), student2);
        assertEquals(studentList.size(), 2);
    }

    /**
     * Tests deleteStudentById method
     * @throws NoSuchStudentException for now, it is not relevant
     */
    @Test
    void test_Delete_User_With_Existing_Student() throws NoSuchStudentException {
        List<Student> studentList = new ArrayList<>();
        Optional<Student> student = Optional.of(new Student("1", "test1", "test2@gmail.com"));
        studentList.add(student.get());

        when(studentRepository.findById(Mockito.any(String.class))).thenReturn(student);
        doAnswer((i -> {
            studentList.remove(student.get());
            return null;
        })).when(studentRepository).deleteById(Mockito.any(String.class));

        assertEquals(studentList.size(), 1);
        this.studentService.deleteStudentById("1");
        assertEquals(studentList.size(), 0);
    }

    /**
     * Tests if the id is not referring to a student,
     *  if it throws the right exception.
     * @throws NoSuchStudentException expected exception
     */
    @Test()
    void test_Delete_Student_With_Not_Existing_Student() throws NoSuchStudentException {
        Optional<Student> student = Optional.empty();

        when(studentRepository.findById(Mockito.any(String.class))).thenReturn(student);

        assertThrows(NoSuchStudentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                studentService.deleteStudentById("1");
            }
        });
    }

    /**
     * Tests updateStudent method, by updating a Student with different scenarios
     * @throws NoSuchStudentException for now, it is not relevant
     */
    @Test
    void test_Update_Student_With_Existing_Student() throws NoSuchStudentException {
        Optional<Student> student = Optional.of(new Student("1", "aaa", "aaa@gmail.com"));
        StudentUpdateDto testCase1=new StudentUpdateDto("bbb","bbb@gmail.com");
        StudentUpdateDto testCase2=new StudentUpdateDto("ccc",null);
        StudentUpdateDto testCase3=new StudentUpdateDto(null,"ccc@gmail.com");
        StudentUpdateDto testCase4=new StudentUpdateDto("ccc","ccc@gmail.com");

        when(studentRepository.findById(Mockito.any(String.class))).thenReturn(student);

        assertEquals(student.get().getName(),"aaa");
        assertEquals(student.get().getEmail(),"aaa@gmail.com");
        studentService.updateStudent("1",testCase1);
        assertEquals(student.get().getName(),"bbb");
        assertEquals(student.get().getEmail(),"bbb@gmail.com");
        studentService.updateStudent("1",testCase2);
        assertEquals(student.get().getName(),"ccc");
        assertEquals(student.get().getEmail(),"bbb@gmail.com");
        studentService.updateStudent("1",testCase3);
        assertEquals(student.get().getName(),"ccc");
        assertEquals(student.get().getEmail(),"ccc@gmail.com");
        studentService.updateStudent("1",testCase4);
        assertEquals(student.get().getName(),"ccc");
        assertEquals(student.get().getEmail(),"ccc@gmail.com");
    }

    /**
     * Tests if the id is not referring to a student,
     *  if it throws the right exception.
     * @throws NoSuchStudentException expected exception
     */
    @Test
    void test_Update_Student_With_Not_Existing_Student() throws NoSuchStudentException {
        Optional<Student> student = Optional.empty();

        when(studentRepository.findById(Mockito.any(String.class))).thenReturn(student);

        assertThrows(NoSuchStudentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                studentService.updateStudent("1",new StudentUpdateDto());
            }
        });
    }
}
