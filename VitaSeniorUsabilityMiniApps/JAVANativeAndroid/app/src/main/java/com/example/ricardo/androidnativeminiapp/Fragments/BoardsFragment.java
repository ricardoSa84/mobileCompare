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
import com.example.ricardo.androidnativeminiapp.adapters.BoardsListAdapter;
import com.example.ricardo.androidnativeminiapp.general.AppConfig;
import com.example.ricardo.androidnativeminiapp.networking.ApiCallInterface;
import com.example.ricardo.androidnativeminiapp.networking.ServiceCallManager;
import com.example.ricardo.androidnativeminiapp.networking.responseBodys.boards.ListBoardsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardsFragment extends Fragment {
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.boards_fragment, container, false);
        recyclerView = view.findViewById(R.id.boardsRecycler);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        callListBoards();
        return view;
    }
    private void callListBoards(){
        ApiCallInterface apiService = ServiceCallManager.getServiceCallManager(AppConfig.getServerAddress(),
                null, getActivity()).create(ApiCallInterface.class);
        Call<ListBoardsResponse> callListBoards = apiService.listBoards(getArguments().getString("VitaboxID"));

        callListBoards.clone().enqueue(getListBoardsCallback);
    }

    private Callback<ListBoardsResponse> getListBoardsCallback = new Callback<ListBoardsResponse>() {
        @Override
        public void onResponse(final Call<ListBoardsResponse> call, Response<ListBoardsResponse> response) {
            if (response.body() != null) {
                BoardsListAdapter adapter = new BoardsListAdapter(response.body().getBoards(),getActivity());
                recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
                recyclerView.setAdapter(adapter);
            }
        }

        @Override
        public void onFailure(Call<ListBoardsResponse> call, Throwable t) {
        }
    };

}
