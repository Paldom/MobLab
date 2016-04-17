package hu.dpal.app.moblab.ui;

/**
 * Created by dpal on 17/04/16.
 */
public abstract class Presenter<T> {
    protected T screen;

    public void attachScreen(T screen) {
        this.screen = screen;
    }

    public void detachScreen() {
        this.screen = null;
    }
}

