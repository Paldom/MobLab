package hu.dpal.app.moblab.network.mock;

import java.util.List;

import hu.dpal.app.moblab.model.Partner;
import hu.dpal.app.moblab.network.IPartnerApi;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by dpal on 18/04/16.
 */
public class MockPartnerApi implements IPartnerApi {

    @Override
    public Observable<List<Partner>> getPartners(@Query("query") String query, @Query("skip") int skip, @Query("limit") int limit) {
        final List<Partner> partners = Partner.listAll(Partner.class);
        return Observable.create(new Observable.OnSubscribe<List<Partner>>() {
            @Override
            public void call(Subscriber<? super List<Partner>> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                        observer.onNext(partners);
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Partner> getPartner(@Path("id") Long id) {
        final Partner partner = Partner.findById(Partner.class, id);
        return Observable.create(new Observable.OnSubscribe<Partner>() {
            @Override
            public void call(Subscriber<? super Partner> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                        observer.onNext(partner);
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        });
    }
}
