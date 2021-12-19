package pl.sasiad.projekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sasiad.projekt.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository <Role, Long> {

    Role findByName(String name);

}
