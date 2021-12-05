package pl.sasiad.projekt.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "available")
public class Available {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    private int status;
    LocalDate start;
    LocalDate end;

    @OneToOne
    ParkingSpot parkingSpot;

}
