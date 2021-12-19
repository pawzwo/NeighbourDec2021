package pl.sasiad.projekt.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CheckLoginValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface CheckLogin {

    String message() default "{loginUsed.error.message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
