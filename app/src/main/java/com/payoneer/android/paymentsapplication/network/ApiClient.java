package com.payoneer.android.paymentsapplication.network;

import com.payoneer.android.paymentsapplication.model.remote.AvailablePaymentsResponse;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class ApiClient {
    private final ApiService apiService;

    /**
     * @param apiService The parameter Api Service
     */
    @Inject
    public ApiClient(ApiService apiService) {
        this.apiService = apiService;
    }

    public Single<AvailablePaymentsResponse> getAvailablePaymentsResponse() {
        return apiService.getAvailablePayments();
    }
}
