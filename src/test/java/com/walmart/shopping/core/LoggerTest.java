package com.walmart.shopping.core;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.walmart.shopping.core.exception.IntelLoggerException;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class LoggerTest {

    @Test
    public void registerLoggerInfo(){
        Logger logger = (Logger) LoggerFactory.getLogger(IntelLogger.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);

        IntelLogger.info("test")
                .description("description")
                .message("message")
                .requestId("reqId")
                .to(logger);

        List<ILoggingEvent> logsList = listAppender.list;

        Assertions.assertThat(logsList.get(0).toString())
                .isEqualTo("[INFO] requestId=reqId, action=test, description=description, message=message");
        Assertions.assertThat(logsList.size()).isEqualTo(1);

    }

    @Test
    public void registerLoggerError(){
        Logger logger = (Logger) LoggerFactory.getLogger(IntelLogger.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);

        IntelLogger.error("testError")
                .description("description")
                .message("message")
                .requestId("reqId")
                .to(logger);

        List<ILoggingEvent> logsList = listAppender.list;

        Assertions.assertThat(logsList.get(0).toString())
                .isEqualTo("[ERROR] requestId=reqId, action=testError, description=description, error=message");
        Assertions.assertThat(logsList.size()).isEqualTo(1);

    }

    @Test
    public void registerLoggerInfoWhenActionIsEmpty() throws IntelLoggerException {
        Logger logger = (Logger) LoggerFactory.getLogger(IntelLogger.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);

        Exception exception = assertThrows(IntelLoggerException.class, () -> {
            IntelLogger.info("")
                    .description("description")
                    .message("message")
                    .requestId("reqId")
                    .to(logger);
        });

        String expectedMessage = "action is required";
        Assertions.assertThat(exception.getMessage()).isEqualTo(expectedMessage);
    }
}
