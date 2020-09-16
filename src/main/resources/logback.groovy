appender("INCLUDED_CONSOLE", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "[%date{yyyy/MM/dd HH:mm:ss.SSS}] [%thread] [%logger] [%level] product=walmark-api-shopping  %m%n%rootException"
    }
}

logger("org.springframework", ERROR)
logger("org.catalina", ERROR)
logger("org.apache", ERROR)
logger("org.mongodb", ERROR)

root(INFO, ["INCLUDED_CONSOLE"])
