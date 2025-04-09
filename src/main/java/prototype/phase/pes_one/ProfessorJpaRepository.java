package prototype.phase.pes_one;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorJpaRepository extends JpaRepository<Professor,Integer> {
    
}
