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

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    UserService userService;
    ParkingSpotService spotService;
    AvailableService availableService;
    OccupiedService occupiedService;

    public AdminController(UserService userService, ParkingSpotService spotService, AvailableService availableService, OccupiedService occupiedService) {
        this.userService = userService;
        this.spotService = spotService;
        this.availableService = availableService;
        this.occupiedService = occupiedService;
    }

    @GetMapping("/main")
    public String getMain(@AuthenticationPrincipal CurrentUser currentUser, Model model) {

        model.addAttribute("admin", currentUser.getUser());
        model.addAttribute("users", userService.selectAllBasicInfo());
        model.addAttribute("spots", spotService.selectAll());

        return "/admin/mainAdmin";
    }

    @GetMapping("/user/details/{userId}")
    public String getUserDetails(@AuthenticationPrincipal CurrentUser currentUser, Model model, @PathVariable long userId) {
        ParkingSpot parkingSpot = new ParkingSpot();
        User user = userService.getSpots(userId);

        model.addAttribute("admin", currentUser.getUser());
        model.addAttribute("user", user);

        model.addAttribute("occupied", occupiedService.findAllBySpotOwner(user.getId()));
        model.addAttribute("availables", availableService.findAllAvailableByUserId(userId));
        model.addAttribute("spot", parkingSpot);
        model.addAttribute("occupiedByUser", occupiedService.findAllByUserId(userId));
        return "/admin/userDetailsAdmin";
    }

    @PostMapping("/parking/add/{userId}")
    public String addSpot(@AuthenticationPrincipal CurrentUser currentUser, Model model, ParkingSpot parkingSpot, @PathVariable long userId) {
        User user = userService.getSpots(userId);
        spotService.save(parkingSpot);
        List<ParkingSpot> spots = user.getParkingSpots();
        spots.add(parkingSpot);
        user.setParkingSpots(spots);
        userService.updateUser(user);
        return "redirect:/admin/user/details/"+userId;
    }

    @PostMapping("/parking/remove")
    public String deleteSpot(@RequestParam long spotId, @RequestParam long userId) {
        if (!occupiedService.findAllActiveByParkingSpotId(spotId).isEmpty()){
            for (Occupied occup:occupiedService.findAllActiveByParkingSpotId(spotId)) {
                availableService.removeFromOccupiedList(occup.getId());
            }
        }

        if (!availableService.findAllAvailableByParkingSpotId(spotId).isEmpty()){
            availableService.deleteByParkingSpot(spotId);
        }
        if (!occupiedService.findAllByParkingSpotId(spotId).isEmpty()){
            occupiedService.deleteAllByParkingSpot(spotId);
        }
        userService.deleteUserSpotByParkingSpot(spotId);
        spotService.delete(spotId);
        return "redirect:/admin/user/details/"+userId;
    }

    @PostMapping("/parking/disable")
    public String disableSpot(@RequestParam long spotId) {
        spotService.disable(spotId);
        return "redirect:/admin/main";
    }

    @PostMapping("/parking/enable")
    public String enableSpot(@RequestParam long spotId) {
        spotService.enable(spotId);
        return "redirect:/admin/main";
    }

    @PostMapping("/user/enable")
    public String enableUser(@RequestParam long userId) {
        userService.enableSetRoleUser(userId);
        return "redirect:/admin/main";
    }

    @PostMapping("/user/disable")
    public String disableUser(@RequestParam long userId) {
        userService.disableUser(userId);
        return "redirect:/admin/main";
    }

    @GetMapping("/makeAdmin/{userId}")
    public String makeAdmin(@PathVariable long userId) {
        userService.makeAdmin(userId);
        return "redirect:/admin/user/details/"+userId;
    }
    @GetMapping("/makeUser/{userId}")
    public String makeUser(@PathVariable long userId) {
        userService.makeUser(userId);
        return "redirect:/admin/user/details/"+userId;
    }

}
