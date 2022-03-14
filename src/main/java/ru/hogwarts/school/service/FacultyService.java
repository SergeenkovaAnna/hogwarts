package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface FacultyService {

    Faculty addFaculty(Faculty faculty);

    void removeFaculty(Long id);

    Faculty findFaculty(Long id);

    Faculty editFaculty(Faculty faculty);

    Collection<Faculty> getAllFaculty();

//    Map<String, List<Faculty>> findFacultyByColor(String color);

    List<Faculty> getFacultyForColor(String color);

}
