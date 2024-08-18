package com.validation.http.dto;

import com.validation.http.validation.StringValidation;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Name {
    @StringValidation(
            required = true,
            minSize = 2,
            maxSize = 10,
            regexp = "^(?!.*[<>\"'*?&]).*$",
            patternErrMsg = "Input contains invalid characters (using negative lookahead)"
    )
    private String firstName;

    @StringValidation(
            minSize = 2,
            maxSize = 5,
            regexp = "^[^<>\"'*?&]*$",
            patternErrMsg = "Input contains invalid characters"
    )
    private String lastName;
}
