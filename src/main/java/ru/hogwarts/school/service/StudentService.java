package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface StudentService {

//    Student addStudent(String name, String age, Long id);

    Student addStudent(Student student);

    Student removeStudent(Long id);

    Student findStudent(Long id);

    Student editStudent(Student student);

    Collection<Student> getAllStudent();

    Map<String, List<Student>> findStudentByAge(String age);
}
