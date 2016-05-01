package hu.dpal.app.moblab.interactor;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dpal on 01/05/16.
 */
@Module
public class InteractorModule {

    @Provides
    public PartnerInteractor providePartnerInteractor() {
        return new PartnerInteractor();
    }

    @Provides
    public ReservationInteractor provideReservationInteractor() {
        return new ReservationInteractor();
    }


}
