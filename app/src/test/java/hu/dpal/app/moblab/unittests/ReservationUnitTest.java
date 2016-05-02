package hu.dpal.app.moblab.unittests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import hu.dpal.app.moblab.BuildConfig;
import hu.dpal.app.moblab.RobolectricDaggerTestRunner;
import hu.dpal.app.moblab.TestData;
import hu.dpal.app.moblab.TestDataSubscriber;
import hu.dpal.app.moblab.interactor.ReservationInteractor;
import hu.dpal.app.moblab.model.Partner;
import hu.dpal.app.moblab.model.Reservation;

import static hu.dpal.app.moblab.TestHelper.setTestInjector;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by dpal on 02/05/16.
 */
@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ReservationUnitTest {

    private final String DUMMY_RESERVATION_CODE = "DUMMY_RESERVATION_CODE";
    private final String TEST_RESERVATION_CODE = "TEST_RESERVATION_CODE";
    private final String MODIFIED_RESERVATION_CODE = "MODIFIED_RESERVATION_CODE";

    private TestData<Reservation> data;
    TestDataSubscriber<Reservation> subscriber;

    @Inject
    public ReservationInteractor interactor;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        interactor = new ReservationInteractor();
        data = new TestData<>();
        subscriber = new TestDataSubscriber<>(data);

        new Reservation(Partner.first(Partner.class).getId(),
                DUMMY_RESERVATION_CODE,
                (new Date()).toString(),
                "Dummy category").save();
    }

    @After
    public void after() {
        data = null;
        subscriber = null;
    }

    @Test
    public void mockGetterTest() throws Exception {
        Long partnerId = 0L;
        String resCode = TEST_RESERVATION_CODE;
        String resDate = (new Date()).toString();
        String category = "Test category";
        Reservation r = new Reservation(partnerId, resCode, resDate, category);

        assertEquals(partnerId, r.getPartnerId());
        assertEquals(resCode, r.getReservationCode());
        assertEquals(resDate, r.getReservationDate());
        assertEquals(category, r.getCategory());
    }

    @Test
    public void mockAddTest() throws Exception {
        if (BuildConfig.FLAVOR == "mock") {
            Long partnerId = Partner.last(Partner.class).getId();
            Reservation r = new Reservation(partnerId,
                    TEST_RESERVATION_CODE,
                    (new Date()).toString(),
                    "Test category");

            interactor.createReservation(r).subscribe(subscriber);

            assertTrue(data.isCompleted());
            assertNull(data.getError());
            assertEquals(r.getReservationCode(), data.getResult().getReservationCode());
        }
    }

    @Test
    public void mockGetTest() throws Exception {
        if (BuildConfig.FLAVOR == "mock") {

            Reservation r = Reservation.first(Reservation.class);

            interactor.getReservation(r.getId()).subscribe(subscriber);

            assertTrue(data.isCompleted());
            assertNull(data.getError());
            assertEquals(r, data.getResult());
        }
    }

    @Test
    public void mockModifyTest() throws Exception {
        if (BuildConfig.FLAVOR == "mock") {

            Reservation r = Reservation.first(Reservation.class);
            r.setReservationCode(MODIFIED_RESERVATION_CODE);

            interactor.updateReservation(r.getId(), r).subscribe(subscriber);

            assertTrue(data.isCompleted());
            assertNull(data.getError());
            assertEquals(r, data.getResult());
        }
    }

    @Test
    public void mockDeleteTest() throws Exception {
        if (BuildConfig.FLAVOR == "mock") {

            Reservation r = Reservation.first(Reservation.class);
            Long deletedId = r.getId();

            TestData<Long> deleteData = new TestData<>();
            TestDataSubscriber<Long> deleteSubscriber = new TestDataSubscriber<>(deleteData);

            interactor.deleteReservation(deletedId).subscribe(deleteSubscriber);

            assertTrue(deleteData.isCompleted());
            assertNull(deleteData.getError());
            assertEquals(deletedId, deleteData.getResult());
        }
    }

}
