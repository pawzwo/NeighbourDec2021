package pl.sasiad.projekt.service;

import org.hibernate.Hibernate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sasiad.projekt.entities.ParkingSpot;
import pl.sasiad.projekt.entities.Role;
import pl.sasiad.projekt.entities.User;
import pl.sasiad.projekt.repository.RoleRepository;
import pl.sasiad.projekt.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<String> findAllActiveEmails() {
        return userRepository.findAllActiveEmails();
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public String findEmailByParkingSpotId(long spotId){
        return userRepository.findEmailByParkingSpotId(spotId);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setConfirmPassword(passwordEncoder.encode(user.getConfirmPassword()));
        user.setEnabled(0);
        userRepository.save(user);
    }

    @Override
    public void updateUserDetails(String firstName, String lastName, String email, String address, String login, long id) {
        userRepository.updateUserDetails(firstName,lastName,email,address,login,id);
    }

    @Override
    public User getSpots(long id) {
        User user = userRepository.findById(id);
        Hibernate.initialize(user.getParkingSpots());
        return user;
    }
    @Override
    public void deleteUserSpotByParkingSpot(long id) {
        userRepository.deleteUserSpotByParkingSpot(id);
    }


    //Admin
    @Override
    public void enableSetRoleUser(long id) {
        userRepository.enableUser(id);
        userRepository.setRoleUser(id);
    }

    @Override
    public void disableUser(long id) {
        userRepository.removeRoleUser(id);
        userRepository.disableUser(id);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void makeAdmin(long userId) {
        userRepository.makeAdmin(userId);
    }

    @Override
    public void makeUser(long userId) {
        userRepository.makeUser(userId);
    }

    @Override
    public List<User> selectAllBasicInfo() {
        return userRepository.selectAllBasicInfo();
    }

    @Override
    public boolean existsByLogin(String login){
       return userRepository.existsByLogin(login);
    }

    @Override
    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

}
