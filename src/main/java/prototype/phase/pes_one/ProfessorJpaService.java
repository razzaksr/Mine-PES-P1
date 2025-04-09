package prototype.phase.pes_one;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorJpaService {
    @Autowired
    private ProfessorJpaRepository repository;

    public List<Professor> getAllProfessors() {
        return repository.findAll();
    }

    public Optional<Professor> getProfessorById(int id) {
        return repository.findById(id);
    }

    public Professor addProfessor(Professor professor) {
        return repository.save(professor);
    }

    public Professor updateProfessor(Professor updatedProfessor) {
        return repository.save(updatedProfessor);
    }

    public boolean deleteProfessor(int id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
