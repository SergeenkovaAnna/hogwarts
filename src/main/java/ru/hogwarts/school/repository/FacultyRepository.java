package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;


public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Faculty findFacultyByNameIgnoreCase(String name);

    Collection<Faculty> getAllFacultyByColor(String color);


}
