package io.github.sayantandaswork.httpvalidation.model;

import io.github.sayantandaswork.httpvalidation.validation.StringValidation;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NestedClass {

    @StringValidation(
            nullAllowed = false,
            maxSize = 10,
            minSizeErrMsg = "Nested class string val violated min size"
    )
    private String strVal2;
}
