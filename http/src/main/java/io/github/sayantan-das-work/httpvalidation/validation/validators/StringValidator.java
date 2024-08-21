package com.validation.http.validation.validators;

import com.validation.http.exception.IllegalValidationConstraintsException;
import com.validation.http.validation.StringValidation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Slf4j
public class StringValidator implements ConstraintValidator<StringValidation, String> {
    private static final String LOG_PREFIX = StringValidator.class.getName();
    private Pattern pattern;

    private boolean nullValueAllowed;
    private boolean emptyValueAllowed;
    private int minLength;
    private int maxLength;

    private String nullAndEmptyValueErrMsg;
    private String minLenErrMsg;
    private String maxLenErrMsg;
    private String patternErrMsg;


    @Override
    public void initialize(StringValidation constraintAnnotation) {
        this.nullAndEmptyValueErrMsg = constraintAnnotation.nullAndEmptyValueErrMsg();
        this.minLenErrMsg = constraintAnnotation.minSizeErrMsg();
        this.maxLenErrMsg = constraintAnnotation.maxSizeErrMsg();
        this.patternErrMsg = constraintAnnotation.patternErrMsg();

        this.nullValueAllowed = !constraintAnnotation.required() && constraintAnnotation.nullAllowed();
        this.emptyValueAllowed = !constraintAnnotation.required() && constraintAnnotation.emptyAllowed();

        this.minLength = Math.max(constraintAnnotation.minSize(), 0);
        this.maxLength = Math.max(constraintAnnotation.maxSize(), 0);

        if(this.maxLength<this.minLength)
        {
            log.error("[ {}.initialize() ] :: Maximum Size cannot be less than Minimum Size", LOG_PREFIX);
            throw new IllegalValidationConstraintsException(
                    (LOG_PREFIX + " :: Maximum Size cannot be less than Minimum Size"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        try{
            pattern = Pattern.compile(constraintAnnotation.regexp());
        }catch (PatternSyntaxException patternSyntaxException){
            log.error("[ {}.initialize() ] :: Invalid regex pattern provided", LOG_PREFIX);
            throw new IllegalValidationConstraintsException(
                    (LOG_PREFIX + " :: Invalid regexp has been provided"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext ctx) {
        /// check for null constraint
        if(nullValueAllowed && s==null)
            return true;
        else if(!nullValueAllowed && s==null)
        {
            setValidationCustomContext(ctx, nullAndEmptyValueErrMsg);
            return false;
        }

        /// check for empty constraint
        if(emptyValueAllowed && (StringUtils.isAllEmpty(s) || StringUtils.isAllBlank(s)))
            return true;
        else if(!emptyValueAllowed && (StringUtils.isAllEmpty(s) || StringUtils.isAllBlank(s)))
        {
            setValidationCustomContext(ctx, nullAndEmptyValueErrMsg);
            return false;
        }

        /// check for length constraint
        int len = s.length();
        if(len<minLength)
        {
            setValidationCustomContext(ctx, minLenErrMsg);
            return false;
        }
        if(len>maxLength)
        {
            setValidationCustomContext(ctx,maxLenErrMsg);
            return false;
        }

        /// check for pattern constraint
        try {
            Matcher matcher = pattern.matcher(s);
            if(!matcher.matches())
            {
                setValidationCustomContext(ctx,patternErrMsg);
                return false;
            }
        } catch (Exception e) {
            log.error("[ {}.isValid() ] Exception :: {}", LOG_PREFIX,e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        return true;
    }

    private void setValidationCustomContext(ConstraintValidatorContext ctx, String customMsg)
    {
        ctx.disableDefaultConstraintViolation();
        ctx.buildConstraintViolationWithTemplate(customMsg).addConstraintViolation();
    }
}
