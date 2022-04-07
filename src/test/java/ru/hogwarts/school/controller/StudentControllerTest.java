package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {
    @LocalServerPort
    private int port;

    private static final int ID = 1;
    private long studentId;

    private static final Student STUDENT = new Student();


    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeAll
    public static void init() {
        STUDENT.setName("Ann");
        STUDENT.setAge(25);
        STUDENT.setId(1L);
    }

    @Test
    public void testPostStudent() throws Exception {
        init();
        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", STUDENT, Student.class))
                .isNotNull();
    }

    @Test
    public void testGetStudents() throws Exception {
        init();
        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", STUDENT, Student.class))
                .isNotNull();
        Assertions.
                assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/" + STUDENT, Student.class))
                .isEqualTo(STUDENT);
    }

    @Test
    public void testDeleteStudent() throws Exception {
            Student response =this.restTemplate.postForObject("http://localhost:" + port + "/student", STUDENT, Student.class);
            this.restTemplate.delete("http://localhost:" + port + "/student" + "/{id}", response.getId());
            Student isDelete = this.restTemplate.getForObject("http://localhost:" + port + "/student" + "/{id}", Student.class, response.getId());
        Assertions
                .assertThat(isDelete.getId()).isNull();


    }


    private Student getStudentById() {
        return this.restTemplate.getForObject("http://localhost:" + port + "/student/id/{id}", Student.class, ID);

    }

    @Test
    public void testEditStudent() throws Exception {
        Student studentBeforeEdit = getStudentById();
        studentBeforeEdit.setName("Bill");
        this.restTemplate.put("http://localhost:" + port + "/student", studentBeforeEdit);
        Student afterEdit = getStudentById();
        Assertions
                .assertThat(afterEdit).isEqualTo(studentBeforeEdit);
    }

    @Test
    public void testGetAllStudents() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port, Student.class))
                .isNotNull();

    }

    @Test
    public void testFindStudentByAgeBetween() throws Exception {
        init();
        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", STUDENT, Student.class))
                .isNotNull();
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/ageBetween/" + "?min=" + 20 + "&max=" + 30, ArrayList.class).size())
                .isGreaterThan(0);
    }

    @Test
    public void testGetStudentByAge() throws Exception {

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/age/", Student.class))
                .isNotNull();
    }


}
