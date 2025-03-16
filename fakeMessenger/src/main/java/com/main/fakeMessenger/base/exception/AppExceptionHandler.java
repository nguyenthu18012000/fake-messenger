package com.main.fakeMessenger.base.exception;

import com.main.fakeMessenger.base.ApiResponse;
import com.main.fakeMessenger.base.service.ErrorMessageService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

    protected static final Logger logger = LogManager.getLogger(AppExceptionHandler.class);

    @Autowired
    private ErrorMessageService errorMessageService;

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<ApiResponse<?>> handleCustomException(CustomException ex, final Throwable throwable) {
        String errorCode = this.errorMessageService.getCode("error.code." + ex.getErrorCode());
        String errorMessageTemplate = this.errorMessageService.getMessage("error.message." + ex.getErrorCode());
        String parsedErrorMessage = String.format(errorMessageTemplate);
        ApiResponse<?> apiResponse = new ApiResponse<>(errorCode, errorMessageTemplate);

        Throwable rootCause;
        for (rootCause = throwable; rootCause.getCause() != null && rootCause.getCause() != rootCause; rootCause = rootCause.getCause()) {
        }

        String logMessage = StringUtils.isEmpty(throwable.getMessage()) ? parsedErrorMessage : throwable.getMessage();
        String var10001 = rootCause.getStackTrace()[0].getClassName();
        logger.error("Class: {}, Method: {} Error: {}", var10001, rootCause.getStackTrace()[0].getMethodName(), logMessage);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
