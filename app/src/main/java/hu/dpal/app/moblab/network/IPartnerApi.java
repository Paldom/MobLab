package hu.dpal.app.moblab.network;

import hu.dpal.app.moblab.model.Partner;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by dpal on 17/04/16.
 */
public interface IPartnerApi {

    @GET("partner")
    Observable<Partner> getPartners(@Query("query") String query,
                                          @Query("skip") int skip,
                                          @Query("limit") int limit);

    @GET("partner/{id}")
    Observable<Partner> getPartner(@Path("id") int id);

}
