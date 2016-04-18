package hu.dpal.app.moblab.network.mock;

import hu.dpal.app.moblab.model.Reservation;
import hu.dpal.app.moblab.network.IReservationApi;
import retrofit2.http.Body;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by dpal on 18/04/16.
 */
public class MockReservationApi implements IReservationApi {

    @Override
    public Observable<Reservation> createReservation(@Body Reservation reservation) {
        return null;
    }

    @Override
    public Observable<Reservation> getReservation(@Path("reservationCode") String reservationCode) {
        return null;
    }

    @Override
    public Observable<Reservation> updateReservation(@Path("reservationCode") String reservationCode, @Body Reservation reservation) {
        return null;
    }

    @Override
    public Observable<Reservation> deleteReservation(@Path("reservationCode") String reservationCode) {
        return null;
    }
}
