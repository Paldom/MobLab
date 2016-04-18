package hu.dpal.app.moblab;

import javax.inject.Singleton;

import dagger.Component;
import hu.dpal.app.moblab.network.NetworkModule;
import hu.dpal.app.moblab.ui.UIModule;
import hu.dpal.app.moblab.ui.main.MainActivity;
import hu.dpal.app.moblab.ui.partners.PartnersFragment;
import hu.dpal.app.moblab.ui.partners.PartnersPresenter;

/**
 * Created by dpal on 17/04/16.
 */

@Singleton
@Component(modules = {UIModule.class, NetworkModule.class})
public interface IMobLabApplicationComponent {

    void inject(MainActivity mainActivity);
    void inject(PartnersPresenter partnersPresenter);
    void inject(PartnersFragment partnersFragment);

}
