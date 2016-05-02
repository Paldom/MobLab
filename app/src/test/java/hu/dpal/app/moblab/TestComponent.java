package hu.dpal.app.moblab;

import javax.inject.Singleton;

import dagger.Component;
import hu.dpal.app.moblab.interactor.InteractorModule;
import hu.dpal.app.moblab.network.mock.MockNetworkModule;

/**
 * Created by dpal on 02/05/16.
 */
@Singleton
@Component(modules = {MockNetworkModule.class, TestModule.class, InteractorModule.class})
public interface TestComponent extends IMobLabApplicationComponent {
}