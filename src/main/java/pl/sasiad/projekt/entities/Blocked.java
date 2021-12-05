package pl.sasiad.projekt.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "blocked")
public class Blocked {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    private int status;
    LocalDate start;
    LocalDate end;

    @OneToOne
    Owner owner;
    @OneToOne
    ParkingSpot parkingSpot;



}
