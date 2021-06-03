package com.payoneer.android.paymentsapplication.di;

import com.payoneer.android.paymentsapplication.BuildConfig;
import com.payoneer.android.paymentsapplication.network.ApiClient;
import com.payoneer.android.paymentsapplication.network.ApiService;
import com.payoneer.android.paymentsapplication.repository.PaymentListRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
class APIModule {

    @Provides
    @Singleton
    public static ApiService getAPIService() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build().create(ApiService.class);
    }

    @Provides
    @Singleton
    public static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        okHttpClient.addInterceptor(httpLoggingInterceptor);
        return okHttpClient.build();
    }

    @Provides
    @Singleton
    public static ApiClient getApiClient(ApiService apiService) {
        return new ApiClient(apiService);
    }

    @Provides
    @Singleton
    public static PaymentListRepository getRepository(ApiClient apiClient) {
        return new PaymentListRepository(apiClient);
    }
}
