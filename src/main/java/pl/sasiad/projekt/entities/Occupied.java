package pl.sasiad.projekt.entities;


import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sasiad.projekt.validator.CheckOccupDates;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "occupied")
@CheckOccupDates
public class Occupied {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotNull(message = "Please select start date")
    private LocalDate start;
    @NotNull(message = "Please select end date")
    private LocalDate end;
    private int disable;

    @NotNull
    long availableId;
    @OneToOne
    private User user;
    @ManyToOne
    private ParkingSpot parkingSpot;



}
