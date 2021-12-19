package pl.sasiad.projekt.service;

import pl.sasiad.projekt.entities.ParkingSpot;

import java.util.List;

public interface ParkingSpotService {


    ParkingSpot findById(long id);

    List<ParkingSpot> selectAll();

    //Admin
    void save(ParkingSpot parkingSpot);

    void delete(long id);

    void disable(long id);

    void enable(long id);






}
