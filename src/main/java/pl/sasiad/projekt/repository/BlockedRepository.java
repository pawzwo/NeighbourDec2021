package pl.sasiad.projekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sasiad.projekt.entities.Blocked;

@Repository
public interface BlockedRepository extends JpaRepository<Blocked, Long> {
}
