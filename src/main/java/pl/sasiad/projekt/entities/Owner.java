package pl.sasiad.projekt.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "owners")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    private String login;
    private String email;
    private String password;
    String firstName;
    String lastName;
    String pesel;
    String address;

    int permitted;
    int admin;

    int points;

    @OneToMany
    private List<ParkingSpot> parkingSpots;

}
