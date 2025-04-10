package prototype.phase.pes_one;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class ProfessorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProfessorJpaService professorService;

    @InjectMocks
    private ProfessorController professorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(professorController).build();
    }

    @Test
    void testGetAllProfessors() throws Exception {
        when(professorService.getAllProfessors()).thenReturn(Arrays.asList(
                new Professor(1, "Dr. John", "CS"),
                new Professor(2, "Dr. Smith", "Math")
        ));

        mockMvc.perform(get("/professors/"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void testGetProfessorById() throws Exception {
        when(professorService.getProfessorById(1)).thenReturn(Optional.of(new Professor(1, "Dr. John", "CS")));

        mockMvc.perform(get("/professors/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("Dr. John"));
    }

    @Test
    void testAddProfessor() throws Exception {
        Professor professor = new Professor(3, "Dr Brown", "Physics");
        when(professorService.addProfessor(any(Professor.class))).thenReturn(professor);

        mockMvc.perform(post("/professors/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"id\": 3, \"name\": \"Dr Brown\", \"department\": \"Physics\" }"))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.name").value("Dr Brown"));
    }

    @Test
    void testUpdateProfessor() throws Exception {
        Professor professor = new Professor(1, "Dr John Updated", "CSE");
        when(professorService.updateProfessor(any(Professor.class))).thenReturn(professor);

        mockMvc.perform(put("/professors/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"id\": 1, \"name\": \"Dr John Updated\", \"department\": \"CSE\" }"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("Dr John Updated"));
    }

    @Test
    void testDeleteProfessor_Success() throws Exception {
        when(professorService.deleteProfessor(1)).thenReturn(true);

        mockMvc.perform(delete("/professors/1"))
               .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteProfessor_NotFound() throws Exception {
        when(professorService.deleteProfessor(999)).thenReturn(false);

        mockMvc.perform(delete("/professors/999"))
               .andExpect(status().isNotFound());
    }
}