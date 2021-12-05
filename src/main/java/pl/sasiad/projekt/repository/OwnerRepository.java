package pl.sasiad.projekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sasiad.projekt.entities.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
