package hu.dpal.app.moblab.ui;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.dpal.app.moblab.ui.main.MainPresenter;
import hu.dpal.app.moblab.ui.partners.PartnersPresenter;

/**
 * Created by dpal on 17/04/16.
 */
@Module
public class UIModule {

    private Context context;

    public UIModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }

    @Provides
    @Singleton
    public PartnersPresenter providePartnersPresenter() {
        return new PartnersPresenter();
    }


}
