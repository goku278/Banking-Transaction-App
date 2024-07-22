package com.example.bankingTransactionDemo.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Data {
    private String totalBalance;
    ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    // Getter Methods
    public String getTotalBalance() {
        return totalBalance;
    }

    // Setter Methods

    public void setTotalBalance(String totalBalance) {
        this.totalBalance = totalBalance;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    @NonNull
    @Override
    public String toString() {
        return "Data{" +
                "totalBalance='" + totalBalance + '\'' +
                ", transactions=" + transactions +
                '}';
    }
}