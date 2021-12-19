package pl.sasiad.projekt.service;

import pl.sasiad.projekt.entities.ParkingSpot;
import pl.sasiad.projekt.entities.User;

import java.util.List;

public interface UserService {

    User findByLogin(String login);

    User findById(long id);

    List<String> findAllActiveEmails();

    String findEmailByParkingSpotId(long spotId);

    void saveUser(User user);

    User getSpots(long id);

    void updateUser(User user);

    void updateUserDetails(String firstName, String lastName, String email, String address, String login, long id);

    void deleteUserSpotByParkingSpot(long id);

    //Admin
    List<User> selectAllBasicInfo();

    boolean existsByLogin(String login);

    boolean existsByEmail(String email);

    void enableSetRoleUser(long id);

    void disableUser(long id);

    void makeAdmin(long userId);

    void makeUser(long userId);




}
