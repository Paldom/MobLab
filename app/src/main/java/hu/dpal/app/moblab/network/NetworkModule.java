package hu.dpal.app.moblab.network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.dpal.app.moblab.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dpal on 17/04/16.
 */
@Module
public class NetworkModule {

    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public IPartnerApi provideArtistsApi(Retrofit retrofit) {
        return retrofit.create(IPartnerApi.class);
    }

    @Provides
    @Singleton
    public IReservationApi provideReservationApi(Retrofit retrofit) {
        return retrofit.create(IReservationApi.class);
    }


}
