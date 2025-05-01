package com.labelapp.production_service.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ExternalServiceException extends RuntimeException {
    public ExternalServiceException(String message) {
        super(message);
    }
}
