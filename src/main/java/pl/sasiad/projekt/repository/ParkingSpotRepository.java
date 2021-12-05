package pl.sasiad.projekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sasiad.projekt.entities.ParkingSpot;

@Repository
public interface ParkingSpotRepository extends JpaRepository <ParkingSpot, Long> {
}
