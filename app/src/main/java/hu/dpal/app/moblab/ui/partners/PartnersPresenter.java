package hu.dpal.app.moblab.ui.partners;

import com.google.common.eventbus.EventBus;

import javax.inject.Inject;

import hu.dpal.app.moblab.MobLabApplication;
import hu.dpal.app.moblab.network.IPartnerApi;
import hu.dpal.app.moblab.ui.Presenter;

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
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }



}
