package com.main.fakeMessenger.base.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(CustomException.class);

    private final String errorCode;

    public CustomException(String errorCode, Object... args) {
        this.errorCode = errorCode;
    }

    public CustomException(String errorCode, Throwable cause, Object... args) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
