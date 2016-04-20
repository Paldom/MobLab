package hu.dpal.app.moblab.ui;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.dpal.app.moblab.model.Reservation;
import hu.dpal.app.moblab.ui.main.MainPresenter;
import hu.dpal.app.moblab.ui.partners.DetailsPresenter;
import hu.dpal.app.moblab.ui.partners.PartnersPresenter;
import hu.dpal.app.moblab.ui.reservation.ReservationPresenter;

/**
 * Created by dpal on 17/04/16.
 */
@Module
public class UIModule {

    private Context context;

    public UIModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }

    @Provides
    @Singleton
    public PartnersPresenter providePartnersPresenter() {
        return new PartnersPresenter();
    }

    @Provides
    @Singleton
    public DetailsPresenter provideDetailsPresenter() {
        return new DetailsPresenter();
    }

    @Provides
    @Singleton
    public ReservationPresenter provideReservationPresenter() {
        return new ReservationPresenter();
    }


}
