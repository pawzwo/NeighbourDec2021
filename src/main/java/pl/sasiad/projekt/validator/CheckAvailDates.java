package pl.sasiad.projekt.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CheckAvailDatesValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface CheckAvailDates {

    String message() default "{wrongDate.error.message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}