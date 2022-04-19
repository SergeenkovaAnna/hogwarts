package ru.hogwarts.school.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty addFaculty(Faculty faculty) {
        logger.info("method addFaculty is start");
        return facultyRepository.save(faculty);
    }

    @Override
    public void removeFaculty(Long id) {
        logger.info("method removeFaculty is start");
        facultyRepository.deleteById(id);
    }

    @Override
    public Faculty findFaculty(Long id) {
        logger.info("method findFaculty is start");
        return facultyRepository.findById(id).get();
    }

    @Override
    public Faculty editFaculty(Faculty faculty) {
        logger.info("method editFaculty is start");
        return facultyRepository.save(faculty);
    }

    @Override
    public Collection<Faculty> getAllFaculty() {
        logger.info("method getAllFaculty is start");
        return facultyRepository.findAll();
    }

    @Override
    public List<Faculty> getFacultyByColor(String color) {
        logger.info("method getFacultyByColor is start");
        return getAllFaculty().stream()
                    .filter(faculty -> faculty.isByColor(color))
                    .collect(Collectors.toList());

    }

    @Override
    public Faculty findFacultyByNameIgnoreCase(String name) {
        logger.info("method findFacultyByNameIgnoreCase is start");
        return facultyRepository.findFacultyByNameIgnoreCase(name);
    }

    @Override
    public Optional<String> getTheMostLongNameOfFaculty() {
        return facultyRepository.findAll().stream()
                .map(Faculty :: getName)
                .max(Comparator.comparingInt(String::length));
    }
}
