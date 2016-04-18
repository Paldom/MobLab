package hu.dpal.app.moblab;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.orm.SugarContext;

import hu.dpal.app.moblab.ui.UIModule;
import io.fabric.sdk.android.Fabric;

/**
 * Created by dpal on 17/04/16.
 */
public class MobLabApplication extends Application {

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


}
