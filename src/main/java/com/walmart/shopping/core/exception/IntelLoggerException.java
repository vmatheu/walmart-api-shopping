package com.walmart.shopping.core.exception;

import com.walmart.shopping.core.IntelLogger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IntelLoggerException extends RuntimeException {
    public IntelLoggerException(String error) {
        super(error);
        IntelLogger.error("loggerException")
                .message(error)
                .to(log);
    }
}
