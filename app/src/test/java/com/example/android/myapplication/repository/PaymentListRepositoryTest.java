package com.example.android.myapplication.repository;

import com.payoneer.android.paymentsapplication.model.remote.ApplicableNetwork;
import com.payoneer.android.paymentsapplication.model.remote.AvailablePaymentsResponse;
import com.payoneer.android.paymentsapplication.network.ApiClient;
import com.payoneer.android.paymentsapplication.network.ApiService;
import com.payoneer.android.paymentsapplication.repository.PaymentListRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.observers.TestObserver;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.google.common.truth.Truth.assertThat;

public class PaymentListRepositoryTest {
    private ApiService apiService;
    private ApiClient apiClient;
    private PaymentListRepository paymentListRepository;
    private TestObserver<AvailablePaymentsResponse> testObserver = new TestObserver<>();
    private MockWebServer mockWebServer = new MockWebServer();
    private OkHttpClient okHttpClient;
    private String directoryName = "src/test/java/com/example/android/myapplication/repository/";

    @Before
    public void setUp() throws Exception {
        okHttpClient = new OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS).build();
        apiService = new Retrofit.Builder().baseUrl(mockWebServer.url("/")).client(okHttpClient).addCallAdapterFactory(RxJava3CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build().create(ApiService.class);
        apiClient = new ApiClient(apiService);
        paymentListRepository = new PaymentListRepository(apiClient);
    }

    @After
    public void tearDown() throws Exception {
        mockWebServer.shutdown();
        testObserver.dispose();
    }

    private String readTestJson(final String filename) throws IOException {
        FileReader fr = new FileReader(directoryName + filename);
        StringBuilder sb = new StringBuilder();
        int i;
        while ((i = fr.read()) != -1) {
            sb.append((char) i);
        }
        return sb.toString();
    }

    @Test
    public void shouldReturnPaymentOptionList() throws IOException {
        MockResponse mockResponse = new MockResponse().setResponseCode(200).setBody(readTestJson("listresult.json"));
        mockWebServer.enqueue(mockResponse);
        paymentListRepository.getAvailablePaymentsResponse().subscribe(testObserver);
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
        ApplicableNetwork applicableNetwork = testObserver.values().get(0).getNetworks().getApplicable().get(0);
        assertThat(testObserver.values().get(0).getNetworks().getApplicable()).hasSize(17);
        assertThat(applicableNetwork.getLabel()).isEqualTo("American Express");
        assertThat(applicableNetwork.getLinks().get("logo").toString()).isEqualTo("https://raw.githubusercontent.com/optile/checkout-android/develop/checkout/src/main/assets/networklogos/amex.png");
    }
}