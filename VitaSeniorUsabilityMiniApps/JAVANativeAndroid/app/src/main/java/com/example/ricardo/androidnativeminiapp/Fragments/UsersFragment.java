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
import com.example.ricardo.androidnativeminiapp.adapters.UsersListAdapter;
import com.example.ricardo.androidnativeminiapp.general.AppConfig;
import com.example.ricardo.androidnativeminiapp.networking.ApiCallInterface;
import com.example.ricardo.androidnativeminiapp.networking.ServiceCallManager;
import com.example.ricardo.androidnativeminiapp.networking.responseBodys.users.ListUsersResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersFragment extends Fragment {

    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.users_fragment, container, false);
        recyclerView = view.findViewById(R.id.usersRecycler);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        callListUsers();
        return view;
    }
    private void callListUsers(){
        ApiCallInterface apiService = ServiceCallManager.getServiceCallManager(AppConfig.getServerAddress(),
                null, getActivity()).create(ApiCallInterface.class);
        Call<ListUsersResponse> callListUsers = apiService.listUsers(getArguments().getString("VitaboxID"));

        callListUsers.clone().enqueue(getListUsersCallback);
    }

    private Callback<ListUsersResponse> getListUsersCallback = new Callback<ListUsersResponse>() {
        @Override
        public void onResponse(final Call<ListUsersResponse> call, Response<ListUsersResponse> response) {
            if (response.body() != null) {
                UsersListAdapter adapter = new UsersListAdapter(response.body().getUsers(),getActivity());
                recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
                recyclerView.setAdapter(adapter);
            }
        }

        @Override
        public void onFailure(Call<ListUsersResponse> call, Throwable t) {
        }
    };
}
