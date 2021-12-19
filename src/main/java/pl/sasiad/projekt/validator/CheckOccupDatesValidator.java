package pl.sasiad.projekt.validator;

import pl.sasiad.projekt.entities.Available;
import pl.sasiad.projekt.entities.Occupied;
import pl.sasiad.projekt.service.AvailableService;
import pl.sasiad.projekt.service.OccupiedService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.util.List;

public class CheckOccupDatesValidator implements ConstraintValidator<CheckOccupDates, Occupied> {


    AvailableService availableService;
    OccupiedService occupiedService;

    public CheckOccupDatesValidator(AvailableService availableService, OccupiedService occupiedService) {
        this.availableService = availableService;
        this.occupiedService = occupiedService;
    }

    @Override
    public void initialize(CheckOccupDates constraintAnnotation) {
    }

    @Override
    public boolean isValid(Occupied occupied, ConstraintValidatorContext context) {
        boolean isWithinAvail=true;
        Available available = availableService.findById(occupied.getAvailableId());
        if (occupied.getStart().isBefore(available.getStart()) || occupied.getEnd().isAfter(available.getEnd())) {
            isWithinAvail = false;
        }
        boolean notOccup = true;
        List<Occupied> occupiedList = available.getOccupiedList();
        for (Occupied el:occupiedList) {
            if (el.getStart().isEqual(occupied.getStart()) || el.getStart().isEqual(occupied.getEnd())
                    || el.getEnd().isEqual(occupied.getStart()) ||el.getEnd().isEqual(occupied.getEnd())) {
                notOccup = false;
            }
            if (el.getStart().isBefore(occupied.getStart())&&el.getEnd().isAfter(occupied.getStart())) {
                notOccup = false;
            }
            if (el.getStart().isAfter(occupied.getStart())&&el.getStart().isBefore(occupied.getEnd())) {
                notOccup = false;
            }
        }
        return occupied.getStart() != null && occupied.getEnd()!=null && !occupied.getStart().isBefore(LocalDate.now())
               && !occupied.getEnd().isBefore(occupied.getStart()) && isWithinAvail && notOccup;
    }
}