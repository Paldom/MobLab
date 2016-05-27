package hu.dpal.app.moblab;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.orm.SugarContext;

import hu.dpal.app.moblab.ui.UIModule;
import io.fabric.sdk.android.Fabric;

/**
 * Created by dpal on 17/04/16.
 */
public class MobLabApplication extends Application {

    private Tracker mTracker;

    /**
     * Gets the default {@link Tracker} for this {@link Application}.
     * @return tracker
     */
    synchronized public Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            mTracker = analytics.newTracker(R.xml.global_tracker);
        }
        return mTracker;
    }

    public static IMobLabApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();

        SugarContext.init(this);
        Fabric.with(this, new Crashlytics());

        if (BuildConfig.TYPE.equals("MOCK")) {
            injector = DaggerIMockNetworkMobLabApplicationComponent.builder().uIModule(
                    new UIModule(this)
            ).build();
        } else {
            injector = DaggerIMobLabApplicationComponent.builder().uIModule(
                    new UIModule(this)
            ).build();
        }


    }

    public void setInjector(IMobLabApplicationComponent appComponent) {
        injector = appComponent;
        injector.inject(this);
    }
}
