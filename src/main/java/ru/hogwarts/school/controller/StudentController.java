package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.OptionalDouble;


@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student studentForEdit = studentService.editStudent(student);
        if (studentForEdit == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        studentService.removeStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudent());
    }

    @GetMapping(value = "/age", params = {"age"})
    public Collection<Student> getAllStudentForAge(@RequestParam Integer age) {
        return (Collection<Student>) studentService.getStudentsFor(age);
    }

    @GetMapping(value = "/ageBetween", params = {"min", "max"})
    public ResponseEntity<Collection<Student>> findByAgeBetween(@RequestParam Integer min,
                                                                @RequestParam Integer max) {
        return ResponseEntity.ok(studentService.findByAgeBetween(min, max));
    }

    @GetMapping("/studentsAreTotal")
    public Long getAllStudentsNumber() {
        return studentService.getNumberAllOfStudents();
    }

    @GetMapping("/studentsAvrAge")
    public OptionalDouble getAvrAge() {
        return studentService.getAvgAge();
    }

    @GetMapping("/lastFiveStudents")
    public Collection<Student> getFiveLastStudents() {
        return studentService.getLastFiveStudents();
    }

    @GetMapping("/startWithA")
    public ResponseEntity<Collection<String>> getNamesByStudentsWichStartsWithA() {
        Collection<String> namesStartWithA = studentService.getAllStudentsNamesWhichStartWithA();
        return ResponseEntity.ok(namesStartWithA);
    }

}
