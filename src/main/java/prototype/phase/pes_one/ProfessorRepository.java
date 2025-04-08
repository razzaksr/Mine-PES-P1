package prototype.phase.pes_one;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository {
    Professor addProfessor(Professor professor);
    List<Professor> getAllProfessors();
    Optional<Professor> getProfessorById(int id);
    Professor updateProfessor(int id, Professor updatedProfessor);
    boolean deleteProfessor(int id);
}
