package io.github.sayantandaswork.httpvalidation.testcontroller;

import io.github.sayantandaswork.httpvalidation.model.RequestPojo;
import io.github.sayantandaswork.httpvalidation.validation.ValidationUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class TestValidation {

    @GetMapping("/test-validation/testAll")
    public String runAllFieldsValidation(@RequestBody RequestPojo requestPojo)
    {
        boolean f = ValidationUtils.validateAll(requestPojo);
        return "Validated all fields all together : " + f;
    }

    @GetMapping("/test-validation/testFieldWise")
    public String fieldWiseValidation(@RequestBody RequestPojo requestPojo) {
        boolean f = ValidationUtils.validateFieldWise(requestPojo)
                && ValidationUtils.validateFieldWise(requestPojo.getNestedClass());
        return "Validated field wise all together : " + f;
    }
}
