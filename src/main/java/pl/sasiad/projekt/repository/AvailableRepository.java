package pl.sasiad.projekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sasiad.projekt.entities.Available;

@Repository
public interface AvailableRepository extends JpaRepository<Available, Long> {
}
