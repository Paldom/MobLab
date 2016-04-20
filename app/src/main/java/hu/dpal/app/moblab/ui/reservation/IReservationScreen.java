package hu.dpal.app.moblab.ui.reservation;

import hu.dpal.app.moblab.model.Partner;
import hu.dpal.app.moblab.model.Reservation;

/**
 * Created by dpal on 20/04/16.
 */
public interface IReservationScreen {
    void showPartner(Partner partner);
    void showReservation(Reservation reservation);
    void showNetworkError(String errorMsg);
    void toogleReservationEdit();
    void finishScreen();
}
