package com.example.ricardo.androidnativeminiapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ricardo.androidnativeminiapp.R;
import com.example.ricardo.androidnativeminiapp.activitys.VitaboxInfo;
import com.example.ricardo.androidnativeminiapp.listeners.RecyclerViewOnClickListener;
import com.example.ricardo.androidnativeminiapp.networking.responseBodys.Vitabox;

import java.util.List;

public class VitaboxListAdapter extends RecyclerView.Adapter<VitaboxListAdapter.MyViewHolder> {

    private List<Vitabox> vitaboxList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView address, boxCoordinates;
        public ImageView statusImg;

        private RecyclerViewOnClickListener recyclerViewOnClickListener;

        public MyViewHolder(View view) {
            super(view);
            address = view.findViewById(R.id.boxAddress);
            boxCoordinates = view.findViewById(R.id.boxCoordinates);
            statusImg = view.findViewById(R.id.boxStatusImg);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            recyclerViewOnClickListener.onRecyclerViewItemClick(view, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            recyclerViewOnClickListener.onRecyclerViewItemClick(view, getAdapterPosition(), true);
            return true;
        }

        public void setItemClickLIstener(RecyclerViewOnClickListener itemClickLIstener) {
            this.recyclerViewOnClickListener = itemClickLIstener;
        }
    }


    public VitaboxListAdapter(List<Vitabox> vitaboxList, Context context) {
        this.vitaboxList = vitaboxList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vitabox_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Vitabox vitabox = vitaboxList.get(position);
        holder.address.setText(vitabox.getAddress());
        holder.boxCoordinates.setText(vitabox.getLatitude() + " / " + vitabox.getLongitude());
        holder.statusImg.setImageResource(vitabox.getActive() ? R.drawable.vita_logo : R.drawable.cross);

        holder.setItemClickLIstener(new RecyclerViewOnClickListener() {
            @Override
            public void onRecyclerViewItemClick(View v, int position, boolean longClick) {
                Intent intent = new Intent(context, VitaboxInfo.class);
                intent.putExtra("VitaboxID",vitaboxList.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return vitaboxList == null ? 0 : vitaboxList.size();
    }
}