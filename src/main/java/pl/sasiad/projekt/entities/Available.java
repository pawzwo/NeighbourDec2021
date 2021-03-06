package pl.sasiad.projekt.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import pl.sasiad.projekt.validator.CheckAvailDates;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "available")
@CheckAvailDates()
public class Available {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotNull(message = "Please select start date")
    private LocalDate start;

    @NotNull(message = "Please select end date")
    private LocalDate end;

    @ManyToOne
    private ParkingSpot parkingSpot;
    @ManyToMany
    List<Occupied> occupiedList;

    private int disabled;
    private int occupied;

}
