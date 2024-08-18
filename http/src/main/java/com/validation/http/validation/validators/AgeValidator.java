package com.validation.http.validation.validators;

import com.validation.http.validation.Age;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AgeValidator implements ConstraintValidator<Age, Integer> {
    private static enum AgeValidatorCheck{
        MIN,
        MAX
    }
    private int minAgeAllowed;
    private int maxAgeAllowed;

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
    public boolean isValid(Integer ageToCheck, ConstraintValidatorContext constraintValidatorContext) {
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
