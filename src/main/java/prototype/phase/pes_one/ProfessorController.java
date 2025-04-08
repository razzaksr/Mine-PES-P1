package prototype.phase.pes_one;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professors")
public class ProfessorController {
    
    @Autowired
    ProfessorService professorService;

    @GetMapping("/")
    public ResponseEntity<List<Professor>> getAllProfessors() {
        List<Professor> professors = professorService.getAllProfessors();
        return ResponseEntity.ok(professors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> getProfessorById(@PathVariable int id) {
        Optional<Professor> professor = professorService.getProfessorById(id);
        return professor.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/")
    public ResponseEntity<Professor> addProfessor(@RequestBody Professor professor) {
        Professor savedProfessor = professorService.addProfessor(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProfessor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> updateProfessor(@PathVariable int id, @RequestBody Professor professor) {
        Professor updatedProfessor = professorService.updateProfessor(id, professor);
        return updatedProfessor != null
               ? ResponseEntity.ok(updatedProfessor)
               : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable int id) {
        boolean deleted = professorService.deleteProfessor(id);
        return deleted
               ? ResponseEntity.noContent().build()
               : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}