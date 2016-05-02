package hu.dpal.app.moblab;

import org.robolectric.RuntimeEnvironment;

/**
 * Created by dpal on 02/05/16.
 */
public class TestHelper {

    public static void setTestInjector() {
        MobLabApplication application = (MobLabApplication) RuntimeEnvironment.application;
        IMobLabApplicationComponent injector = DaggerTestComponent.builder().testModule(
                new TestModule(application.getApplicationContext())
        ).build();
        application.setInjector(injector);
    }

}
