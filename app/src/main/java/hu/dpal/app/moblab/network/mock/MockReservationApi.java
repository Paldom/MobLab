package hu.dpal.app.moblab.network.mock;

import java.util.List;

import hu.dpal.app.moblab.model.Reservation;
import hu.dpal.app.moblab.network.IReservationApi;
import retrofit2.http.Body;
import retrofit2.http.Path;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by dpal on 18/04/16.
 */
public class MockReservationApi implements IReservationApi {

    @Override
    public Observable<Reservation> createReservation(@Body Reservation reservation) {
        Long id = reservation.save();
        reservation.setId(id);
        final Reservation res = reservation;
        return Observable.create(new Observable.OnSubscribe<Reservation>() {
            @Override
            public void call(Subscriber<? super Reservation> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                        observer.onNext(res);
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Reservation> getReservation(@Path("reservationId") Long reservationId) {
        final Reservation res = Reservation.findById(Reservation.class, reservationId);
        return Observable.create(new Observable.OnSubscribe<Reservation>() {
            @Override
            public void call(Subscriber<? super Reservation> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                        observer.onNext(res);
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Reservation> updateReservation(@Path("reservationId") Long reservationId, @Body Reservation reservation) {
        reservation.save();
        final Reservation res = reservation;
        return Observable.create(new Observable.OnSubscribe<Reservation>() {
            @Override
            public void call(Subscriber<? super Reservation> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                        observer.onNext(res);
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Long> deleteReservation(@Path("reservationId") Long reservationId) {
        Reservation res = Reservation.findById(Reservation.class, reservationId);
        final Long id = res.getId();
        res.delete();
        return Observable.create(new Observable.OnSubscribe<Long>() {
            @Override
            public void call(Subscriber<? super Long> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                        observer.onNext(id);
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        });
    }
}
