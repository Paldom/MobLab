package hu.dpal.app.moblab.network.mock;

import javax.annotation.Nullable;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.dpal.app.moblab.model.Partner;
import hu.dpal.app.moblab.network.IPartnerApi;
import hu.dpal.app.moblab.network.IReservationApi;
import retrofit2.Retrofit;

/**
 * Created by dpal on 18/04/16.
 */
@Module
public class MockNetworkModule {

    public MockNetworkModule () {
        Partner.deleteAll(Partner.class);
        new Partner("http://loremflickr.com/320/240/government,office",
                "Government Office",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                        "Suspendisse eros enim, efficitur vitae semper ac, lacinia tempor erat.",
                "1111, Random st. 2",
                "http://office.com/",
                "+1 111 111",
                new String[]{"47.4979", "19.0402"}).save();
        new Partner("http://loremflickr.com/320/240/pharmacy",
                "Pharmacy",
                "Vivamus elementum in felis eu ultrices. Duis commodo tincidunt velit, id accumsan nibh dictum nec." +
                        " Quisque dapibus hendrerit orci, nec rhoncus tortor maximus ut. Mauris vehicula tellus quam, eu consequat arcu ultricies nec.",
                "2222, Next st. 3",
                "http://office.com/",
                "+2 222 222",
                new String[]{"47.4812", "19.0672"}).save();
        new Partner("http://loremflickr.com/320/240/store",
                "Store",
                "Nulla quis risus nisi. Morbi iaculis eros interdum, ornare nisl ut, cursus est." +
                        " Maecenas ut dui hendrerit, fermentum nisi vel, imperdiet arcu. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.",
                "3333, Store st. 4",
                "http://office.com/",
                "+3 333 333",
                new String[]{"47.5038", "19.0375"}).save();
    }

    @Provides
    @Singleton
    public IPartnerApi provideArtistsApi() {
        return new MockPartnerApi();
    }

    @Provides
    @Singleton
    public IReservationApi provideReservationApi() {
        return new MockReservationApi();
    }

}
