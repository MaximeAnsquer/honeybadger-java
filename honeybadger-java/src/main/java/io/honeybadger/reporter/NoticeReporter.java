package io.honeybadger.reporter;

import io.honeybadger.reporter.config.ConfigContext;

/**
 * Interface representing error reporting behavior.
 *
 * @author <a href="https://github.com/dekobon">Elijah Zupancic</a>
 * @since 1.0.9
 */
public interface NoticeReporter {
    /**
     * Send any Java {@link java.lang.Throwable} to the Honeybadger error
     * reporting interface.
     *
     * @param error error to report
     * @return UUID of error created, if there was a problem null
     */
    NoticeReportResult reportError(Throwable error);

    /**
     * Send any Java {@link java.lang.Throwable} to the Honeybadger error
     * reporting interface.
     *
     * @param error   error to report
     * @param request Object to parse for request properties
     * @return UUID of error created, if there was a problem or ignored null
     */
    NoticeReportResult reportError(Throwable error, Object request);

    /**
     * Send any Java {@link java.lang.Throwable} to the Honeybadger error
     * reporting interface with the associated tags.
     *
     * @param error   error to report
     * @param request Object to parse for request properties
     * @param message message to report instead of message associated with exception
     * @return UUID of error created, if there was a problem or ignored null
     */
    NoticeReportResult reportError(Throwable error, Object request, String message);

    /**
     * Send any Java {@link java.lang.Throwable} to the Honeybadger error
     * reporting interface with the associated tags.
     *
     * @param error   error to report
     * @param request Object to parse for request properties
     * @param message message to report instead of message associated with exception
     * @param tags    tag values (duplicates will be removed)
     * @return UUID of error created, if there was a problem or ignored null
     */
    NoticeReportResult reportError(Throwable error, Object request, String message,
                                   Iterable<String> tags);

    /**
     * Send any Java {@link java.lang.Throwable} to the Honeybadger error
     * reporting interface with the associated tags.
     *
     * @param error       error to report
     * @param request     Object to parse for request properties
     * @param message     message to report instead of message associated with exception
     * @param tags        tag values (duplicates will be removed)
     * @param fingerprint custom fingerprint (used to group errors)
     * @return UUID of error created, if there was a problem or ignored null
     */
    NoticeReportResult reportError(Throwable error, Object request, String message,
                                   Iterable<String> tags, String fingerprint);

    /**
     * @return The configuration used in the reporter
     */
    ConfigContext getConfig();
}
