package com.example.android.myapplication.ui;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.runner.AndroidJUnitRunner;

import com.payoneer.android.paymentsapplication.R;
import com.payoneer.android.paymentsapplication.ui.AvailablePaymentActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class AvailablePaymentActivityTest extends AndroidJUnitRunner {

    private ActivityScenario<AvailablePaymentActivity> activityScenario;

    @Before
    public void setup() {
        activityScenario = ActivityScenario.launch(AvailablePaymentActivity.class);
        activityScenario.moveToState(Lifecycle.State.RESUMED);
    }

    @Test
    public void isShowPaymentsRecyclerView() {
        onView(withId(R.id.payment_list_recycler_view)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

}