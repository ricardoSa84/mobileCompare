package com.example.rsa.androidnativobench.activitys.routineTasks;

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
import com.example.rsa.androidnativobench.networking.requestBodys.RegisterRequestBody;
import com.example.rsa.androidnativobench.networking.responseBodys.LogInResponse;
import com.example.rsa.androidnativobench.networking.responseBodys.RegisterResponse;

import org.w3c.dom.Text;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private Button logInButton;
    private Button registerButton;
    private EditText email;
    private EditText password;
    private EditText numberOfRequests;
    private int requestCounter = 0;
    private int requestsToMake = 0;
    private TextView resultTitle;
    private Calendar startTime;
    private int attempt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.emailText);
        email.setText("jose@a.aa");
        password = findViewById(R.id.passwordText);
        password.setText("123qweASD");
        numberOfRequests = findViewById(R.id.numberOfTimes);

        resultTitle = findViewById(R.id.resultTitle);

        logInButton = findViewById(R.id.loginBtn);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeRequests(false);
            }
        });

        registerButton = findViewById(R.id.registerBtn);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeRequests(true);
                attempt++;
            }
        });
    }

    private void makeRequests(boolean register) {
        requestCounter = 0;
        String number = numberOfRequests.getText().toString();
        requestsToMake = Integer.parseInt(number);
        startTime = Calendar.getInstance();
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(numberOfRequests.getWindowToken(), 0);
        resultTitle.setText("Please wait work in progress...");
        requestCounter = 0;
        for (int i = 1; i <= requestsToMake; i++) {
            if (register)
                callRegister(i);
            else
                callLogin();
        }
    }

    private void callRegister(int i) {
        ApiCallInterface apiService = ServiceCallManager.getServiceCallManager(AppConfig.getServerAddress(),
                null, getApplicationContext()).create(ApiCallInterface.class);
        Call<RegisterResponse> callRegister = apiService.register(new RegisterRequestBody("teste" + String.valueOf(attempt) +
                String.valueOf(i) + email.getText().toString(), "Ricardo" + String.valueOf(i), password.getText().toString()));

        callRegister.clone().enqueue(getRegisterCallback);
    }

    private Callback<RegisterResponse> getRegisterCallback = new Callback<RegisterResponse>() {
        @Override
        public void onResponse(final Call<RegisterResponse> call, Response<RegisterResponse> response) {
            requestCounter++;
            if (requestCounter == requestsToMake) {
                resultTitle.setText("Requests finished in: " + (Calendar.getInstance().getTimeInMillis() - startTime.getTimeInMillis()));
            }
        }

        @Override
        public void onFailure(Call<RegisterResponse> call, Throwable t) {
        }
    };

    private void callLogin() {
        ApiCallInterface apiService = ServiceCallManager.getServiceCallManager(AppConfig.getServerAddress(),
                null, getApplicationContext()).create(ApiCallInterface.class);
        Call<LogInResponse> callLogin = apiService.login(new LogInRequestBody(email.getText().toString(), password.getText().toString()));

        callLogin.clone().enqueue(getLoginCallback);
    }

    private Callback<LogInResponse> getLoginCallback = new Callback<LogInResponse>() {
        @Override
        public void onResponse(final Call<LogInResponse> call, Response<LogInResponse> response) {
            requestCounter++;
            if (requestCounter == requestsToMake) {
                resultTitle.setText("Requests finished in: " + (Calendar.getInstance().getTimeInMillis() - startTime.getTimeInMillis()));
            }
        }

        @Override
        public void onFailure(Call<LogInResponse> call, Throwable t) {
        }
    };

}
