package com.walmart.shopping.product.exception;

import com.walmart.shopping.core.IntelLogger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FormatErrorInput extends RuntimeException{

    public FormatErrorInput(Exception exception, String error) {
        super(exception);
        IntelLogger.error("loggerException")
                .message(error)
                .to(log);
    }
}
