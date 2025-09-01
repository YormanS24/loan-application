package co.com.loadapplication.slf4juselogger;

import co.com.loadapplication.model.utils.LoanApplicationLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Slf4JUseLogger implements LoanApplicationLogger {

    private static final Logger logger = LoggerFactory.getLogger(Slf4JUseLogger.class);

    @Override
    public void trace(String message) {
        logger.trace(message);
    }

    @Override
    public void debug(String message) {
        logger.debug(message);
    }

    @Override
    public void info(String message) {
        logger.info(message);
    }

    @Override
    public void warn(String message) {
        logger.warn(message);
    }

    @Override
    public void error(String message, Throwable t) {
        logger.error(message, t);
    }
}
