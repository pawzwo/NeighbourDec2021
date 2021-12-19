package pl.sasiad.projekt.validator;

import pl.sasiad.projekt.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckLoginValidator implements ConstraintValidator<CheckLogin, String> {


    UserService userService;

    public CheckLoginValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(CheckLogin constraintAnnotation) {
    }
    @Override
    public boolean isValid(String login, ConstraintValidatorContext context) {
        return login != null && !userService.existsByLogin(login);
    }
}
