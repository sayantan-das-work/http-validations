package io.github.sayantandaswork.httpvalidation.validation;

import io.github.sayantandaswork.httpvalidation.validation.validators.AgeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeValidator.class)
public @interface Age {
    int minAge() default 16;
    int maxAge() default 80;

    String minAgeExceedErrMsg() default "You're too young for this software";

    String maxAgeExceedErrMsg() default "You're too old for this software";

    String message() default "Invalid age provided";

    Class <?> [] groups() default {};

    Class <? extends Payload> [] payload() default {};
}
