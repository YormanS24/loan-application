package co.com.loadapplication.model.utils;

public interface LoanApplicationLogger {

    void trace(String message);
    void debug(String message);
    void info(String message);
    void warn(String message);
    void error(String message, Throwable t);
}
