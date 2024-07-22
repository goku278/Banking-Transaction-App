package com.example.bankingTransactionDemo.ui;

import static com.example.bankingTransactionDemo.config.ApiConfig.APP_ID;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bankingTransactionDemo.R;
import com.example.bankingTransactionDemo.adapter.ApplicationAdapter;
import com.example.bankingTransactionDemo.model.Application;
import com.example.bankingTransactionDemo.model.Transaction;
import com.example.bankingTransactionDemo.network.ApiService;
import com.example.bankingTransactionDemo.network.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvTransactionList;
    private ApplicationAdapter adapter;
    private LinearLayout llPager1, llPager2, llPager3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rvTransactionList = findViewById(R.id.rvTransactionList);
        rvTransactionList.setLayoutManager(new LinearLayoutManager(this));

        llPager1 = findViewById(R.id.llPager1);
        llPager2 = findViewById(R.id.llPager2);
        llPager3 = findViewById(R.id.llPager3);

        setClickListeners();

        callApi(1);

    }

    private void setUI(ArrayList<Transaction> transactions) {
//        if (adapter == null) {
        adapter = new ApplicationAdapter(this, transactions);
        rvTransactionList.setAdapter(adapter);
//        } else {
        adapter.notifyDataSetChanged();
//        }
    }

    private void callApi(int pageNo) {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);

        Call<Application> call = apiService.getTransaction(pageNo, APP_ID);
        call.enqueue(new Callback<Application>() {
            @Override
            public void onResponse(@NonNull Call<Application> call, @NonNull Response<Application> response) {
                if (response.isSuccessful()) {
                    Application application = response.body();
                    System.out.println("Page: " + pageNo);
                    assert application != null;
                    System.out.println("Success: " + application.getSuccess());
                    System.out.println("Message: " + application.getMsg());
                    System.out.println("Data: " + application.getData());

                    assert response.body() != null;
                    Log.d("MainActivity", "Response data is = " + response.body().getData());

                    setUI(response.body().getData().getTransactions());

                } else {
                    System.err.println("Request failed. Code: " + response.code());
                    Toast.makeText(getApplicationContext(),
                            "Error while retrieving data form server, error code: " + response.code(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Application> call, Throwable t) {
//                System.err.println("Request error: " + t.getMessage());
                Log.d("MainActivity", "Request error: " + t.getMessage());
                Toast.makeText(getApplicationContext(),
                        "Error while retrieving data form server, error message: \n" + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setClickListeners() {
        llPager1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callApi(1);
            }
        });
        llPager2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callApi(2);
            }
        });
        llPager3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callApi(3);
            }
        });
    }
}