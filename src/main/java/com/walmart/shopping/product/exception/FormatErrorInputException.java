package com.walmart.shopping.product.exception;

import com.walmart.shopping.core.IntelLogger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FormatErrorInputException extends RuntimeException{

    public FormatErrorInputException(Exception exception, String error) {
        super(exception);
        IntelLogger.error("formatErrorException")
                .message(error)
                .to(log);
    }
}
