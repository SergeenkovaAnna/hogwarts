package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface StudentService {

//    Student addStudent(String name, String age, Long id);

    Student addStudent(Student student);

    void removeStudent(Long id);

    Student findStudent(Long id);

    Student editStudent(Student student);

    Collection<Student> getAllStudent();

    Collection<Student> getStudentsFor(Integer age);

    Map<Integer, List<Student>> findStudentByAge();

    Collection<Student> findByAgeBetween(Integer min, Integer max);

    Long getNumberAllOfStudents();

    Long getAvgAge();

    Collection<Student> getLastFiveStudents();
}
