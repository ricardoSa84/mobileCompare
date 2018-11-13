package com.example.ricardo.androidnativeminiapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ricardo.androidnativeminiapp.R;
import com.example.ricardo.androidnativeminiapp.activitys.VitaboxInfo;
import com.example.ricardo.androidnativeminiapp.listeners.RecyclerViewOnClickListener;
import com.example.ricardo.androidnativeminiapp.networking.responseBodys.Patient;
import com.example.ricardo.androidnativeminiapp.networking.responseBodys.Vitabox;

import java.util.List;

public class PatientsListAdapter extends RecyclerView.Adapter<PatientsListAdapter.MyViewHolder> {

    private List<Patient> patientsList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView name, gender, age, weight, height, regDate;
        public ImageView statusImg;

        private RecyclerViewOnClickListener recyclerViewOnClickListener;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            gender = view.findViewById(R.id.gender);
            age = view.findViewById(R.id.age);
            weight = view.findViewById(R.id.weight);
            height = view.findViewById(R.id.height);
            regDate = view.findViewById(R.id.regDate);
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


    public PatientsListAdapter(List<Patient> patientsList, Context context) {
        this.patientsList = patientsList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.patients_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Patient patient = patientsList.get(position);
        holder.name.setText(patient.getName());
        holder.gender.setText(patient.getGender());
        holder.age.setText(patient.getBirthdate());
        holder.weight.setText(String.valueOf(patient.getWeight())+"Kg");
        holder.height.setText(String.valueOf(patient.getHeight())+"m");
        holder.regDate.setText(patient.getSince());
    }

    @Override
    public int getItemCount() {
        return patientsList == null ? 0 : patientsList.size();
    }
}