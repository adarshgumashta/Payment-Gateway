package com.payoneer.android.paymentsapplication.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.payoneer.android.paymentsapplication.R;
import com.payoneer.android.paymentsapplication.adapter.PaymentListAdapter;
import com.payoneer.android.paymentsapplication.databinding.ActivityMainBinding;
import com.payoneer.android.paymentsapplication.model.remote.ApplicableNetwork;
import com.payoneer.android.paymentsapplication.utils.Utility;
import com.payoneer.android.paymentsapplication.viewmodel.PaymentListViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * Activity Displaying the list of available payment options in the App.
 */

@AndroidEntryPoint
public class AvailablePaymentActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;
    private PaymentListViewModel paymentListViewModel;
    private PaymentListAdapter paymentListAdapter;
    private List<ApplicableNetwork> availablePaymentsResponseArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        paymentListViewModel = new ViewModelProvider(this).get(PaymentListViewModel.class);
        setupActionBar();
        setupAdapter();
        initiateAvailablePaymentsRequest();
    }

    private void setupActionBar() {
        Spannable text = new SpannableString(getSupportActionBar().getTitle());
        text.setSpan(new ForegroundColorSpan(Color.BLACK), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        getSupportActionBar().setTitle(text);
    }

    /**
     * Method defined for retreving list of available payments from API
     */

    private void initiateAvailablePaymentsRequest() {
        if (Utility.isInternetConnected(this)) {
            paymentListViewModel.getListOfAvailablePaymentOptions();
            getAvailablePayments();
            checkErrorResponse();
            checkProgressBar();
        } else {
            activityMainBinding.paymentListRecyclerView.setVisibility(View.GONE);
            activityMainBinding.paymentListLoadingProgressBar.setVisibility(View.GONE);
            activityMainBinding.paymentListInternetError.setText(getString(R.string.internet_not_connected));
        }
    }

    /**
     * Method defined for checking Error response with well known error codes if any
     */

    private void checkErrorResponse() {
        paymentListViewModel.getResponseError().observe(this, integer -> {
            String errorMessage;
            switch (integer) {
                case 403:
                    errorMessage = getString(R.string.error_403);
                case 500:
                    errorMessage = getString(R.string.error_500);
                default:
                    errorMessage = getString(R.string.error_default);
            }
            showErrorDialog(errorMessage);
        });
    }

    /**
     * Method defined for displaying Error Dialog based on error response
     */

    private void showErrorDialog(String errorMessage) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setMessage(errorMessage);
        alertDialog.show();
        activityMainBinding.paymentListInternetError.setText(errorMessage);
    }

    /**
     * Method to toggle the progressbar Visibility to gone or Visible based on the aBoolean value
     */

    private void checkProgressBar() {
        paymentListViewModel.getResponseLoadingStatus().observe(this, aBoolean -> {
            if (aBoolean) {
                activityMainBinding.paymentListLoadingProgressBar.setVisibility(View.VISIBLE);
            } else {
                activityMainBinding.paymentListLoadingProgressBar.setVisibility(View.GONE);
            }
        });
    }

    /**
     * Method to Observe the AvailablePaymentsResponse and Notify The Adapter
     */
    private void getAvailablePayments() {
        paymentListViewModel.getAvailablePaymentsResponseList().observe(this, availablePaymentsResponse -> {
            availablePaymentsResponseArrayList.clear();
            availablePaymentsResponseArrayList.addAll(availablePaymentsResponse.getNetworks().getApplicable());
            paymentListAdapter.notifyDataSetChanged();
        });
    }

    /**
     * Method Defined for Intializing the Adapter and setting it to RecyclerView
     */
    private void setupAdapter() {
        availablePaymentsResponseArrayList = new ArrayList<>();
        paymentListAdapter = new PaymentListAdapter(availablePaymentsResponseArrayList, this);
        activityMainBinding.paymentListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        activityMainBinding.paymentListRecyclerView.setAdapter(paymentListAdapter);
    }

}