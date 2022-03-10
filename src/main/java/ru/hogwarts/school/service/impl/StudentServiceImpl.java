package ru.hogwarts.school.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final HashMap<Long, Student> students = new HashMap<>();
    private long lastId = 0;

    @Override
    public Student addStudent(Student student) {
        student.setId(++lastId);
        students.put(lastId, student);
        return student;
    }

    @Override
    public Student removeStudent(Long id) {
        return students.remove(id);
    }

    @Override
    public Student findStudent(Long id) {
        return students.get(id);
    }

    @Override
    public Map<String, List<Student>> findStudentByAge(String age) {
        return getAllStudent().stream()
                .collect(Collectors.groupingBy(Student::getAge));
    }

    @Override
    public Student editStudent(Student student) {
        if (students.containsKey(student.getId())) {
            students.put(student.getId(), student);
            return student;
        }
        return null;
    }

    @Override
    public Collection<Student> getAllStudent() {
        return Set.copyOf(students.values());
    }
}
