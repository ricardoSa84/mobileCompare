package com.example.ricardo.androidnativeminiapp.activitys;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ricardo.androidnativeminiapp.R;
import com.example.ricardo.androidnativeminiapp.general.AppConfig;
import com.example.ricardo.androidnativeminiapp.networking.ApiCallInterface;
import com.example.ricardo.androidnativeminiapp.networking.ServiceCallManager;
import com.example.ricardo.androidnativeminiapp.networking.requestBodys.BoxRegistrationRequest;
import com.example.ricardo.androidnativeminiapp.networking.responseBodys.RegisterResponse;
import com.example.ricardo.androidnativeminiapp.networking.responseBodys.boxRegistration.BoxRegistrationResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoxRegistration extends AppCompatActivity {

    private EditText id,adress,password,latitude,longitude;
    private Button registerBtn;
    private CoordinatorLayout baseLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.box_registration_activity);
        getSupportActionBar().hide();
        setUiComponents();
        registerListeners();
    }

    private void setUiComponents() {
        id = findViewById(R.id.boxId);
        adress = findViewById(R.id.boxAdress);
        password = findViewById(R.id.boxPassword);
        registerBtn = findViewById(R.id.registerBtn);
        latitude = findViewById(R.id.boxLatitude);
        longitude = findViewById(R.id.boxLongitude);
        baseLayout = findViewById(R.id.baseLayout);
    }

    private void registerListeners() {
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callRegisterBox();
            }
        });
    }

    private void callRegisterBox() {
        ApiCallInterface apiService = ServiceCallManager.getServiceCallManager(AppConfig.getServerAddress(),
                null, getApplicationContext()).create(ApiCallInterface.class);
        Call<BoxRegistrationResponse> callLogin = apiService.registerBox(id.getText().toString(), new BoxRegistrationRequest(
                latitude.getText().toString(),
                longitude.getText().toString(),
                adress.getText().toString(),
                AppConfig.getEmail(),
                password.getText().toString()));

        callLogin.clone().enqueue(getRegisterBoxCallback);
    }

    private Callback<BoxRegistrationResponse> getRegisterBoxCallback = new Callback<BoxRegistrationResponse>() {
        @Override
        public void onResponse(final Call<BoxRegistrationResponse> call, Response<BoxRegistrationResponse> response) {
            if(response.body()!=null){

            }else{
                try {
                    showRetrySnack(response.errorBody().string(), true);
                } catch (Exception e) {
                    showRetrySnack(e.getMessage(), true);
                }
            }
        }

        @Override
        public void onFailure(Call<BoxRegistrationResponse> call, Throwable t) {
            showRetrySnack(t.getMessage(),true);
        }
    };

    private void showRetrySnack(String text, final boolean login){
        Snackbar snackbar = Snackbar.make(baseLayout,text,Snackbar.LENGTH_LONG);
        snackbar.setAction("RETRY", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callRegisterBox();
            }
        });
        snackbar.show();
    }
}
