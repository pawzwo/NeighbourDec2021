package pl.sasiad.projekt.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sasiad.projekt.entities.Available;
import pl.sasiad.projekt.entities.Occupied;
import pl.sasiad.projekt.entities.ParkingSpot;
import pl.sasiad.projekt.entities.User;
import pl.sasiad.projekt.model.CurrentUser;
import pl.sasiad.projekt.service.AvailableService;
import pl.sasiad.projekt.service.OccupiedService;
import pl.sasiad.projekt.service.ParkingSpotService;
import pl.sasiad.projekt.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AvailableService availableService;
    private final OccupiedService occupiedService;

    public UserController(UserService userService, AvailableService availableService, OccupiedService occupiedService) {
        this.userService = userService;
        this.availableService = availableService;
        this.occupiedService = occupiedService;
    }

    @GetMapping("/main")
    public String getMain(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        User user = userService.getSpots(currentUser.getUser().getId());
        Available available = new Available();

        model.addAttribute("available", available);
        model.addAttribute("occupied", occupiedService.findAllBySpotOwner(user.getId()));
        model.addAttribute("availables", availableService.findAllAvailableByUserId(currentUser.getUser().getId()));
        model.addAttribute("user", user);
        model.addAttribute("occupiedByUser", occupiedService.findAllByUserId(currentUser.getUser().getId()));
        return "/user/mainUser";
    }
    @GetMapping("/edit")
    public String showEditForm(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        model.addAttribute("user", userService.findById(currentUser.getUser().getId()));
        return "user/editUser";
    }

    @PostMapping("/edit")
    public String getEditForm(@AuthenticationPrincipal CurrentUser currentUser, User user) {
        userService.updateUserDetails(user.getFirstName(), user.getLastName(), user.getEmail(), user.getAddress(), user.getLogin(), currentUser.getUser().getId());
        return "redirect:/user/main";
    }


}
