package hu.dpal.app.moblab.unittests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import hu.dpal.app.moblab.BuildConfig;
import hu.dpal.app.moblab.RobolectricDaggerTestRunner;
import hu.dpal.app.moblab.TestData;
import hu.dpal.app.moblab.TestDataSubscriber;
import hu.dpal.app.moblab.interactor.PartnerInteractor;
import hu.dpal.app.moblab.model.Partner;
import rx.Observable;
import rx.Subscriber;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;
import rx.schedulers.TestScheduler;

import static hu.dpal.app.moblab.TestHelper.setTestInjector;
import static org.junit.Assert.*;

/**
 * Created by dpal on 02/05/16.
 */
@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class PartnerUnitTest {

    @Inject
    public PartnerInteractor interactor;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        interactor = new PartnerInteractor();
    }

    @Test
    public void getterTest() throws Exception {
        String title = "Test";
        String desc = "Lorem ipsum dolor sit amet.";
        Partner p = new Partner("http://loremflickr.com/320/240/government,office",
                title,
                desc,
                "1111, Test st. 2",
                "http://test.com/",
                "+1 111 111",
                new String[]{"47.4979", "19.0402"});
        assertEquals(p.getTitle().equals(title), true);
        assertEquals(p.getDesc().equals(desc), true);
    }

    @Test
    public void mockGetTest() throws Exception {
        if (BuildConfig.FLAVOR == "mock") {

            /*TestScheduler testScheduler = Schedulers.test();
            TestSubscriber<List<Partner>> subscriber = new TestSubscriber<>();
            Observable<List<Partner>> observable = interactor.getPartners("",0,1)
                    .observeOn(testScheduler)
                    .subscribeOn(testScheduler);
            observable.subscribe(subscriber);

            List<Partner> p = subscriber.getOnNextEvents().get(0);*/

            TestData<List<Partner>> data = new TestData<>();
            TestDataSubscriber<List<Partner>> subscriber = new TestDataSubscriber<>(data);

            interactor.getPartners("",0,1).subscribe(subscriber);

            assertTrue(data.isCompleted());
            assertNull(data.getError());
            assertEquals(Partner.listAll(Partner.class), data.getResult());
        }
    }

    @Test
    public void mockGetOneTest() throws Exception {
        if (BuildConfig.FLAVOR == "mock") {

            TestData<Partner> data = new TestData<>();
            TestDataSubscriber<Partner> subscriber = new TestDataSubscriber<>(data);

            Partner p = Partner.first(Partner.class);

            interactor.getPartner(p.getId()).subscribe(subscriber);

            assertTrue(data.isCompleted());
            assertNull(data.getError());
            assertEquals(p, data.getResult());
        }
    }


}
