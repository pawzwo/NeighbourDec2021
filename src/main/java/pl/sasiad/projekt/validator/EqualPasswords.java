package pl.sasiad.projekt.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Constraint(validatedBy = EqualPasswordsValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface EqualPasswords {

    String message() default "{notMatchingPasswords.error.message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

}
