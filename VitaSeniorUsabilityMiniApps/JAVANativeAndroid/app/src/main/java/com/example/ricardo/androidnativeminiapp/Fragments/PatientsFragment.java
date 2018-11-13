package com.example.ricardo.androidnativeminiapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ricardo.androidnativeminiapp.R;
import com.example.ricardo.androidnativeminiapp.adapters.PatientsListAdapter;
import com.example.ricardo.androidnativeminiapp.general.AppConfig;
import com.example.ricardo.androidnativeminiapp.networking.ApiCallInterface;
import com.example.ricardo.androidnativeminiapp.networking.ServiceCallManager;
import com.example.ricardo.androidnativeminiapp.networking.responseBodys.ListPatientsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientsFragment extends Fragment {

    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.patients_fragment, container, false);
        recyclerView = view.findViewById(R.id.patientsRecycler);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        callListPatiens();
        return view;
    }
    private void callListPatiens(){
        ApiCallInterface apiService = ServiceCallManager.getServiceCallManager(AppConfig.getServerAddress(),
                null, getActivity()).create(ApiCallInterface.class);
        Call<ListPatientsResponse> callListPatients = apiService.listPatients(getArguments().getString("VitaboxID"));

        callListPatients.clone().enqueue(getListPatientsCallback);
    }

    private Callback<ListPatientsResponse> getListPatientsCallback = new Callback<ListPatientsResponse>() {
        @Override
        public void onResponse(final Call<ListPatientsResponse> call, Response<ListPatientsResponse> response) {
            if (response.body() != null) {
                PatientsListAdapter adapter = new PatientsListAdapter(response.body().getPatients(),getActivity());
                recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
                recyclerView.setAdapter(adapter);
            }
        }

        @Override
        public void onFailure(Call<ListPatientsResponse> call, Throwable t) {
        }
    };

}
