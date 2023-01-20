package io.honeybadger.reporter.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.honeybadger.reporter.config.ConfigContext;

import java.io.Serializable;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 * Details of the error being reported to the Honeybadger API.
 * @author <a href="https://github.com/dekobon">Elijah Zupancic</a>
 * @since 1.0.9
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"class", "message", "tags", "backtrace", "causes", "fingerprint"})
public class NoticeDetails implements Serializable {
    private static final long serialVersionUID = -3055963787038629496L;

    @JsonProperty("class")
    private final String className;
    private final String message;
    private final Set<String> tags;
    private final Backtrace backtrace;
    private final Causes causes;
    private final String fingerprint;

    @SuppressWarnings("unchecked")
    public NoticeDetails(final ConfigContext config, final Throwable error) {
        this(config, error, Collections.emptySet());
    }

    public NoticeDetails(final ConfigContext config, final Throwable error, final Set<String> tags) {
        this(config, error, tags, error.getMessage(), null);
    }

    public NoticeDetails(final ConfigContext config, final Throwable error, final Set<String> tags,
                         final String message, String fingerprint) {
        if (error == null) {
            throw new IllegalArgumentException("Error can't be null");
        }

        this.className = error.getClass().getName();
        this.message = message;
        this.tags = tags;
        this.backtrace = new Backtrace(config, error);
        this.causes = new Causes(config, error);
        this.fingerprint = fingerprint;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof NoticeDetails)) return false;
        NoticeDetails that = (NoticeDetails) o;
        return Objects.equals(getClassName(), that.getClassName()) &&
                Objects.equals(getMessage(), that.getMessage()) &&
                Objects.equals(getTags(), that.getTags()) &&
                Objects.equals(getBacktrace(), that.getBacktrace()) &&
                Objects.equals(getCauses(), that.getCauses()) &&
                Objects.equals(getFingerprint(), that.getFingerprint());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClassName(), getMessage(), getTags(), getBacktrace(), getCauses(), getFingerprint());
    }

    public String getClassName() {
        return className;
    }

    public String getMessage() {
        return message;
    }

    public Set<String> getTags() {
        return tags;
    }

    public Backtrace getBacktrace() {
        return backtrace;
    }

    public Causes getCauses() {
        return causes;
    }

    public String getFingerprint() {
        return fingerprint;
    }
}
