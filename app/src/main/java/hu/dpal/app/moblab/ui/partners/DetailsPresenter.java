package hu.dpal.app.moblab.ui.partners;

import javax.inject.Inject;

import hu.dpal.app.moblab.MobLabApplication;
import hu.dpal.app.moblab.model.Partner;
import hu.dpal.app.moblab.model.Reservation;
import hu.dpal.app.moblab.network.IPartnerApi;
import hu.dpal.app.moblab.network.IReservationApi;
import hu.dpal.app.moblab.ui.Presenter;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dpal on 18/04/16.
 */
public class DetailsPresenter extends Presenter<IDetailsScreen> {

    @Inject
    IPartnerApi partnerApi;

    @Inject
    IReservationApi reservationApi;


    @Override
    public void attachScreen(IDetailsScreen screen) {
        super.attachScreen(screen);
        MobLabApplication.injector.inject(this);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    // private

    private void showPartnerOnScreen(Partner partner) {
        screen.showPartner(partner);
    }

    private void showErrorMsgOnScreen(String e) {
        screen.showNetworkError(e);
    }

    private void showReservation(Long reservationId) {
        screen.showReservationScreen(reservationId);
    }

    // public

    public void loadPartner (Long partnerId) {
        partnerApi.getPartner(partnerId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Partner>() {
                    @Override
                    public void onNext(Partner partner) {
                        showPartnerOnScreen(partner);
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        showErrorMsgOnScreen(e.getMessage());
                    }
                });
    }

    public void beginReservation() {
        screen.showReservationDialog();
    }

    public void makeReservation(Reservation reservation) {
        reservationApi.createReservation(reservation)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Reservation>() {
                    @Override
                    public void onNext(Reservation reservation) {
                        showReservation(reservation.getId());
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        showErrorMsgOnScreen(e.getMessage());
                    }
                });
    }


}
