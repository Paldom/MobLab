package hu.dpal.app.moblab.ui.reservation;

import android.text.Editable;

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
 * Created by dpal on 20/04/16.
 */
public class ReservationPresenter extends Presenter<IReservationScreen> {

    private Reservation reservation;

    @Inject
    IPartnerApi partnerApi;

    @Inject
    IReservationApi reservationApi;

    @Override
    public void attachScreen(IReservationScreen screen) {
        super.attachScreen(screen);
        MobLabApplication.injector.inject(this);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    // private

    private void showReservationOnScreeen(Reservation reservation) {
        this.reservation = reservation;
        screen.showReservation(reservation);
    }

    private void showPartnerOnScreen(Partner partner) {
        screen.showPartner(partner);
    }

    private void showErrorMsgOnScreen(String e) {
        screen.showNetworkError(e);
    }

    private void finishScreen() {
        screen.finishScreen();
    }

    // public

    public void loadData(Long reservationId) {
        reservationApi.getReservation(reservationId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Reservation>() {
                    @Override
                    public void onNext(final Reservation reservation) {
                        partnerApi.getPartner(reservation.getPartnerId())
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Subscriber<Partner>() {
                                    @Override
                                    public void onNext(Partner partner) {
                                        showReservationOnScreeen(reservation);
                                        showPartnerOnScreen(partner);
                                    }

                                    @Override
                                    public void onCompleted() {
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        e.printStackTrace();
                                        showErrorMsgOnScreen(e.getMessage());
                                    }
                                });
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

    public void deleteReservation(Long reservationId) {
        reservationApi.deleteReservation(reservationId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onNext(Long id) {
                        finishScreen();
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

    public void modifyReservation(Long reservationId, Editable date, Editable category) {
        reservation.setReservationDate(date.toString());
        reservation.setCategory(category.toString());
        reservationApi.updateReservation(reservationId,reservation)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Reservation>() {
                    @Override
                    public void onNext(Reservation reservation) {
                        showReservationOnScreeen(reservation);
                        screen.toogleReservationEdit();
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


    public void cancelEditing() {
        screen.toogleReservationEdit();
    }

    public void startEditing() {
        screen.toogleReservationEdit();
    }
}
