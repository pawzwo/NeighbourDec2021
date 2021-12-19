package pl.sasiad.projekt.service;

import org.springframework.stereotype.Service;
import pl.sasiad.projekt.entities.ParkingSpot;
import pl.sasiad.projekt.repository.ParkingSpotRepository;

import java.util.List;

@Service
public class ParkingSpotServiceImpl implements ParkingSpotService{

    ParkingSpotRepository spotRepository;

    public ParkingSpotServiceImpl(ParkingSpotRepository spotRepository) {
        this.spotRepository = spotRepository;
    }

    @Override
    public ParkingSpot findById(long id) {
        return spotRepository.findById(id).orElseThrow();
    }

    @Override
    public List<ParkingSpot> selectAll() {
        return spotRepository.selectAll();
    }


    //Admin
    @Override
    public void save(ParkingSpot parkingSpot){
        parkingSpot.setDisable(0);
        spotRepository.save(parkingSpot);
    }

    @Override
    public void delete(long id) {
        spotRepository.delete(findById(id));
    }

    @Override
    public void disable(long id){
        spotRepository.disable(id);
    }

    @Override
    public void enable(long id){
        spotRepository.enable(id);
    }



}
