package pl.sasiad.projekt.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pl.sasiad.projekt.entities.*;
import pl.sasiad.projekt.mail.EmailService;
import pl.sasiad.projekt.model.CurrentUser;
import pl.sasiad.projekt.service.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/available")
public class AvailableController {

    private final AvailableService availableService;
    private final OccupiedService occupiedService;
    private final UserService userService;
    private final MessageService messageService;

    public AvailableController(AvailableService availableService, OccupiedService occupiedService, UserService userService, MessageService messageService) {
        this.availableService = availableService;
        this.occupiedService = occupiedService;
        this.userService = userService;
        this.messageService = messageService;
    }

    @PostMapping("/add/{id}")
    public String getAvailableForm(@Valid Available available, BindingResult bindingResult, Model model, @PathVariable long id, @AuthenticationPrincipal CurrentUser currentUser) {
        User user = userService.getSpots(currentUser.getUser().getId());
        if (user.getParkingSpots().contains(available.getParkingSpot())) {
            if (bindingResult.hasErrors()) {
                for (ObjectError error:bindingResult.getAllErrors()) {
                    if (error.getCode().contains("CheckAvailDates")) {
                        model.addAttribute("CheckAvailDates", "You need to select dates and selected dates can't overlap with other sharing periods");
                    }
                }
                model.addAttribute("occupied", occupiedService.findAllBySpotOwner(user.getId()));
                model.addAttribute("availables", availableService.findAllAvailableByUserId(currentUser.getUser().getId()));
                model.addAttribute("user", user);
                model.addAttribute("occupiedByUser", occupiedService.findAllByUserId(currentUser.getUser().getId()));
                return "/user/mainUser";
            }
            availableService.saveNew(0, available.getEnd(), 0, available.getStart(), available.getParkingSpot());
            //messageService.sendNewSpotAvailable(available.getId());
            //messageService.sendYourParkingSpotAvailable(currentUser.getUser().getId(), available.getParkingSpot().getId());
            return "redirect:/user/main";
        } return "home";
    }

    @GetMapping("/disable/{id}")
    public String disable(@PathVariable long id, @AuthenticationPrincipal CurrentUser currentUser) {
        User user = userService.getSpots(currentUser.getUser().getId());
        Role role = new Role();
        if (user.getParkingSpots().contains(availableService.findById(id).getParkingSpot()) || currentUser.getUser().getRoles().stream().count()>1) {
            availableService.disable(id);
            //messageService.yourPsIsNoLongerAvail do zrobienia
            if (availableService.findById(id).getOccupied() == 1) {
                for (Occupied occupied:availableService.findById(id).getOccupiedList()) {
                    occupiedService.disable(occupied.getId());
                    //messageService.psSelectedbyYouIsNoLongerAvail do zrobienia
                }
                availableService.clearOccupiedList(id);
            }
            return "redirect:/user/main";
        } else {
            return "home";
        }
    }

    @GetMapping("/list")
    public String showAvailable (Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        Occupied occupied = new Occupied();
        model.addAttribute("occupiedNew", occupied);
        model.addAttribute("user", currentUser.getUser());
        model.addAttribute("availSpots", availableService.findAllAvailableNotUser(currentUser.getUser().getId()));
        model.addAttribute("occupied", occupiedService.findAllByUserId(currentUser.getUser().getId()));
        return "user/availableList";
    }



}
