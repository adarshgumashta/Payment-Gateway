package com.payoneer.android.paymentsapplication.network;

import com.payoneer.android.paymentsapplication.BuildConfig;
import com.payoneer.android.paymentsapplication.model.remote.AvailablePaymentsResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface ApiService {

    @GET(BuildConfig.API_URL)
    Single<AvailablePaymentsResponse> getAvailablePayments();
}
