package com.validation.http.controller;

import com.validation.http.dto.RequestPojo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/validation/test")
public class ValidationTestController {

    @PostMapping
    public ResponseEntity<Object> testRequestParameterValidation(@RequestBody @Valid RequestPojo requestPojo)
    {
        return ResponseEntity.ok(requestPojo);
    }
}
