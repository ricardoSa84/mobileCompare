package com.example.ricardo.androidnativeminiapp.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.ricardo.androidnativeminiapp.R;
import com.example.ricardo.androidnativeminiapp.adapters.VitaboxListAdapter;
import com.example.ricardo.androidnativeminiapp.general.AppConfig;
import com.example.ricardo.androidnativeminiapp.networking.ApiCallInterface;
import com.example.ricardo.androidnativeminiapp.networking.ServiceCallManager;
import com.example.ricardo.androidnativeminiapp.networking.responseBodys.ListBoxsResponse;
import com.example.ricardo.androidnativeminiapp.networking.responseBodys.Vitabox;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoxListAndResgistration extends AppCompatActivity {

    private RecyclerView boxListRecycler;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.box_list_and_resgistration_layout);
        getSupportActionBar().hide();
        setUiComponents();
        setListeners();
        callGetVitaboxes();
    }

    private void setUiComponents() {
        boxListRecycler = findViewById(R.id.boxListRecycler);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        boxListRecycler.setLayoutManager(mLayoutManager);
        registerButton = findViewById(R.id.registerBoxBtn);
    }

    private void setListeners() {
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BoxListAndResgistration.this, BoxRegistration.class));
            }
        });
    }

    private void callGetVitaboxes() {
        ApiCallInterface apiService = ServiceCallManager.getServiceCallManager(AppConfig.getServerAddress(),
                null, getApplicationContext()).create(ApiCallInterface.class);
        Call<ListBoxsResponse> callVitaboxes = apiService.listBoxs();

        callVitaboxes.clone().enqueue(getListBoxsCallback);
    }

    private Callback<ListBoxsResponse> getListBoxsCallback = new Callback<ListBoxsResponse>() {
        @Override
        public void onResponse(final Call<ListBoxsResponse> call, Response<ListBoxsResponse> response) {
            if (response.body() != null) {
                VitaboxListAdapter adapter = new VitaboxListAdapter(response.body().getVitaboxes(),BoxListAndResgistration.this);
                boxListRecycler.addItemDecoration(new DividerItemDecoration(BoxListAndResgistration.this, LinearLayoutManager.VERTICAL));
                boxListRecycler.setAdapter(adapter);
            }
        }

        @Override
        public void onFailure(Call<ListBoxsResponse> call, Throwable t) {
        }
    };

}
