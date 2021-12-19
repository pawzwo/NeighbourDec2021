package pl.sasiad.projekt.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pl.sasiad.projekt.entities.Available;
import pl.sasiad.projekt.entities.Occupied;
import pl.sasiad.projekt.entities.User;
import pl.sasiad.projekt.mail.EmailService;
import pl.sasiad.projekt.model.CurrentUser;
import pl.sasiad.projekt.service.AvailableService;
import pl.sasiad.projekt.service.MessageService;
import pl.sasiad.projekt.service.OccupiedService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/occupied")
public class OccupiedController {

    OccupiedService occupiedService;
    AvailableService availableService;
    MessageService messageService;

    public OccupiedController(OccupiedService occupiedService, AvailableService availableService, MessageService messageService) {
        this.occupiedService = occupiedService;
        this.availableService = availableService;
        this.messageService = messageService;
    }

    @PostMapping("/add")
    public String addOccupied(@ModelAttribute ("occupiedNew") @Valid Occupied occupied, BindingResult bindingResult, Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        if (bindingResult.hasErrors()) {
            for (ObjectError error:bindingResult.getAllErrors()) {
                if (error.getCode().contains("CheckOccupDates")) {
                    model.addAttribute("CheckOccupDates", "You need to select dates, selected dates have to be within period when spot is being shared and can't overlap with other selected periods");
                }
            }
            model.addAttribute("user", currentUser.getUser());
            model.addAttribute("availSpots", availableService.findAllAvailableNotUser(currentUser.getUser().getId()));
            model.addAttribute("occupied", occupiedService.findAllByUserId(currentUser.getUser().getId()));
            return "/user/availableList";
        }
        occupied.setParkingSpot(availableService.findById(occupied.getAvailableId()).getParkingSpot());
        occupied.setUser(currentUser.getUser());
        occupiedService.save(occupied);
        availableService.occupy(occupied.getAvailableId());
        availableService.addToOccupiedList(occupied.getAvailableId(), occupied.getId());
        //messageService.sendYourParkingSpotIsUsed(occupied); Lazy initialize - nie da rady znalezc maila po parking psotach
        //messageService.youAreUsingParkingSpot(currentUser, occupied);
        return "redirect:/available/list";
    }

    @PostMapping("/disable")
    public String disableOccupied(@RequestParam long occupiedId, @AuthenticationPrincipal CurrentUser currentUser) {
        if (occupiedService.findById(occupiedId).getUser().getId() == currentUser.getUser().getId()) {
            occupiedService.disable(occupiedId);
            availableService.removeFromOccupiedList(occupiedId);
            //messageService.youAreNoLongerUsingPs do zrobienia
            //messageService.yourPsIsNoLongerUsed do zrobienia
            return "redirect:/user/main";
        } else {
            return "home";
        }
    }

}
