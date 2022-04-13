package ru.hogwarts.school.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void removeStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student findStudent(Long id) {
        return studentRepository.findById(id).get();
    }


    @Override
    public Collection<Student> getStudentsFor(Integer age) {
        return getAllStudent().stream()
                .filter(student -> student.isByAge(age))
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Student>> findStudentByAge() {
        return getAllStudent().stream()
                .collect(Collectors.groupingBy(Student::getAge));
    }

    @Override
    public Collection<Student> findByAgeBetween(Integer min, Integer max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    @Override
    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Collection<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Long getNumberAllOfStudents() {
        return studentRepository.getAll();
    }

    @Override
    public Long getAvgAge() {
        return studentRepository.avgAge();
    }

    @Override
    public Collection<Student> getLastFiveStudents() {
        return studentRepository.getFiveAtLastStudents();
    }
}
