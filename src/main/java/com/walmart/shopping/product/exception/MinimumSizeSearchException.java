package com.walmart.shopping.product.exception;

import com.walmart.shopping.core.IntelLogger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MinimumSizeSearchException extends RuntimeException{

    public MinimumSizeSearchException(String error) {
        super(error);
        IntelLogger.error("minimumSizeSearchException")
                .message(error)
                .to(log);
    }
}
