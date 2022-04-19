package ru.hogwarts.school.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public Student addStudent(Student student) {
        logger.info("method addStudent is start");
        return studentRepository.save(student);
    }

    @Override
    public void removeStudent(Long id) {
        logger.info("method removeStudent is start");
        studentRepository.deleteById(id);
    }

    @Override
    public Student findStudent(Long id) {
        logger.info("method findStudent is start");
        return studentRepository.findById(id).get();
    }


    @Override
    public Collection<Student> getStudentsFor(Integer age) {
        logger.info("method getStudentsFor is start");
        return getAllStudent().stream()
                .filter(student -> student.isByAge(age))
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Student>> findStudentByAge() {
        logger.info("method findStudentByAge is start");
        return getAllStudent().stream()
                .collect(Collectors.groupingBy(Student::getAge));
    }

    @Override
    public Collection<Student> findByAgeBetween(Integer min, Integer max) {
        logger.info("method findByAgeBetween is start");
        return studentRepository.findByAgeBetween(min, max);
    }

    @Override
    public Student editStudent(Student student) {
        logger.info("method editStudent is start");
        return studentRepository.save(student);
    }

    @Override
    public Collection<Student> getAllStudent() {
        logger.info("method getAllStudent is start");
        return studentRepository.findAll();
    }

    @Override
    public Long getNumberAllOfStudents() {
        logger.info("method getNumberAllOfStudents is start");
        return studentRepository.getAll();
    }

    @Override
    public OptionalDouble getAvgAge() {
        logger.info("method getAvgAge is start");
//        return studentRepository.avgAge();
        return studentRepository.findAll().stream()
                .mapToLong(Student :: getAge)
                .average();
    }

    @Override
    public Collection<Student> getLastFiveStudents() {
        logger.info("method getLastFiveStudents is start");
        return studentRepository.getFiveAtLastStudents();
    }

    @Override
    public Collection<String> getAllStudentsNamesWhichStartWithA() {
        return studentRepository.findAll().stream()
                .parallel()
                .map(Student :: getName)
                .map(String::toUpperCase)
                .filter(a -> a.startsWith("A"))
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public void getNamesByThreads() {
        System.out.println(studentRepository.getById(1L).getId()
                            + " "
                            + studentRepository.getById(1L).getName());
        System.out.println(studentRepository.getById(2L).getId()
                + " "
                + studentRepository.getById(2L).getName());

        new Thread(() -> {
            System.out.println(studentRepository.getById(3L).getId()
                    + " "
                    + studentRepository.getById(3L).getName());
            System.out.println(studentRepository.getById(4L).getId()
                    + " "
                    + studentRepository.getById(4L).getName());
        }).start();

        new Thread(() -> {
            System.out.println(studentRepository.getById(6L).getId()
                    + " "
                    + studentRepository.getById(6L).getName());
            System.out.println(studentRepository.getById(7L).getId()
                    + " "
                    + studentRepository.getById(7L).getName());
        }).start();

    }

    @Override
    public void getStudentsNamesByThreadsSync() {
        System.out.println(studentRepository.getById(1L).getId()
                + " "
                + studentRepository.getById(1L).getName());
        System.out.println(studentRepository.getById(2L).getId()
                + " "
                + studentRepository.getById(2L).getName());

        Object flag = new Object();
        new Thread(() -> {
            synchronized (flag) {
                System.out.println(studentRepository.getById(3L).getId()
                        + " "
                        + studentRepository.getById(3L).getName());
                System.out.println(studentRepository.getById(4L).getId()
                        + " "
                        + studentRepository.getById(4L).getName());
            }

        }).start();

        new Thread(() -> {
            synchronized (flag) {
                System.out.println(studentRepository.getById(6L).getId()
                        + " "
                        + studentRepository.getById(6L).getName());
                System.out.println(studentRepository.getById(7L).getId()
                        + " "
                        + studentRepository.getById(7L).getName());
            }

        }).start();
    }
}
