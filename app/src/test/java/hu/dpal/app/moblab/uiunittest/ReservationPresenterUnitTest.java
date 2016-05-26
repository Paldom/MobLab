package hu.dpal.app.moblab.uiunittest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import javax.inject.Inject;

import hu.dpal.app.moblab.BuildConfig;
import hu.dpal.app.moblab.RobolectricDaggerTestRunner;
import hu.dpal.app.moblab.model.Partner;
import hu.dpal.app.moblab.model.Reservation;
import hu.dpal.app.moblab.ui.reservation.IReservationScreen;
import hu.dpal.app.moblab.ui.reservation.ReservationPresenter;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;

import static hu.dpal.app.moblab.TestHelper.setTestInjector;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by dpal on 26/05/16.
 */
@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ReservationPresenterUnitTest  {

    public class ReservationScreenMock implements IReservationScreen {

        public BehaviorSubject<Boolean> isShowPartnerCalled = BehaviorSubject.create(false);
        public BehaviorSubject<Boolean> isShowReservationCalled = BehaviorSubject.create(false);
        public BehaviorSubject<Boolean> isShowNetworkErrorCalled = BehaviorSubject.create(false);
        public int toogleReservationEditCalledCound = 0;
        public Boolean isFinishScreenCalled = false;

        @Override
        public void showPartner(Partner partner) {
            this.isShowPartnerCalled.onNext(true);
            this.isShowPartnerCalled.onCompleted();
        }

        @Override
        public void showReservation(Reservation reservation) {
            this.isShowReservationCalled.onNext(true);
            this.isShowReservationCalled.onCompleted();
        }

        @Override
        public void toogleReservationEdit() {
            this.toogleReservationEditCalledCound++;
        }

        @Override
        public void finishScreen() {
            this.isFinishScreenCalled = true;
        }


        @Override
        public void showNetworkError(String errorMsg) {
            this.isShowNetworkErrorCalled.onNext(true);
            this.isShowNetworkErrorCalled.onCompleted();
        }

    }

    @Inject
    public ReservationPresenter presenter;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        this.presenter = new ReservationPresenter();
    }

    @Test
    public void showReservationTest() throws Exception {

        final Boolean[] isCalled = new Boolean[1];
        isCalled[0] = false;
        ReservationScreenMock screen = new ReservationScreenMock();
        screen.isShowReservationCalled.subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean called) {
                isCalled[0] = true;
            }
        });
        this.presenter.attachScreen(screen);

        assertTrue(isCalled[0]);
    }

    @Test
    public void showPartnerTest() throws Exception {

        final Boolean[] isCalled = new Boolean[1];
        isCalled[0] = false;
        ReservationScreenMock screen = new ReservationScreenMock();
        screen.isShowPartnerCalled.subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean called) {
                isCalled[0] = true;
            }
        });
        this.presenter.attachScreen(screen);

        assertTrue(isCalled[0]);
    }


    @Test
    public void editorTest() throws Exception {

        ReservationScreenMock screen = new ReservationScreenMock();
        this.presenter.attachScreen(screen);
        this.presenter.startEditing();
        this.presenter.cancelEditing();

        assertEquals(screen.toogleReservationEditCalledCound, 2);
    }


}