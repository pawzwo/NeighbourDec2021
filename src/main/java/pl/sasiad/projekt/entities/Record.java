package pl.sasiad.projekt.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "history")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String ownerLastName;
    String localization;
    int parkingSpotNo;
    String action;
    String userLastName;

}
