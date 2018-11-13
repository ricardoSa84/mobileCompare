package com.example.ricardo.androidnativeminiapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ricardo.androidnativeminiapp.R;
import com.example.ricardo.androidnativeminiapp.listeners.RecyclerViewOnClickListener;
import com.example.ricardo.androidnativeminiapp.networking.responseBodys.Patient;
import com.example.ricardo.androidnativeminiapp.networking.responseBodys.users.User;

import java.util.List;

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.MyViewHolder> {

    private List<User> usersList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView name, email, regDate;
        public ImageView sponsorImg;

        private RecyclerViewOnClickListener recyclerViewOnClickListener;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            email = view.findViewById(R.id.email);
            regDate = view.findViewById(R.id.regDate);
            sponsorImg = view.findViewById(R.id.sponsorImg);
        }

        @Override
        public void onClick(View view) {
            if (recyclerViewOnClickListener != null)
                recyclerViewOnClickListener.onRecyclerViewItemClick(view, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            if (recyclerViewOnClickListener != null) {
                recyclerViewOnClickListener.onRecyclerViewItemClick(view, getAdapterPosition(), true);
                return true;
            }
            return false;
        }

        public void setItemClickLIstener(RecyclerViewOnClickListener itemClickLIstener) {
            this.recyclerViewOnClickListener = itemClickLIstener;
        }
    }


    public UsersListAdapter(List<User> usersList, Context context) {
        this.usersList = usersList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.users_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        User user = usersList.get(position);
        holder.name.setText(user.getName());
        holder.email.setText(user.getEmail());
        holder.regDate.setText(user.getSince());
    }

    @Override
    public int getItemCount() {
        return usersList == null ? 0 : usersList.size();
    }
}