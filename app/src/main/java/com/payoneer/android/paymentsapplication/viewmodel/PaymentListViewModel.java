package com.payoneer.android.paymentsapplication.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.payoneer.android.paymentsapplication.model.remote.AvailablePaymentsResponse;
import com.payoneer.android.paymentsapplication.repository.PaymentListRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.HttpException;

@HiltViewModel
public class PaymentListViewModel extends ViewModel {

    private final MutableLiveData<Integer> responseError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> responseLoadingStatus = new MutableLiveData<>();
    private final MutableLiveData<AvailablePaymentsResponse> availablePaymentsResponseList = new MutableLiveData<>();
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final PaymentListRepository paymentListRepository;

    /**
     * Injecting Dependency
     */
    @Inject
    public PaymentListViewModel(PaymentListRepository paymentListRepository) {
        this.paymentListRepository = paymentListRepository;
    }

    /**
     * Function used for Querying the Data From REST API
     */
    public void getListOfAvailablePaymentOptions() {
        compositeDisposable.add(paymentListRepository.getAvailablePaymentsResponse().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<AvailablePaymentsResponse>() {
                    @Override
                    public void onSuccess(@NonNull AvailablePaymentsResponse availablePaymentsResponse) {
                        responseLoadingStatus.setValue(false);
                        availablePaymentsResponseList.setValue(availablePaymentsResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        responseLoadingStatus.setValue(false);
                        responseError.setValue(((HttpException) e).code());
                    }
                }));
    }

    /**
     * Getter Function To Return MutableLiveData<Integer> if any Error comes in response
     */
    public MutableLiveData<Integer> getResponseError() {
        return responseError;
    }

    /**
     * Getter Function To Return MutableLiveData<Boolean> for progressbar loading
     */
    public MutableLiveData<Boolean> getResponseLoadingStatus() {
        return responseLoadingStatus;
    }

    /**
     * Getter Function To Return MutableLiveData<AvailablePaymentsResponse>
     */
    public MutableLiveData<AvailablePaymentsResponse> getAvailablePaymentsResponseList() {
        return availablePaymentsResponseList;
    }
}
