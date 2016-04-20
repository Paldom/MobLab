package hu.dpal.app.moblab.ui.partners;

import hu.dpal.app.moblab.model.Partner;

/**
 * Created by dpal on 18/04/16.
 */
public interface IDetailsScreen {
    void showPartner(Partner partners);
    void showNetworkError(String errorMsg);
    void showReservationScreen(Long reservationId);
    void showReservationDialog();
}
