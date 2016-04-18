package hu.dpal.app.moblab.ui.partners;

import java.util.List;

import hu.dpal.app.moblab.model.Partner;

/**
 * Created by dpal on 17/04/16.
 */
public interface IPartnersScreen {
    void showPartners(List<Partner> partners);
    void showNetworkError(String errorMsg);
    void showPartnerDetailsScreen(Long partnerId);
}
