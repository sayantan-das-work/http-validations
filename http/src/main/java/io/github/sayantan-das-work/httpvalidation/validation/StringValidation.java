package com.validation.http.validation;

import com.validation.http.validation.validators.StringValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StringValidator.class)
public @interface StringValidation {

    boolean required() default false;

    boolean nullAllowed() default true;

    boolean emptyAllowed() default true;

    int minSize() default 0;

    int maxSize() default Integer.MAX_VALUE;

    String regexp() default "";

    String nullAndEmptyValueErrMsg() default "Null and empty value not allowed";

    String minSizeErrMsg() default "Minimum Size constraint violated";

    String maxSizeErrMsg() default "Maximum Size constraint violated";

    String patternErrMsg() default "Input pattern invalid";

    String message() default "Invalid input provided";

    Class <?> [] groups() default {};

    Class <? extends Payload> [] payload() default {};
}
