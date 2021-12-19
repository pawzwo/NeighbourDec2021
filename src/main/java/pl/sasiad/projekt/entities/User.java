package pl.sasiad.projekt.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sasiad.projekt.validator.CheckEmail;
import pl.sasiad.projekt.validator.CheckLogin;
import pl.sasiad.projekt.validator.EqualPasswords;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
@EqualPasswords()
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;


    @CheckLogin(message = "Please select a different login")
    @Size(min = 2, message = "Must have at least two characters")
    @Column(unique = true)
    private String login;

    @CheckEmail(message = "Please use your own email")
    @Email(message = "Please use a correct email")
    private String email;

    @Size(min = 5, message = "Must have at least five characters")
    private String password;
    @Size(min = 5, message = "Must have at least five characters")
    private String confirmPassword;
    @Size(min = 2, message = "Must have at least two characters")
    String firstName;
    @Size(min = 2, message = "Must have at least two characters")
    String lastName;
    String address;

    private int enabled;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    int points;

    @OneToMany
    private List<ParkingSpot> parkingSpots;

}
