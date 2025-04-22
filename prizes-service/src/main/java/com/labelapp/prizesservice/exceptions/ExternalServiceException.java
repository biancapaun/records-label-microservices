package com.labelapp.prizesservice.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ExternalServiceException extends RuntimeException {
    public ExternalServiceException(String message) {
        super(message);
    }
}
