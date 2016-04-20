package hu.dpal.app.moblab.network;

import hu.dpal.app.moblab.model.Reservation;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by dpal on 17/04/16.
 */
public interface IReservationApi {

    @POST("reservation")
    Observable<Reservation> createReservation(@Body Reservation reservation);

    @GET("reservation/{reservationCode}")
    Observable<Reservation> getReservation(@Path("reservationId") Long reservationId);

    @PUT("reservation/{reservationCode}")
    Observable<Reservation> updateReservation(@Path("reservationId") Long reservationId,
                                        @Body Reservation reservation);

    @DELETE("reservation/{reservationCode}")
    Observable<Long> deleteReservation(@Path("reservationId") Long reservationId);

}
