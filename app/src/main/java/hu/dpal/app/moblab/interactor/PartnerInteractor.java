package hu.dpal.app.moblab.interactor;

import java.util.List;

import javax.inject.Inject;

import hu.dpal.app.moblab.MobLabApplication;
import hu.dpal.app.moblab.model.Partner;
import hu.dpal.app.moblab.network.IPartnerApi;
import rx.Observable;

/**
 * Created by dpal on 01/05/16.
 */
public class PartnerInteractor {

    @Inject
    IPartnerApi partnerApi;

    public PartnerInteractor() {
        MobLabApplication.injector.inject(this);
    }

    public Observable<List<Partner>> getPartners(String query, int skip, int limit) {
        return partnerApi.getPartners(query, skip, limit);
    }

    public Observable<Partner> getPartner(Long id) {
        return partnerApi.getPartner(id);
    }
}