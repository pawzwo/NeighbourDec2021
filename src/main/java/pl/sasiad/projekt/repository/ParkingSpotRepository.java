package pl.sasiad.projekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.sasiad.projekt.entities.ParkingSpot;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ParkingSpotRepository extends JpaRepository <ParkingSpot, Long> {




    //Admin
    @Query(value = "SELECT * FROM spots", nativeQuery = true)
    List<ParkingSpot> selectAll();

    @Transactional
    @Modifying
    @Query(value = "UPDATE spots SET disable=1 WHERE id = ?1", nativeQuery = true)
    void disable(long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE spots SET disable=0 WHERE id = ?1", nativeQuery = true)
    void enable(long id);


}
