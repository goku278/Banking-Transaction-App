package com.example.bankingTransactionDemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bankingTransactionDemo.R;
import com.example.bankingTransactionDemo.model.Application;
import com.example.bankingTransactionDemo.model.Transaction;
//import com.example.deliveryapp.R;

import java.util.ArrayList;
import java.util.List;

public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationAdapter.ViewHolder> {

    private final List<Transaction> transactionList;
    private Context context;

    public ApplicationAdapter(Context context, ArrayList<Transaction> transactionList) {
        this.context = context;
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.application_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaction transaction = transactionList.get(position);
        holder.tvTransactionId.setText(transaction.getId());
        holder.tvTransactionType.setText(transaction.getType());

        if (transaction.getType().equals("credit")) {
            holder.ivUpArrow.setImageResource(R.drawable.down_arrow);
            holder.tvAmount.setTextColor(context.getResources().getColor(R.color.green));
            holder.tvAmount.setText("+" + transaction.getAmount());
        }
        else {
            holder.ivUpArrow.setImageResource(R.drawable.up_arrow);
            holder.tvAmount.setTextColor(context.getResources().getColor(R.color.red));
            holder.tvAmount.setText("-" + transaction.getAmount());
        }
        // Assuming Data has a meaningful toString method
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTransactionId;
        public TextView tvTransactionType;
        public TextView tvAmount;
        public ImageView ivUpArrow;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTransactionId = itemView.findViewById(R.id.tvTransactionId);
            tvTransactionType = itemView.findViewById(R.id.tvTransactionType);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            ivUpArrow = itemView.findViewById(R.id.ivUpArrow);
        }
    }
}