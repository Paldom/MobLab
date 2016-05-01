package hu.dpal.app.moblab.interactor;

import javax.inject.Inject;

import hu.dpal.app.moblab.MobLabApplication;
import hu.dpal.app.moblab.model.Reservation;
import hu.dpal.app.moblab.network.IReservationApi;
import retrofit2.http.Body;
import rx.Observable;

/**
 * Created by dpal on 01/05/16.
 */
public class ReservationInteractor {

    @Inject
    IReservationApi reservationApi;

    public ReservationInteractor() {
        MobLabApplication.injector.inject(this);
    }

    public Observable<Reservation> createReservation(Reservation reservation) {
        return reservationApi.createReservation(reservation);
    }

    public Observable<Reservation> getReservation(Long reservationId) {
        return reservationApi.getReservation(reservationId);
    }

    public Observable<Reservation> updateReservation(Long reservationId,Reservation reservation) {
        return reservationApi.updateReservation(reservationId, reservation);
    }

    public Observable<Long> deleteReservation(Long reservationId) {
        return reservationApi.deleteReservation(reservationId);
    }

}
