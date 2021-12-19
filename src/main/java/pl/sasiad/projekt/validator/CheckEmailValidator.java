package pl.sasiad.projekt.validator;

import pl.sasiad.projekt.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckEmailValidator implements ConstraintValidator<CheckEmail, String> {


    UserService userService;

    public CheckEmailValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(CheckEmail constraintAnnotation) {
    }
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return email != null && !userService.existsByEmail(email);
    }
}
