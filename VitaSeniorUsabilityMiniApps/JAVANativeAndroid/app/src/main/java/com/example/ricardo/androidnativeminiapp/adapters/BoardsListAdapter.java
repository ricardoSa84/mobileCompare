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
import com.example.ricardo.androidnativeminiapp.networking.responseBodys.Board;
import com.example.ricardo.androidnativeminiapp.networking.responseBodys.Patient;

import java.util.List;

public class BoardsListAdapter extends RecyclerView.Adapter<BoardsListAdapter.MyViewHolder> {

    private List<Board> boardsList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView name, lastUpdate;
        public ImageView statusImg;

        private RecyclerViewOnClickListener recyclerViewOnClickListener;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            lastUpdate = view.findViewById(R.id.lastUpdate);
            statusImg = view.findViewById(R.id.statusImg);
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


    public BoardsListAdapter(List<Board> boardsList, Context context) {
        this.boardsList = boardsList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.boards_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Board board = boardsList.get(position);
        holder.name.setText(board.getBoardmodel().getName());
        holder.lastUpdate.setText(board.getUpdatedAt());
        holder.statusImg.setImageResource(board.getActive() ? R.drawable.vita_logo : R.drawable.cross);
    }

    @Override
    public int getItemCount() {
        return boardsList == null ? 0 : boardsList.size();
    }
}