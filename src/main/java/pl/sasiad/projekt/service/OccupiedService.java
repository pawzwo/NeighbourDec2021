package pl.sasiad.projekt.service;

import pl.sasiad.projekt.entities.Available;
import pl.sasiad.projekt.entities.Occupied;
import pl.sasiad.projekt.entities.ParkingSpot;
import pl.sasiad.projekt.entities.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OccupiedService {

    Occupied findById(Long id);

    List<Occupied> findAllByUserId(long id);

    List<Occupied> findAllActive();

    List<Occupied> findAllActiveByParkingSpotId(long spotId);

    List<Occupied> findAllBySpotOwner(long id);

    List<Occupied> findAllByParkingSpotId(long spotId);

    void save(Occupied occupied);

    void disable(long id);

    //Admin
    void deleteAllByParkingSpot(long spotId);

    void deleteAllAvailableOccuList(long occupId);


}
