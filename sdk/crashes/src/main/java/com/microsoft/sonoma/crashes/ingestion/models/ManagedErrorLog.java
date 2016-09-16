package com.microsoft.sonoma.crashes.ingestion.models;

import com.microsoft.sonoma.core.ingestion.models.json.JSONUtils;
import com.microsoft.sonoma.crashes.ingestion.models.json.ThreadFactory;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.List;

/**
 * Error log for managed platforms (such as Android Dalvik).
 */
public class ManagedErrorLog extends AbstractErrorLog {

    /**
     * Log type.
     */
    public static final String TYPE = "managed_error";

    private static final String EXCEPTION = "exception";

    private static final String THREADS = "threads";

    /**
     * Exception.
     */
    private Exception exception;

    /**
     * Thread stack traces associated to the error.
     */
    private List<Thread> threads;

    @Override
    public String getType() {
        return TYPE;
    }

    /**
     * Get the exception value.
     *
     * @return the exception value
     */
    public com.microsoft.sonoma.crashes.ingestion.models.Exception getException() {
        return this.exception;
    }

    /**
     * Set the exception value.
     *
     * @param exception the exception value to set
     */
    public void setException(com.microsoft.sonoma.crashes.ingestion.models.Exception exception) {
        this.exception = exception;
    }

    /**
     * Get the threads value.
     *
     * @return the threads value
     */
    public List<Thread> getThreads() {
        return this.threads;
    }

    /**
     * Set the threads value.
     *
     * @param threads the threads value to set
     */
    public void setThreads(List<Thread> threads) {
        this.threads = threads;
    }

    @Override
    public void read(JSONObject object) throws JSONException {
        super.read(object);
        if (object.has(EXCEPTION)) {
            JSONObject jException = object.getJSONObject(EXCEPTION);
            com.microsoft.sonoma.crashes.ingestion.models.Exception exception = new com.microsoft.sonoma.crashes.ingestion.models.Exception();
            exception.read(jException);
            setException(exception);
        }
        setThreads(JSONUtils.readArray(object, THREADS, ThreadFactory.getInstance()));
    }

    @Override
    public void write(JSONStringer writer) throws JSONException {
        super.write(writer);
        if (getException() != null) {
            writer.key(EXCEPTION).object();
            exception.write(writer);
            writer.endObject();
        }
        JSONUtils.writeArray(writer, THREADS, getThreads());
    }

    @Override
    @SuppressWarnings("SimplifiableIfStatement")
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ManagedErrorLog that = (ManagedErrorLog) o;

        if (exception != null ? !exception.equals(that.exception) : that.exception != null)
            return false;
        return threads != null ? threads.equals(that.threads) : that.threads == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (exception != null ? exception.hashCode() : 0);
        result = 31 * result + (threads != null ? threads.hashCode() : 0);
        return result;
    }
}