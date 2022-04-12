package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository <Student, Long> {

    Collection<Student> findByAgeBetween(Integer min, Integer max);

    @Query(value = "SELECT COUNT(*) FROM student", nativeQuery = true)
    Long getAll();

    @Query(value = "SELECT avg(age) FROM student", nativeQuery = true)
    Long avgAge();

    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<Student> getFiveAtLastStudents();
}
