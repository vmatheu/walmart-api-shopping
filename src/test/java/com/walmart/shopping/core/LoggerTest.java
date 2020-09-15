package com.walmart.shopping.core;

import ch.qos.logback.classic.spi.ILoggingEvent;
import com.walmart.shopping.core.exception.IntelLoggerException;
import com.walmart.shopping.utils.LoggerUtil;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class LoggerTest {

    @Test
    public void shouldRegisterLogWithLevelInfo(){
        LoggerUtil log = new LoggerUtil();

        IntelLogger.info("test")
                .description("description")
                .message("message")
                .requestId("reqId")
                .to(log.getLogger());

        List<ILoggingEvent> logsList = log.getList();

        Assertions.assertThat(logsList.get(0).toString())
                .isEqualTo("[INFO] requestId=reqId, action=test, description=description, message=message");
        Assertions.assertThat(logsList.size()).isEqualTo(1);

    }

    @Test
    public void shouldRegisterLogError(){
        LoggerUtil log = new LoggerUtil();

        IntelLogger.error("testError")
                .description("description")
                .message("message")
                .requestId("reqId")
                .to(log.getLogger());

        List<ILoggingEvent> logsList = log.getList();

        Assertions.assertThat(logsList.get(0).toString())
                .isEqualTo("[ERROR] requestId=reqId, action=testError, description=description, error=message");
        Assertions.assertThat(logsList.size()).isEqualTo(1);

    }

    @Test
    public void shouldReturnErrorWhenActionIsEmpty() throws IntelLoggerException {
        LoggerUtil log = new LoggerUtil();

        Exception exception = assertThrows(IntelLoggerException.class, () -> {
            IntelLogger.info("")
                    .description("description")
                    .message("message")
                    .requestId("reqId")
                    .to(log.getLogger());
        });

        String expectedMessage = "action is required";
        Assertions.assertThat(exception.getMessage()).isEqualTo(expectedMessage);
    }
}
