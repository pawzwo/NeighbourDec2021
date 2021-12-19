package pl.sasiad.projekt.validator;

import pl.sasiad.projekt.entities.Available;
import pl.sasiad.projekt.service.AvailableService;
import pl.sasiad.projekt.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class CheckAvailDatesValidator implements ConstraintValidator<CheckAvailDates, Available> {


    AvailableService availableService;

    public CheckAvailDatesValidator(AvailableService availableService) {
        this.availableService = availableService;
    }

    @Override
    public void initialize(CheckAvailDates constraintAnnotation) {
    }

    @Override
    public boolean isValid(Available available, ConstraintValidatorContext context) {
        boolean alreadyAvail = false;
        if (available.getStart()!=null && available.getEnd()!=null){
            for (Available el:availableService.findAllAvailableByParkingSpotId(available.getParkingSpot().getId())) {
                if (el.getStart().isEqual(available.getStart())||el.getStart().isEqual(available.getEnd())||el.getEnd().isEqual(available.getStart())||el.getEnd().isEqual(available.getEnd())) {
                    alreadyAvail = true;
                }
                if (el.getStart().isBefore(available.getStart()) && el.getEnd().isAfter(available.getStart())) {
                    alreadyAvail = true;
                }
                if (el.getStart().isAfter(available.getStart())&&el.getStart().isBefore(available.getEnd())) {
                    alreadyAvail = true;
                }
            }
        }

        return available.getStart() != null && available.getEnd()!=null &&
                !available.getEnd().isBefore(available.getStart()) && !available.getStart().isBefore(LocalDate.now()) && !alreadyAvail;
    }
}
