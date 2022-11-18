package log;

import org.apache.log4j.xml.DOMConfigurator;

public class Logger {
    static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Logger.class);
    public Logger() {
        DOMConfigurator.configure("log4j.xml");
    }
    public void info(String message) {
        logger.info(message);
    }
    public void error(String message) {
        logger.error(message);
    }
}
