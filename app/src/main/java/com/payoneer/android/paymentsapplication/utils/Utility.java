package com.payoneer.android.paymentsapplication.utils;

import android.content.Context;
import android.net.ConnectivityManager;

public class Utility {
    public static boolean isInternetConnected(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
