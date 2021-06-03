package com.payoneer.android.paymentsapplication;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class PaymentApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
