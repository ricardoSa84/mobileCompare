package com.example.rsa.androidnativobench.activitys.performanceRelated;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rsa.androidnativobench.R;
import com.example.rsa.androidnativobench.general.AppConfig;
import com.example.rsa.androidnativobench.networking.ApiCallInterface;
import com.example.rsa.androidnativobench.networking.ServiceCallManager;
import com.example.rsa.androidnativobench.networking.requestBodys.LogInRequestBody;
import com.example.rsa.androidnativobench.networking.responseBodys.ListBoxsResponse;
import com.example.rsa.androidnativobench.networking.responseBodys.ListPatientsResponse;
import com.example.rsa.androidnativobench.networking.responseBodys.LogInResponse;
import com.example.rsa.androidnativobench.networking.responseBodys.Vitabox;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Networking extends AppCompatActivity {

    private Button startButton;
    private EditText email;
    private EditText password;
    private EditText numberOfRequests;
    private int numberOfPatientsRequests = 0;
    private int requestCounter = 0;
    private int requestsToMake = 0;
    private TextView resultTitle;
    private Calendar startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_networking);

        email = findViewById(R.id.emailText);
        email.setText("jose@a.aa");
        password = findViewById(R.id.passwordText);
        password.setText("123qweASD");
        numberOfRequests = findViewById(R.id.numberOfTimes);

        resultTitle = findViewById(R.id.resultTitle);

        startButton = findViewById(R.id.startProcessBtn);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                resultTitle.setText("Please wait work in progress...");
                requestCounter = 0;
                numberOfPatientsRequests = 0;
                makeRequests();
            }
        });
    }

    private void makeRequests() {
        requestCounter = 0;
        String number = numberOfRequests.getText().toString();
        requestsToMake = Integer.parseInt(number);
        startTime = Calendar.getInstance();
        callLogin();
    }

    //step1 login to get token
    private void callLogin() {
        ApiCallInterface apiService = ServiceCallManager.getServiceCallManager(AppConfig.getServerAddress(),
                null, getApplicationContext()).create(ApiCallInterface.class);
        Call<LogInResponse> callLogin = apiService.login(new LogInRequestBody(email.getText().toString(), password.getText().toString()));

        callLogin.clone().enqueue(getLoginCallback);
    }

    private Callback<LogInResponse> getLoginCallback = new Callback<LogInResponse>() {
        @Override
        public void onResponse(final Call<LogInResponse> call, Response<LogInResponse> response) {
            if (response.body() != null) {
                AppConfig.setToken(response.body().getToken());
                for (int i = 1; i <= requestsToMake; i++) {
                    callListBoxs();
                }
            }
        }

        @Override
        public void onFailure(Call<LogInResponse> call, Throwable t) {
        }
    };

    //step2 get all boxes
    private void callListBoxs() {
        ApiCallInterface apiService = ServiceCallManager.getServiceCallManager(AppConfig.getServerAddress(),
                null, getApplicationContext()).create(ApiCallInterface.class);
        Call<ListBoxsResponse> callRegister = apiService.listBoxs();

        callRegister.clone().enqueue(getListBoxsCallback);
    }

    private Callback<ListBoxsResponse> getListBoxsCallback = new Callback<ListBoxsResponse>() {
        @Override
        public void onResponse(final Call<ListBoxsResponse> call, Response<ListBoxsResponse> response) {
            if (response.body() != null) {
                List<Vitabox> boxes = response.body().getVitaboxes();
                numberOfPatientsRequests += boxes.size();
                for (int i = 0; i < boxes.size(); i++) {
                    callListPatiens(boxes.get(i).getId());
                }
            }
        }

        @Override
        public void onFailure(Call<ListBoxsResponse> call, Throwable t) {
        }
    };

    //step3 get patients
    private void callListPatiens(String id) {
        ApiCallInterface apiService = ServiceCallManager.getServiceCallManager(AppConfig.getServerAddress(),
                null, getApplicationContext()).create(ApiCallInterface.class);
        Call<ListPatientsResponse> callListPatients = apiService.listPatients(id);

        callListPatients.clone().enqueue(getListPatientsCallback);
    }

    private Callback<ListPatientsResponse> getListPatientsCallback = new Callback<ListPatientsResponse>() {
        @Override
        public void onResponse(final Call<ListPatientsResponse> call, Response<ListPatientsResponse> response) {
            requestCounter++;
            if (requestCounter == numberOfPatientsRequests) {
                resultTitle.setText("Requests finished in: " + (Calendar.getInstance().getTimeInMillis() - startTime.getTimeInMillis()));
            }
        }

        @Override
        public void onFailure(Call<ListPatientsResponse> call, Throwable t) {
        }
    };


}