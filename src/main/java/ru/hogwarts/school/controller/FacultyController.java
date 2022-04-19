package ru.hogwarts.school.controller;

import liquibase.pro.packaged.F;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> editStudent(@RequestBody Faculty faculty) {
        Faculty facultyForEdit = facultyService.editFaculty(faculty);
        if (facultyForEdit == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(faculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long id) {
        facultyService.removeFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> getAllFaculty() {
        return ResponseEntity.ok(facultyService.getAllFaculty());
    }

    @GetMapping(value = "/color", params = {"color"})
    public ResponseEntity<Collection<Faculty>> getAllFacultyByColor(@RequestParam String color) {
       return ResponseEntity.ok((Collection<Faculty>) facultyService.getFacultyByColor(color));
    }

    @GetMapping(value = "/name", params = {"name"})
    public ResponseEntity<Faculty> findFacultyByNameIgnoreCase(@RequestParam String name) {
        return ResponseEntity.ok(facultyService.findFacultyByNameIgnoreCase(name));
    }

    @GetMapping("/theMostLongNameByFaculty")
    public ResponseEntity<Optional<String>> findFacultyWithTheMostLongName() {
        Optional<String> theMostLongNameByFaculty = facultyService.getTheMostLongNameOfFaculty();
        return ResponseEntity.ok(theMostLongNameByFaculty);
    }

}
