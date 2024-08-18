package com.validation.http.dto;

import com.validation.http.validation.Age;
import com.validation.http.validation.StringValidation;
import lombok.*;

import javax.validation.Valid;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RequestPojo {

    @Age(
            minAge = 20,
            maxAge = 65,
            minAgeExceedErrMsg = "Minimum age must be 20"
    )
    private Integer age;

    @StringValidation(
            required = true,
            minSize = 10,
            maxSize = 6,
            regexp = "^[A-Z]*$",
            patternErrMsg = "Input contains invalid characters"
    )
    private String gender;

    @Valid
    private Name name;

    private List<@Valid Name> friends;
}
