package io.github.sayantandaswork.httpvalidation.model;

import io.github.sayantandaswork.httpvalidation.validation.Age;
import io.github.sayantandaswork.httpvalidation.validation.StringValidation;
import lombok.*;

import javax.validation.Valid;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestPojo {

    @Age
    private Long valInt1;

    @StringValidation(
            required = true,
            minSize = 4, maxSize = 7, regexp = "^[A-Z]+$"
    )
    private String strVal1;

    @Valid
    private NestedClass nestedClass;
}
