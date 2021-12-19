package pl.sasiad.projekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.sasiad.projekt.entities.Available;
import pl.sasiad.projekt.entities.Occupied;
import pl.sasiad.projekt.entities.ParkingSpot;
import pl.sasiad.projekt.entities.User;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OccupiedRepository extends JpaRepository<Occupied, Long> {

    @Query(value = "SELECT * FROM occupied WHERE end>=current_date AND disable=0 AND user_id=?1", nativeQuery = true)
    List<Occupied> findAllByUserId(long id);

    @Query(value = "SELECT * FROM occupied WHERE end>=current_date AND disable =0", nativeQuery = true)
    List<Occupied> findAllActive();

    @Query(value = "SELECT * FROM occupied JOIN spots s on occupied.parking_spot_id = s.id\n" +
            "JOIN users_parking_spots ups on s.id = ups.parking_spots_id JOIN users u on u.id = ups.user_id\n" +
            "WHERE occupied.end>=CURRENT_DATE AND occupied.disable=0 AND u.id = ?1", nativeQuery = true)
    List<Occupied> findAllBySpotOwner(long id);

    @Query(value = "SELECT * FROM occupied WHERE parking_spot_id=?1", nativeQuery = true)
    List<Occupied> findAllByParkingSpotId(long spotId);

    @Query(value = "SELECT * FROM occupied WHERE parking_spot_id=?1 AND disable=0", nativeQuery = true)
    List<Occupied> findAllActiveByParkingSpotId(long spotId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO occupied (disable, end, start, available_id, parking_spot_id, user_id) VALUES (?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
    void saveNew(int disable, LocalDate end, LocalDate start, Available available, ParkingSpot spot, User user);

    @Transactional
    @Modifying
    @Query(value = "UPDATE occupied SET disable=1 WHERE id = ?1", nativeQuery = true)
    void disable(long id);

    @Override
    Optional<Occupied> findById(Long id);

    //Admin
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM occupied WHERE parking_spot_id=?1", nativeQuery = true)
    void deleteAllByParkingSpot(long spotId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM available_occupied_list WHERE occupied_list_id=?1", nativeQuery = true)
    void deleteAllAvailableOccuList(long occupId);

}
