package utils;

import org.apache.log4j.Logger;

public class ATLogger {

    private static Logger logger = Logger.getLogger(ATLogger.class);

    public static void error(String message) {
        logger.error(message);
    }

    public static void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void trace(String message) {
        logger.trace(message);
    }

    public static void log(String message){
        logger.info(message);
    }

    public static void attach(String filePath, String message) {
        //info("RP_MESSAGE#FILE#{}#{}", filePath, message);
    }
}
