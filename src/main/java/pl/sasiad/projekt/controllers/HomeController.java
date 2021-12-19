package pl.sasiad.projekt.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MessageCodeFormatter;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pl.sasiad.projekt.entities.User;
import pl.sasiad.projekt.mail.EmailService;
import pl.sasiad.projekt.model.CurrentUser;
import pl.sasiad.projekt.service.MessageServiceImpl;
import pl.sasiad.projekt.service.UserService;

import javax.validation.Valid;
import javax.validation.Validator;

@Controller
public class HomeController {

    private final UserService userService;
    private final MessageServiceImpl messageService;

    public HomeController(UserService userService, MessageServiceImpl messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @GetMapping("/signIn")
    public String showSignInForm() {
        return "signIn";
    }

    @GetMapping("/signUp")
    public String showSignInForm(Model model) {
        User user = new User();
        model.addAttribute(user);
        return "signUp";
    }

    @PostMapping("/signUp")
    public String GetSignUpForm(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            for (ObjectError error:bindingResult.getAllErrors()) {
                if (error.getCode().contains("EqualPasswords")) {
                    model.addAttribute("notEqualPasswords", "Password must match Confirm password");
                }
            }
            return "signUp";
        }
        userService.saveUser(user);
        messageService.sendConfirmEmail(user);
        return "redirect:/user/main";
    }

    @GetMapping("/newUser/{id}")
    @ResponseBody
    public String newUserMessage(@PathVariable long id) {
        messageService.sendActivateUserEmail(id);
        return "Thank you for you patience, you'll hear from us soon.";
    }



}
