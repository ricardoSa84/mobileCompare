package com.example.ricardo.androidnativeminiapp.activitys;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ricardo.androidnativeminiapp.R;
import com.example.ricardo.androidnativeminiapp.general.AppConfig;
import com.example.ricardo.androidnativeminiapp.networking.ApiCallInterface;
import com.example.ricardo.androidnativeminiapp.networking.ServiceCallManager;
import com.example.ricardo.androidnativeminiapp.networking.requestBodys.LogInRequestBody;
import com.example.ricardo.androidnativeminiapp.networking.requestBodys.RegisterRequestBody;
import com.example.ricardo.androidnativeminiapp.networking.responseBodys.LogInResponse;
import com.example.ricardo.androidnativeminiapp.networking.responseBodys.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private EditText email, password,name;
    private Button loginBtn,registerBtn;
    private TextInputLayout nameContainer;
    private CoordinatorLayout baseLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.login_activity);
        setUiComponents();
        setListeners();
    }

    private void setUiComponents() {
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        //TODO: Remove this
        email.setText("jose@a.aa");
        password.setText("123qweASD");

        name = findViewById(R.id.name);
        nameContainer = findViewById(R.id.nameContainer);
        loginBtn = findViewById(R.id.loginBtn);
        registerBtn = findViewById(R.id.registerBtn);
        baseLayout = findViewById(R.id.baseLayout);
    }

    private void setListeners() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callLogin();
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().length()==0){
                    nameContainer.setVisibility(View.VISIBLE);
                    Snackbar snack = Snackbar.make(baseLayout,"Please provide a name and press register to complete!",Snackbar.LENGTH_LONG);
                    View v = snack.getView();
                    v.setBackgroundColor(ContextCompat.getColor(Login.this,R.color.colorAccent));
                    snack.show();
                }else {
                    callRegister();
                }

            }
        });
    }


    private void callRegister(){
        ApiCallInterface apiService = ServiceCallManager.getServiceCallManager(AppConfig.getServerAddress(),
                null, getApplicationContext()).create(ApiCallInterface.class);
        Call<RegisterResponse> callRegister = apiService.register( new RegisterRequestBody(email.getText().toString(),
                name.getText().toString(),password.getText().toString()));

        callRegister.clone().enqueue(getRegisterCallback);
    }

    private Callback<RegisterResponse> getRegisterCallback = new Callback<RegisterResponse>() {
        @Override
        public void onResponse(final Call<RegisterResponse> call, Response<RegisterResponse> response) {
            if(response.body()!=null){
                callLogin();
            }else{
                try {
                    showRetrySnack(response.errorBody().string(), true);
                } catch (Exception e) {
                    showRetrySnack(e.getMessage(), true);
                }
            }
        }

        @Override
        public void onFailure(Call<RegisterResponse> call, Throwable t) {
            showRetrySnack(t.getMessage(),false);
        }
    };

    private void showRetrySnack(String text, final boolean login){
        Snackbar snackbar = Snackbar.make(baseLayout,text,Snackbar.LENGTH_LONG);
        snackbar.setAction("RETRY", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(login){
                    callLogin();
                }else{
                    callRegister();
                }
            }
        });
        snackbar.show();
    }

    private void callLogin() {
        ApiCallInterface apiService = ServiceCallManager.getServiceCallManager(AppConfig.getServerAddress(),
                null, getApplicationContext()).create(ApiCallInterface.class);
        Call<LogInResponse> callLogin = apiService.login( new LogInRequestBody(email.getText().toString(),password.getText().toString()));

        callLogin.clone().enqueue(getLoginCallback);
    }

    private Callback<LogInResponse> getLoginCallback = new Callback<LogInResponse>() {
        @Override
        public void onResponse(final Call<LogInResponse> call, Response<LogInResponse> response) {
            if(response.body()!=null){
                AppConfig.setToken(response.body().getToken());
                AppConfig.setEmail(email.getText().toString());
                startActivity(new Intent(Login.this,BoxListAndResgistration.class));
            }else{
                try {
                    showRetrySnack(response.errorBody().string(), true);
                } catch (Exception e) {
                    showRetrySnack(e.getMessage(), true);
                }
            }
        }

        @Override
        public void onFailure(Call<LogInResponse> call, Throwable t) {
            showRetrySnack(t.getMessage(),true);
        }
    };


}
