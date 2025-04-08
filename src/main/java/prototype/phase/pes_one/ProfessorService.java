package prototype.phase.pes_one;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProfessorService implements ProfessorRepository {
    private List<Professor> professors = new ArrayList<>();

    public ProfessorService(){
        professors = Stream.of(
            new Professor(12, "Razak Mohamed", "CSE"),
            new Professor(93, "Rasheedha Mohamed", "ISE"),
            new Professor(45, "Rajiya Mohamed", "EEE"),
            new Professor(56, "Vetrikanth", "CSE")
        ).collect(Collectors.toList());
    }

    public List<Professor> getAllProfessors() {
        return professors;
    }

    public Optional<Professor> getProfessorById(int id) {
        return professors.stream().filter(p -> p.getId() == id).findFirst();
    }

    public Professor addProfessor(Professor professor) {
        professors.add(professor);
        return professor;
    }

    public Professor updateProfessor(int id, Professor updatedProfessor) {
        for (int i = 0; i < professors.size(); i++) {
            if (professors.get(i).getId() == id) {
                professors.set(i, updatedProfessor);
                return updatedProfessor;
            }
        }
        return null;
    }

    public boolean deleteProfessor(int id) {
        return professors.removeIf(p -> p.getId() == id);
    }
}