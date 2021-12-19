package pl.sasiad.projekt.service;

import org.springframework.stereotype.Service;
import pl.sasiad.projekt.entities.Available;
import pl.sasiad.projekt.entities.ParkingSpot;
import pl.sasiad.projekt.repository.AvailableRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AvailableServiceImpl implements AvailableService{

    private final AvailableRepository availableRepository;

    public AvailableServiceImpl(AvailableRepository availableRepository) {
        this.availableRepository = availableRepository;
    }

    @Override
    public void saveNew(int disabled, LocalDate end, int occupied, LocalDate start, ParkingSpot spot) {
        availableRepository.saveNew(disabled, end, occupied, start, spot);
    }

    @Override
    public void addToOccupiedList(long idAvail, long idOccup) {
        availableRepository.addToOccupiedList(idAvail, idOccup);
    }

    @Override
    public void removeFromOccupiedList(long idOccup){
        availableRepository.removeFromOccupiedList(idOccup);
    }

    @Override
    public void clearOccupiedList(long availId){
        availableRepository.clearOccupiedList(availId);
    }

    @Override
    public List<Available> findAllAvailableByParkingSpotId(Long id) {
        return availableRepository.findAllAvailableByParkingSpotId(id);
    }

    @Override
    public Available findById(long id) {
        return availableRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Available> findAllAvailableByUserId(long id) {
        return availableRepository.findAllAvailableByUserId(id);
    }

    @Override
    public List<Available> findAllAvailableNotUser(long id) {
        return availableRepository.findAllAvailableNotUser(id);
    };

    @Override
    public void disable(long id) {
        availableRepository.disable(id);
    }

    @Override
    public void occupy(long id) {
        availableRepository.occupy(id);
    }

    @Override
    public void deleteByParkingSpot(long id) {
        availableRepository.deleteByParkingSpot(id);
    }
}
