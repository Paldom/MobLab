package hu.dpal.app.moblab;

import rx.Subscriber;

/**
 * Created by dpal on 02/05/16.
 */
public class TestDataSubscriber<T> extends Subscriber<T> {

    private TestData<T> data;

    public TestDataSubscriber(TestData<T> data) {
        this.data = data;
    }

    @Override
    public void onCompleted() {
        data.setCompleted(true);
    }

    @Override
    public void onError(Throwable e) {
        data.setError(e);
    }

    @Override
    public void onNext(T t) {
        data.setResult(t);
    }
}
