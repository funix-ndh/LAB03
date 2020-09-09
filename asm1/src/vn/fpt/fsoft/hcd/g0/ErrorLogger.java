package vn.fpt.fsoft.hcd.g0;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class ErrorLogger {
    public static void log(final Logger logger, final Exception e) {
        logger.log(Level.SEVERE, "error log: ", e);
    }

    public static void fatal(final Logger logger, final Exception e) {
        log(logger, e);
        System.exit(1); // exit with non-zero
    }

    private ErrorLogger() {}
}
