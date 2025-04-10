package prototype.phase.pes_one;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RepositoryTest {
    @Mock
    private ProfessorJpaRepository repository;
    @InjectMocks
    private ProfessorJpaService service;

    @BeforeEach
    void settingUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProfessors() {
        when(repository.findAll()).thenReturn(Arrays.asList(
                new Professor(1, "Dr. John", "CS"),
                new Professor(2, "Dr. Smith", "Math")
        ));
        assertEquals(2, service.getAllProfessors().size());
    }

    @Test
    void testGetProfessorById() {
        when(repository.findById(1)).thenReturn(Optional.of(new Professor(1, "Dr. John", "CS")));
        Optional<Professor> professor = service.getProfessorById(1);
        assertTrue(professor.isPresent());
        assertEquals("Dr. John", professor.get().getName());
    }
    @Test
    void testAddProfessor() {
        Professor professor1 = new Professor(3, "Dr. Brown", "Physics");
        Professor professor2 = new Professor(1, "Dr. White", "Chemistry");
        when(repository.save(professor1)).thenReturn(professor1);
        assertEquals(professor1, service.addProfessor(professor1));
    }

    @Test
    void testUpdateProfessor() {
        Professor professor = new Professor(1, "Dr. John Updated", "CS");
        when(repository.existsById(professor.getId())).thenReturn(true);
        when(repository.save(professor)).thenReturn(professor);
        assertEquals(professor, service.updateProfessor(professor));
    }
    @Test
    void testDeleteProfessor() {
        when(repository.existsById(1)).thenReturn(true);
        doNothing().when(repository).deleteById(1);
        service.deleteProfessor(1);
        verify(repository, times(1)).deleteById(1);
    }
}
