package com.intetics.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = DuplicateUserConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DuplicateUser {

    String message() default "User with this email already exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
