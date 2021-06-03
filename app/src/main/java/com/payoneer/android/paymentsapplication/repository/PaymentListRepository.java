package com.payoneer.android.paymentsapplication.repository;

import com.payoneer.android.paymentsapplication.model.remote.AvailablePaymentsResponse;
import com.payoneer.android.paymentsapplication.network.ApiClient;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class PaymentListRepository {

    private final ApiClient apiClient;

    /**
     * @param apiClient The parameter Api Client
     */

    @Inject
    public PaymentListRepository(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public Single<AvailablePaymentsResponse> getAvailablePaymentsResponse() {
        return apiClient.getAvailablePaymentsResponse();
    }
}
