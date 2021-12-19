package pl.sasiad.projekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.sasiad.projekt.entities.User;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Transactional
    @Query (value = "UPDATE users SET first_name=?1, last_name=?2, email=?3, address=?4, login=?5 WHERE id=?6", nativeQuery = true)
    void updateUserDetails(String firstName, String lastName, String email, String address, String login, long id);

    User findByLogin(String login);

    User findById(long id);

    @Query(value = "SELECT email FROM users JOIN users_parking_spots ups on users.id = ups.user_id WHERE parking_spots_id=?1", nativeQuery = true)
    String findEmailByParkingSpotId(long spotId);

    @Query(value = "SELECT email FROM users WHERE enabled=1", nativeQuery = true)
    List<String> findAllActiveEmails();


    //Admin
    @Query(value = "SELECT * FROM users", nativeQuery = true)
    List<User> selectAllBasicInfo();

    boolean existsByLogin(String login);

    boolean existsByEmail(String email);

    @Modifying
    @Transactional
    @Query (value = "INSERT INTO user_role (user_id, role_id) VALUES (?1, 1)" , nativeQuery = true)
    void makeAdmin(long userId);

    @Modifying
    @Transactional
    @Query (value = "DELETE FROM user_role WHERE user_id=?1 AND role_id=1", nativeQuery = true)
    void makeUser(long userId);

    @Modifying
    @Transactional
    @Query (value = "INSERT INTO user_role (user_id, role_id) values (?1, 2)", nativeQuery = true)
    void setRoleUser(long id);

    @Modifying
    @Transactional
    @Query (value = "DELETE from user_role WHERE user_id=?1", nativeQuery = true)
    void removeRoleUser(long id);

    @Modifying
    @Transactional
    @Query (value = "UPDATE users SET enabled=1 WHERE users.id=?1", nativeQuery = true)
    void enableUser(long id);

    @Modifying
    @Transactional
    @Query (value = "UPDATE users SET enabled=0 WHERE users.id=?1", nativeQuery = true)
    void disableUser(long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM users_parking_spots WHERE parking_spots_id = ?1", nativeQuery = true)
    void deleteUserSpotByParkingSpot(long spotId);

}
