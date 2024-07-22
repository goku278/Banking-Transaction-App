package com.example.bankingTransactionDemo.application;

import android.app.Application;

/**
 *  This is the entry point of our delivery app application,
 *  Here below we initiated PaperDb
 */

public class BankingTransactionDemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        Paper.init(this);
    }
}
