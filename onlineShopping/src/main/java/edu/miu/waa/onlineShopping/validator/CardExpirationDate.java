package edu.miu.waa.onlineShopping.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CardExpirationValidator.class)
public @interface CardExpirationDate {
    String message() default "Expiration date should be in the feature and in this format MM/YY";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}