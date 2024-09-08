package io.github.sayantandaswork.httpvalidation.validation;

import io.github.sayantandaswork.httpvalidation.model.RequestPojo;

import javax.validation.*;
import java.lang.reflect.Field;
import java.util.Set;

public class ValidationUtils {

    private static final ValidatorFactory validatorFactory;
    private static final Validator validator;

    static {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    public static boolean validateAll(RequestPojo objToValidate)
    {
        Set<ConstraintViolation<RequestPojo>> constraintViolations = validator.validate(objToValidate);
        if(!constraintViolations.isEmpty())
            throw new ConstraintViolationException(constraintViolations);
        return true;
    }

    public static <T> boolean validateFieldWise(T objToValidate) {
        Field[] fields = objToValidate.getClass().getDeclaredFields();
        Set<ConstraintViolation<T>> constraintViolations;
        for (Field field : fields) {

            constraintViolations = validator.validateProperty(objToValidate, field.getName());
            if(!constraintViolations.isEmpty())
                throw new ConstraintViolationException(constraintViolations);
        }
        return true;
    }

    public void closeValidation(){
        validatorFactory.close();
    }
}
