package com.walmart.shopping.product.exception;

import com.walmart.shopping.core.IntelLogger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotFoundProductException extends RuntimeException{

    public NotFoundProductException(String error) {
        super(error);
        IntelLogger.error("notFoundProductException")
                .message(error)
                .to(log);
    }
}
