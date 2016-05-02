package hu.dpal.app.moblab;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import hu.dpal.app.moblab.ui.UIModule;
import hu.dpal.app.moblab.ui.main.MainPresenter;
import hu.dpal.app.moblab.ui.partners.DetailsPresenter;
import hu.dpal.app.moblab.ui.partners.PartnersPresenter;
import hu.dpal.app.moblab.ui.reservation.ReservationPresenter;

/**
 * Created by dpal on 02/05/16.
 */
@Module
public class TestModule {

    private final UIModule uiModule;

    public TestModule(Context context) {

        this.uiModule = new UIModule(context);
    }

    @Provides
    public Context provideContext() {
        return uiModule.provideContext();
    }

    @Provides
    public MainPresenter provideMainPresenter() {
        return uiModule.provideMainPresenter();
    }

    @Provides
    public PartnersPresenter providePartnersPresenter() {
        return uiModule.providePartnersPresenter();
    }

    @Provides
    public DetailsPresenter provideDetailsPresenter() {
        return uiModule.provideDetailsPresenter();
    }

    @Provides
    public ReservationPresenter provideReservaitonPresenter() {
        return uiModule.provideReservationPresenter();
    }
}
