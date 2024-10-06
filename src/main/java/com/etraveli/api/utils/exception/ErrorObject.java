package com.etraveli.api.utils.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@AllArgsConstructor
@Getter
@Builder
public class ErrorObject {
    private final int status;
    private final String exceptionType;
    private final Object content;
    private final String request;
}