package hu.dpal.app.moblab;

/**
 * Created by dpal on 02/05/16.
 */
public class TestData<T> {
    private Throwable error = null;
    private boolean completed = false;
    private T result;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public T getPartner() {
        return result;
    }

    public Throwable getError() {
        return error;
    }
}
