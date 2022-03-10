package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface FacultyService {

    Faculty addFaculty(Faculty faculty);

    Faculty removeFaculty(Long id);

    Faculty findFaculty(Long id);

    Faculty editFaculty(Faculty faculty);

    Collection<Faculty> getAllFaculty();

    Map<String, List<Faculty>> findFacultyByColor(String color);

}
