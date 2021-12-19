package pl.sasiad.projekt.service;

import pl.sasiad.projekt.entities.Occupied;
import pl.sasiad.projekt.entities.User;
import pl.sasiad.projekt.model.CurrentUser;

public interface MessageService {

    //Home
    void sendConfirmEmail(User user);

    void sendActivateUserEmail(long id);

    //Available
    void sendNewSpotAvailable(long id);

    void sendYourParkingSpotAvailable(long idUser, long idSpot);

    //Occupied
    void sendYourParkingSpotIsUsed(Occupied occupied);

    void youAreUsingParkingSpot(CurrentUser currentUser, Occupied occupied);

}
