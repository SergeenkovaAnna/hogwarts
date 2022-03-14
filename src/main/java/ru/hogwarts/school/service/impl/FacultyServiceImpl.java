package ru.hogwarts.school.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public void removeFaculty(Long id) {
       facultyRepository.deleteById(id);
    }

    @Override
    public Faculty findFaculty(Long id) {
        return facultyRepository.findById(id).get();
    }

    @Override
    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Collection<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

//    @Override
//    public Map<String, List<Faculty>> findFacultyByColor(String color) {
//        return getAllFaculty().stream()
//                .collect(Collectors.groupingBy(Faculty::getColor));
//    }

    @Override
    public List<Faculty> getFacultyForColor(String color) {
        return getAllFaculty().stream()
                    .filter(faculty -> faculty.isByColor(color))
                    .collect(Collectors.toList());

    }
}
