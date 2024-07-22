package com.example.bankingTransactionDemo.model;

import androidx.annotation.NonNull;

public class Application {
    private boolean success;
    Data data;
    private String msg;


    // Getter Methods

    public boolean getSuccess() {
        return success;
    }

    public Data getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    // Setter Methods

    public void setSuccess( boolean success ) {
        this.success = success;
    }

    public void setData( Data dataObject ) {
        this.data = dataObject;
    }

    public void setMsg( String msg ) {
        this.msg = msg;
    }

    @NonNull
    @Override
    public String toString() {
        return "Application{" +
                "success=" + success +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}