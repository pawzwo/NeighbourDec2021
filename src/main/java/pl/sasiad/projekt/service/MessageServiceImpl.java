package pl.sasiad.projekt.service;

import org.springframework.stereotype.Service;
import pl.sasiad.projekt.entities.Available;
import pl.sasiad.projekt.entities.Occupied;
import pl.sasiad.projekt.entities.User;
import pl.sasiad.projekt.mail.EmailService;
import pl.sasiad.projekt.model.CurrentUser;

@Service
public class MessageServiceImpl implements MessageService {

    private final EmailService emailService;
    private final UserService userService;
    private final AvailableService availableService;
    private final ParkingSpotService spotService;
    private final OccupiedService occupiedService;

    public MessageServiceImpl(EmailService emailService, UserService userService, AvailableService availableService, ParkingSpotService spotService, OccupiedService occupiedService) {
        this.emailService = emailService;
        this.userService = userService;
        this.availableService = availableService;
        this.spotService = spotService;
        this.occupiedService = occupiedService;
    }

    //home
    @Override
    public void sendConfirmEmail(User user) {
        emailService.sendSimpleMessage(user.getEmail(), "Welcome to the Neighbour, please activate your account",
                String.format("In order to confirm your email please go to: http://localhost:8080/newUser/%s. " +
                                "We will need sometime to confirm your identity but it shouldn't take us longer than 24h.", user.getId()));
    }

    @Override
    public void sendActivateUserEmail(long id) {
        emailService.sendSimpleMessage("pawelzwolinski@poczta.onet.pl", "New user: "+userService.findById(id).getLogin(),
                String.format("New user %s joined the Neighbour. " +
                                "Please go to: http://localhost:8080/admin/user/details/%s to confirm his identity, attach parking spots and activate his account",
                        userService.findById(id).getLogin(), id));
    }

    //available
    @Override
    public void sendNewSpotAvailable(long id) {
        Available available = availableService.findById(id);
        for (String email:userService.findAllActiveEmails()) {
            emailService.sendSimpleMessage(email, String.format("New Parking Spot Available from %s to %s",
                    available.getStart(), available.getEnd()),
                    String.format("New Parking Spot %s, %s available from %s to %s",
                            available.getParkingSpot().getParking(), available.getParkingSpot().getPlace(), available.getStart(), available.getEnd()));
        }
    }

    @Override
    public void sendYourParkingSpotAvailable(long idUser, long idSpot) {
        emailService.sendSimpleMessage(userService.findById(idUser).getEmail(), "Your Parking Spot was made available",
                String.format("Your Parking Spot, %s, %s was made available", spotService.findById(idSpot).getParking(), spotService.findById(idSpot).getPlace()));
    }

    //occupied
    @Override
    public void sendYourParkingSpotIsUsed(Occupied occupied) {
        emailService.sendSimpleMessage(userService.findEmailByParkingSpotId(occupied.getParkingSpot().getId()), "Your Parking Spot is being used by "+occupied.getUser(),
                String.format("Your Parking Spot, %s, %s is being used by %s", occupied.getParkingSpot().getParking(), occupied.getParkingSpot().getPlace(), occupied.getUser()));
    }

    @Override
    public void youAreUsingParkingSpot(CurrentUser currentUser, Occupied occupied) {
        emailService.sendSimpleMessage(currentUser.getUser().getEmail(), "Your are using Parking Spot "+occupied.getParkingSpot().getParking(),
                String.format("Your are using Parking Spot, %s, %s.", occupied.getParkingSpot().getParking(), occupied.getParkingSpot().getPlace()));
    };

}
