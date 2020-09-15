package com.walmart.shopping.core;

import com.walmart.shopping.core.exception.IntelLoggerException;

public class IntelLogger implements Constants {

    public static LoggerRegister info(String action) {
        validateAction(action);
        return createLoggerRegister("info", action);
    }

    public static LoggerRegister error(String action) {
        validateAction(action);
        return createLoggerRegister("error", action);
    }

    private static LoggerRegister createLoggerRegister(String level, String action) {
        LoggerRegister loggerRegister = new LoggerRegister();
        loggerRegister.level = level;
        loggerRegister.action = action;
        return loggerRegister;
    }

    private static void validateAction(String action){
        if (EMPTY_STRING.equals(action) || action == null){
            throw  new IntelLoggerException("action is required");
        }
    }

    public static class LoggerRegister {
        private String level = EMPTY_STRING;
        private String action = EMPTY_STRING;
        private String description = EMPTY_STRING;
        private String message = EMPTY_STRING;
        private String requestId = EMPTY_STRING;

        public LoggerRegister description(String description) {
            this.description = description;
            return this;
        }

        public LoggerRegister message(String message) {
            this.message = message;
            return this;
        }

        public LoggerRegister requestId(String requestId) {
            this.requestId = requestId;
            return this;
        }

        public void to(org.slf4j.Logger log) {
            switch (level) {
                case "info" : log.info(String.format("requestId=%s, action=%s, description=%s, message=%s",
                        requestId, action, description, message));
                    break;
                case "error" : log.error(String.format("requestId=%s, action=%s, description=%s, error=%s",
                        requestId, action, description, message));
                    break;
            }
        }
    }
}
