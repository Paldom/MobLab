package hu.dpal.app.moblab.ui.partners;

import com.google.common.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import hu.dpal.app.moblab.MobLabApplication;
import hu.dpal.app.moblab.model.Partner;
import hu.dpal.app.moblab.network.IPartnerApi;
import hu.dpal.app.moblab.ui.Presenter;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by dpal on 17/04/16.
 */
public class PartnersPresenter extends Presenter<IPartnersScreen> {

    @Inject
    IPartnerApi partnerApi;

    @Override
    public void attachScreen(IPartnersScreen screen) {
        super.attachScreen(screen);
        MobLabApplication.injector.inject(this);
        partnerApi.getPartners("",0,1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Partner>>() {
                    @Override
                    public void call(List<Partner> partners) {
                        showPartners(partners);
                    }
                });
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    private void showPartners(List<Partner> partners) {
        screen.showPartners(partners);
    }

    public void searchPartner(String query) {
        // TODO
    }

    public void showDetails(Long id) {
        screen.showPartnerDetailsScreen(id);
    }


}
