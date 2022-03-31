package ru.hogwarts.school.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.exceptions.misusing.NotAMockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.impl.FacultyServiceImpl;

import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.hogwarts.school.ConstantsForTests.*;


@WebMvcTest(controllers = FacultyController.class)
public class FacultyControllerTest {

    private Faculty faculty;
    private Faculty faculty2;

    private Set<Faculty> faculties;

    private JSONObject facultyObject;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;

    @SpyBean
    private FacultyServiceImpl facultyServiceImpl;

    @InjectMocks
    private FacultyController facultyController;

    @BeforeEach
    public void setUp() throws Exception {
        facultyObject = new JSONObject();
        facultyObject.put("name", NAME);
        facultyObject.put("color", COLOR);

        faculty = new Faculty();
        faculty.setName(NAME);
        faculty.setId(ID);
        faculty.setColor(COLOR);

        faculty2 = new Faculty();
        faculty2.setName(NAME_2);
        faculty2.setId(ID_2);
        faculty2.setColor(COLOR_2);

        faculties = Set.of(faculty, faculty2);

    }


    @Test
    public void saveTest() throws Exception {
//        final String name = "Томское моторно-тракторное училище";
//        final String color = "Цвет грязных гусениц трактора";
//        final long id = 1L;
//        JSONObject facultyObject = new JSONObject();
//        facultyObject.put("Mary", 15L);
//
//        Faculty faculty = new Faculty();
//        faculty.setId(15L);
//        faculty.setName("Mary");
//        faculty.setColor("red");

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                .post("/faculty")
                .content(facultyObject.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.color").value(COLOR));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/faculty/" + ID)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.color").value(COLOR));

    }

}
