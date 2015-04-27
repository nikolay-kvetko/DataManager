package com.intetics.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = DuplicateEntityConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DuplicateEntity {

    String message() default "{DuplicateEntity}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
