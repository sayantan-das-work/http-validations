package io.github.sayantandaswork.httpvalidation.validation.validators;

import io.github.sayantandaswork.httpvalidation.validation.Age;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AgeValidator implements ConstraintValidator<Age, Long> {
    private long minAgeAllowed;
    private long maxAgeAllowed;

    private String minAgeErrMsg;

    private String maxAgeErrMsg;

    @Override
    public void initialize(Age ageConstraintAnnotation) {
        this.minAgeAllowed = ageConstraintAnnotation.minAge();
        this.maxAgeAllowed = ageConstraintAnnotation.maxAge();
        this.minAgeErrMsg = ageConstraintAnnotation.minAgeExceedErrMsg();
        this.maxAgeErrMsg = ageConstraintAnnotation.maxAgeExceedErrMsg();
    }

    @Override
    public boolean isValid(Long ageToCheck, ConstraintValidatorContext constraintValidatorContext) {
        if(ageToCheck<minAgeAllowed)
        {
            generateValidationContext(constraintValidatorContext,true);
            return false;
        }
        else if(ageToCheck>maxAgeAllowed)
        {
            generateValidationContext(constraintValidatorContext,false);
            return false;
        }
        return true;
    }

    private void generateValidationContext(
            ConstraintValidatorContext constraintValidatorContext,boolean forMinAgeCheck) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext
                .buildConstraintViolationWithTemplate(forMinAgeCheck ? minAgeErrMsg : maxAgeErrMsg)
                .addConstraintViolation();
    }
}
