package pl.sasiad.projekt.validator;

import pl.sasiad.projekt.entities.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EqualPasswordsValidator implements ConstraintValidator<EqualPasswords, User> {


    @Override
    public void initialize(EqualPasswords constraintAnnotation) {
    }
    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {
        return user.getPassword() != null && user.getConfirmPassword()!=null && user.getPassword().equals(user.getConfirmPassword());
    }

}
