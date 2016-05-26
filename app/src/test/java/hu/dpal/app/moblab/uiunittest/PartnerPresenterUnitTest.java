package hu.dpal.app.moblab.uiunittest;

import org.apache.tools.ant.types.Environment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Observable;

import javax.inject.Inject;
import javax.security.auth.Subject;

import hu.dpal.app.moblab.BuildConfig;
import hu.dpal.app.moblab.RobolectricDaggerTestRunner;
import hu.dpal.app.moblab.model.Partner;
import hu.dpal.app.moblab.ui.partners.IPartnersScreen;
import hu.dpal.app.moblab.ui.partners.PartnersPresenter;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;

import static hu.dpal.app.moblab.TestHelper.setTestInjector;
import static org.junit.Assert.assertTrue;

/**
 * Created by dpal on 26/05/16.
 */
@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class PartnerPresenterUnitTest {

    public class PartnerScreenMock implements IPartnersScreen {

        public BehaviorSubject<Boolean>  isShowPartnersCalled = BehaviorSubject.create(false);
        public BehaviorSubject<Boolean> isShowNetworkErrorCalled = BehaviorSubject.create(false);
        public Boolean isShowPartnerDetailsScreenCalled = false;

        @Override
        public void showPartners(List<Partner> partners) {
            this.isShowPartnersCalled.onNext(true);
            this.isShowPartnersCalled.onCompleted();
        }

        @Override
        public void showNetworkError(String errorMsg) {
            this.isShowNetworkErrorCalled.onNext(true);
            this.isShowNetworkErrorCalled.onCompleted();
        }

        @Override
        public void showPartnerDetailsScreen(Long partnerId) {
            this.isShowPartnerDetailsScreenCalled = true;
        }
    }

    @Inject
    public PartnersPresenter presenter;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        this.presenter = new PartnersPresenter();
    }

    @Test
    public void showPartnersTest() throws Exception {

        final Boolean[] isCalled = new Boolean[1];
        isCalled[0] = false;
        PartnerScreenMock screen = new PartnerScreenMock();
        screen.isShowPartnersCalled.subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean called) {
               isCalled[0] = true;
            }
        });
        this.presenter.attachScreen(screen);

        assertTrue(isCalled[0]);
    }


    @Test
    public void showPartnerDetailsScreenTest() throws Exception {
        PartnerScreenMock screen = new PartnerScreenMock();
        this.presenter.attachScreen(screen);
        this.presenter.showDetails(new Long(0));
        assertTrue(screen.isShowPartnerDetailsScreenCalled);
    }


}