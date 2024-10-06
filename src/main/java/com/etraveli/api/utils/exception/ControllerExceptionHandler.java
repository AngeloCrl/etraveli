package com.etraveli.api.utils.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(value = {ResponseStatusException.class})
    public ResponseEntity<ErrorObject> handleResponseStatusException(ResponseStatusException e, WebRequest request) {
        String requestURI = ((ServletWebRequest) request).getRequest().getRequestURI();
        LOGGER.debug("Caught an error of type {}", e.getClass(), e);
        ErrorObject error = ErrorObject.builder()
                .exceptionType(ResponseStatusException.class.getSimpleName())
                .content(e.getReason())
                .status(e.getStatusCode().value())
                .request(requestURI)
                .build();
        return ResponseEntity
                .status(e.getStatusCode())
                .body(error);
    }
}
