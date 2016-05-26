package hu.dpal.app.moblab.uiunittest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import javax.inject.Inject;

import hu.dpal.app.moblab.BuildConfig;
import hu.dpal.app.moblab.RobolectricDaggerTestRunner;
import hu.dpal.app.moblab.ui.main.IMainScreen;
import hu.dpal.app.moblab.ui.main.MainPresenter;

import static hu.dpal.app.moblab.TestHelper.setTestInjector;
import static org.junit.Assert.assertTrue;

/**
 * Created by dpal on 26/05/16.
 */
@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainPresenterUnitTest {

    public class MainScreenMock implements IMainScreen {

        public Boolean isShowPartnersScreenCalled = false;
        public Boolean isShowReservationScreenCalled = false;

        @Override
        public void showPartnersScreen() {
            this.isShowPartnersScreenCalled = true;
        }

        @Override
        public void showReservationScreen(Long reservationId) {
            this.isShowReservationScreenCalled = true;
        }
    }

    @Inject
    public MainPresenter presenter;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        this.presenter = new MainPresenter();
    }

    @Test
    public void showPartnerScreenTest() throws Exception {
        MainScreenMock screen = new MainScreenMock();
        this.presenter.attachScreen(screen);
        this.presenter.showPartners();
        assertTrue(screen.isShowPartnersScreenCalled);
    }


    @Test
    public void showReservationScreenTest() throws Exception {
        MainScreenMock screen = new MainScreenMock();
        this.presenter.attachScreen(screen);
        this.presenter.showReservation(new Long(0));
        assertTrue(screen.isShowReservationScreenCalled);
    }


}