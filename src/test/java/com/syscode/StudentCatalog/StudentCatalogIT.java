package com.syscode.StudentCatalog;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.syscode.StudentCatalog.model.Student;
import com.syscode.StudentCatalog.repository.StudentRepository;
import com.syscode.StudentCatalog.service.StudentService;
import com.syscode.StudentCatalog.web.StudentController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class StudentCatalogIT {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentController studentController;

    @Autowired
    private MockMvc mockMvc;

    private List<Student> studentList;
    private String selected_Id;

    @BeforeEach
    void setUp(){
        studentRepository.deleteAll();
        studentList = new ArrayList<>();
        Student student1 = new Student("1","test1","test1@gmail.com");
        Student student2 = new Student("2","test2","test2@gmail.com");
        List<Student> students = studentRepository.saveAll(List.of(student1,student2));
        studentList.addAll(students);
        selected_Id = studentList.get(0).getId();
    }

    /**
     * Tests the Student create handler and the Student list handler
     * by adding a new Student to the database and to a list
     * and then comparing the two
     * @throws Exception for now, it is not relevant
     */
    @Test
    void test_Create_Student_And_List() throws Exception {
        Student student3 = new Student("3","test3","test3@gmail.com");

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/student/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(student3)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
        Student student = getStudentFromResponse(mvcResult);
        studentList.add(student);

        assertEquals(studentController.getStudents(),studentList);
    }

    /**
     * Sending create request with bad email format and
     * testing the response status
     * @throws Exception for now, it is not relevant
     */
    @Test
    void test_Bad_Email_Format() throws Exception {
        Student student1 = new Student("1","test1","test1gmail.com");
        mockMvc.perform(
                    MockMvcRequestBuilders.post("/student/new")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(student1)))
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    /**
     * Sending delete request then testing the response status
     * @throws Exception for now, it is not relevant
     */
    @Test
    void test_Good_Delete_Student() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/student/delete/"+selected_Id))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Sending delete request then testing the response status
     * @throws Exception for now, it is not relevant
     */
    @Test
    void test_Bad_Delete_Student() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/student/delete/"+1))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    private static Student getStudentFromResponse(MvcResult mvcResult) throws JsonProcessingException, UnsupportedEncodingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Student.class);
    }

    private static String asJsonString(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.writeValueAsString(object);
    }
}
