package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.RequestEntity;
import ru.hogwarts.school.model.Student;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testPostStudent() throws Exception {
        Student student = new Student();
        student.setName("Ann");
        student.setAge(12);
        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", student, Student.class))
                .isNotNull();
    }

    @Test
    public void testGetStudents() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student", Student.class))
                .isNotNull();
    }

//    @Test
//    public void testDeleteStudent() throws Exception {
//        Student student = new Student();
//        student.setName("Ann");
//        student.setAge(12);
//        Assertions
//                .assertThat(this.restTemplate.delete("http://localhost:" + port + "/student" + "/{id}", id, Student.class))
//                .isNotNull();
//
//    }
//
//    @Test
//    public void testEditStudent() throws Exception {
//        Student student = new Student();
//        student.setName("Ann");
//        student.setAge(12);
//        Assertions
//                .assertThat(this.restTemplate.exchange(RequestEntity<Student> student, Student.class));
//    }

}
