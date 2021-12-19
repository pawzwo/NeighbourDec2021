package pl.sasiad.projekt.service;

import pl.sasiad.projekt.entities.Available;
import pl.sasiad.projekt.entities.ParkingSpot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AvailableService {

    List<Available> findAllAvailableByParkingSpotId(Long id);

    List<Available> findAllAvailableByUserId(long id);

    List<Available> findAllAvailableNotUser(long id);

    void disable(long id);

    void occupy(long id);

    void saveNew(int disabled, LocalDate end, int occupied, LocalDate start, ParkingSpot spot);

    Available findById(long id);

    void addToOccupiedList(long idAvail, long idOccup);

    void removeFromOccupiedList(long idOccup);

    void clearOccupiedList(long availId);


    //Admin
    void deleteByParkingSpot(long id);


}
