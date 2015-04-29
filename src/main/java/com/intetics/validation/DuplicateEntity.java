package com.intetics.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = DuplicateEntityConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DuplicateEntity {

    String message() default "entity with this name already exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
