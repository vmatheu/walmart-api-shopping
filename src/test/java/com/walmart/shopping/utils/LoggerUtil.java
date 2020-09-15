package com.walmart.shopping.utils;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.walmart.shopping.core.IntelLogger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class LoggerUtil {
    private Logger logger;
    private ListAppender<ILoggingEvent> listAppender;

    public LoggerUtil() {
        logger = (Logger) LoggerFactory.getLogger(IntelLogger.class);
        listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);
    }

    public Logger getLogger() {
        return logger;
    }

    public List<ILoggingEvent> getList() {
        return listAppender.list;
    }
}
