package hu.dpal.app.moblab.ui.main;

import hu.dpal.app.moblab.ui.Presenter;

/**
 * Created by dpal on 17/04/16.
 */
public class MainPresenter extends Presenter<IMainScreen> {

    @Override
    public void attachScreen(IMainScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    public void showPartners() {
        screen.showPartnersScreen();
    }

    public void showReservation(String reservationCode) {
        screen.showReservationScreen(reservationCode);
    }

}
