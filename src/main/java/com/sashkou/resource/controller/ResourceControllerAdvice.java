package com.sashkou.resource.controller;

import com.sashkou.resource.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackageClasses = ResourceController.class)
public class ResourceControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResourceNotFoundException onResourceNotFoundException(ResourceNotFoundException ex) {
        log.error("handling ResourceNotFoundException {}", ex.getMessage());

        return ex;
    }
}
