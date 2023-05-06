package com.syscode.StudentCatalog;

import com.syscode.StudentCatalog.dto.StudentUpdateDto;
import com.syscode.StudentCatalog.libs.errors.NoSuchStudentException;
import com.syscode.StudentCatalog.libs.errors.StudentCreateException;
import com.syscode.StudentCatalog.libs.errors.StudentUpdateException;
import com.syscode.StudentCatalog.model.Student;
import com.syscode.StudentCatalog.service.StudentService;
import com.syscode.StudentCatalog.web.StudentController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentControllerUnitTest {
    @Mock
    StudentService studentService;

    @InjectMocks
    StudentController studentController;

    @Test
    void test_Validation_Exception(){
        BindingResult bindingResult = mock(BindingResult.class);

        when(bindingResult.hasErrors()).thenReturn(true);

        assertThrows(StudentCreateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                studentController.postStudent(new Student(),bindingResult);
            }
        });

        assertThrows(StudentUpdateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                studentController.putStudent("0",new StudentUpdateDto(),bindingResult);
            }
        });
    }
}
