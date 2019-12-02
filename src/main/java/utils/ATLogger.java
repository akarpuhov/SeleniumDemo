package utils;

//import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ATLogger {

    private static Logger LOGGER = LogManager.getLogger(ATLogger.class);

    public static void error(String message) {
        LOGGER.error(message);
    }

    public static void error(String message, Throwable throwable) {
        LOGGER.error(message, throwable);
    }

    public static void info(String message) {
        LOGGER.info(message);
    }

    public static void debug(String message) {
        LOGGER.debug(message);
    }

    public static void warn(String message) {
        LOGGER.warn(message);
    }

    public static void trace(String message) {
        LOGGER.trace(message);
    }

    public static void log(String message){
        LOGGER.info(message);
    }

    static void attach(String filePath, String message) {
        LOGGER.info("RP_MESSAGE#FILE#{}#{}", filePath, message);
    }
}
