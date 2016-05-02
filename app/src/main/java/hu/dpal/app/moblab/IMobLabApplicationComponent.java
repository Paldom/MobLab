package hu.dpal.app.moblab;

import javax.inject.Singleton;

import dagger.Component;
import hu.dpal.app.moblab.interactor.InteractorModule;
import hu.dpal.app.moblab.interactor.PartnerInteractor;
import hu.dpal.app.moblab.interactor.ReservationInteractor;
import hu.dpal.app.moblab.network.NetworkModule;
import hu.dpal.app.moblab.ui.UIModule;
import hu.dpal.app.moblab.ui.main.MainActivity;
import hu.dpal.app.moblab.ui.partners.DetailsFragment;
import hu.dpal.app.moblab.ui.partners.DetailsPresenter;
import hu.dpal.app.moblab.ui.partners.PartnersFragment;
import hu.dpal.app.moblab.ui.partners.PartnersPresenter;
import hu.dpal.app.moblab.ui.reservation.ReservationActivity;
import hu.dpal.app.moblab.ui.reservation.ReservationPresenter;

/**
 * Created by dpal on 17/04/16.
 */

@Singleton
@Component(modules = {UIModule.class, NetworkModule.class, InteractorModule.class})
public interface IMobLabApplicationComponent {

    void inject(MainActivity mainActivity);
    void inject(PartnersPresenter partnersPresenter);
    void inject(PartnersFragment partnersFragment);
    void inject(DetailsFragment detailsFragment);
    void inject(DetailsPresenter detailsPresenter);
    void inject(ReservationActivity reservationActivity);
    void inject(ReservationPresenter reservationPresenter);

    void inject(ReservationInteractor reservationInteractor);
    void inject(PartnerInteractor partnerInteractor);

    void inject(MobLabApplication mobLabApplication);

}
