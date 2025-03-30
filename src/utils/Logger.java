package src.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    public enum LogLevel {
        INFO, WARNING, ERROR
    }

    private final String className;

    public Logger(Class<?> clazz) {
        this.className = clazz.getSimpleName();
    }

    private static void log(LogLevel level, String className, String message) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = now.format(formatter);
        
        System.out.println("[" + timestamp + "] [" + level + "] [" + className + "] " + message);
    }

    public void info(String message) {
        Logger.log(LogLevel.INFO, className, message);
    }

    public void warn(String message) {
        Logger.log(LogLevel.WARNING, className, message);
    }

    public void error(String message) {
        Logger.log(LogLevel.ERROR, className, message);
    }
}

