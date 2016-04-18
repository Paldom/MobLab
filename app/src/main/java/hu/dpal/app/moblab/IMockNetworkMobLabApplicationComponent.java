package hu.dpal.app.moblab;

import javax.inject.Singleton;

import dagger.Component;
import hu.dpal.app.moblab.network.mock.MockNetworkModule;
import hu.dpal.app.moblab.ui.UIModule;

/**
 * Created by dpal on 18/04/16.
 */
@Singleton
@Component(modules = {UIModule.class, MockNetworkModule.class})
public interface IMockNetworkMobLabApplicationComponent extends IMobLabApplicationComponent {
}
