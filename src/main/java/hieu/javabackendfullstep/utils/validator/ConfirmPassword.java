package hieu.javabackendfullstep.utils.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ConfirmPasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfirmPassword {
    String message() default "Invalid confirm password";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    }