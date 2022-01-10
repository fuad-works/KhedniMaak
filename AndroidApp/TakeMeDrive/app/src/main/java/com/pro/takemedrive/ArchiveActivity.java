package com.pro.takemedrive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.pro.takemedrive.Models.FullTrip;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArchiveActivity extends AppCompatActivity {

    int user_id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);

        SharedPreferences prefs = getBaseContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        user_id = prefs.getInt("user_id",0);

        LoadData();
    }

    private void LoadData() {

        String url = getString(R.string.baseUrl);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);

        Call<List<FullTrip>> call = api.get_archive_trips(user_id);

        call.enqueue(new Callback<List<FullTrip>>() {

            @Override
            public void onResponse(Call<List<FullTrip>> call, Response<List<FullTrip>> response) {
                final List<FullTrip> trips = response.body();

                TripAdapter adapter = new TripAdapter(getBaseContext(), new TripAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(FullTrip item) {

                    }
                },trips);

                RecyclerView rv1 = findViewById(R.id.mytripsrec);
                rv1.setAdapter(adapter);

                RecyclerView.LayoutManager layoutManager;

                layoutManager = new GridLayoutManager(getBaseContext(),1);

                rv1.setLayoutManager(layoutManager);

            }
            @Override
            public void onFailure(Call<List<FullTrip>> call, Throwable t) {
                Toast.makeText(getBaseContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}