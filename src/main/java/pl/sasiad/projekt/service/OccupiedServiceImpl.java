package pl.sasiad.projekt.service;

import org.springframework.stereotype.Service;
import pl.sasiad.projekt.entities.Available;
import pl.sasiad.projekt.entities.Occupied;
import pl.sasiad.projekt.entities.ParkingSpot;
import pl.sasiad.projekt.entities.User;
import pl.sasiad.projekt.repository.OccupiedRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OccupiedServiceImpl implements OccupiedService {

    private final OccupiedRepository occupiedRepository;

    public OccupiedServiceImpl(OccupiedRepository occupiedRepository) {
        this.occupiedRepository = occupiedRepository;
    }

    public Occupied findById(Long id) {
        return occupiedRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Occupied> findAllByUserId(long id) {
        return occupiedRepository.findAllByUserId(id);
    }

    @Override
    public List<Occupied> findAllActive() {
        return occupiedRepository.findAllActive();
    }

    @Override
    public List<Occupied> findAllActiveByParkingSpotId(long spotId){
        return occupiedRepository.findAllActiveByParkingSpotId(spotId);
    }

    @Override
    public List<Occupied> findAllByParkingSpotId(long spotId){
        return occupiedRepository.findAllByParkingSpotId(spotId);
    }

    @Override
    public List<Occupied> findAllBySpotOwner(long id){
        return occupiedRepository.findAllBySpotOwner(id);
    }

    @Override
    public void save(Occupied occupied){
        occupiedRepository.save(occupied);
    }

    //Admin

    @Override
    public void deleteAllByParkingSpot(long spotId){
        occupiedRepository.deleteAllByParkingSpot(spotId);
    };

    @Override
    public void disable(long id){
        occupiedRepository.disable(id);
    }

    @Override
    public void deleteAllAvailableOccuList(long occupId){
        occupiedRepository.deleteAllAvailableOccuList(occupId);
    }


}
