package pl.sasiad.projekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.sasiad.projekt.entities.Available;
import pl.sasiad.projekt.entities.ParkingSpot;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AvailableRepository extends JpaRepository<Available, Long> {

    @Query(value = "SELECT * FROM available JOIN users_parking_spots on parking_spot_id = parking_spots_id WHERE end>=current_date AND disabled =0 AND user_id = ?1 ORDER BY available.start", nativeQuery = true)
    List<Available> findAllAvailableByUserId(long id);

    @Query(value = "SELECT * FROM available JOIN users_parking_spots on parking_spot_id = parking_spots_id WHERE end>=current_date AND disabled = 0 AND NOT user_id = ?1 ORDER BY available.start", nativeQuery = true)
    List<Available> findAllAvailableNotUser(long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE available SET disabled=1 WHERE id = ?1", nativeQuery = true)
    void disable(long id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO available_occupied_list (available_id, occupied_list_id) VALUES (?1, ?2)", nativeQuery = true)
    void addToOccupiedList(long idAvail, long idOccup);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM available_occupied_list WHERE occupied_list_id=?1", nativeQuery = true)
    void removeFromOccupiedList(long idOccup);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM available_occupied_list WHERE available_id=?1", nativeQuery = true)
    void clearOccupiedList(long availId);

    //potrzebne??
    @Transactional
    @Modifying
    @Query(value = "UPDATE available SET occupied=1 WHERE id = ?1", nativeQuery = true)
    void occupy(long id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO available (disabled, end, occupied, start, parking_spot_id) VALUES (?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    void saveNew(int disabled, LocalDate end, int occupied, LocalDate start, ParkingSpot spot);

    @Query(value = "SELECT * FROM available JOIN users_parking_spots on parking_spot_id = parking_spots_id WHERE parking_spots_id=?1", nativeQuery = true)
    List<Available> findAllAvailableByParkingSpotId(Long id);


    //Admin
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM available WHERE parking_spot_id = ?1", nativeQuery = true)
    void deleteByParkingSpot(long id);
}
