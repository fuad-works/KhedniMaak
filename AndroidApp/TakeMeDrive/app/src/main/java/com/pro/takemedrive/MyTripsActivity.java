package com.pro.takemedrive;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.pro.takemedrive.Models.FullTrip;
import com.pro.takemedrive.Models.Trip;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyTripsActivity extends AppCompatActivity {

    int user_id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trips);

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

        Call<List<FullTrip>> call = api.get_my_trips(user_id);

        call.enqueue(new Callback<List<FullTrip>>() {

            @Override
            public void onResponse(Call<List<FullTrip>> call, Response<List<FullTrip>> response) {
                final List<FullTrip> trips = response.body();

                TripAdapter adapter = new TripAdapter(getBaseContext(), new TripAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(FullTrip item) {
                        //Toast.makeText(getBaseContext(),item.getCat_name(),Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getBaseContext(),AddNewTripActivity.class);
                        i.putExtra("trip_id",item.getId());
                        i.putExtra("car",item.getCar());
                        i.putExtra("start_place",item.getStart_place());
                        i.putExtra("end_place",item.getEnd_place());
                        i.putExtra("the_date",item.getThe_date());
                        i.putExtra("the_time",item.getThe_time());
                        i.putExtra("duration",item.getDuration());
                        i.putExtra("price",item.getPrice());
                        i.putExtra("seats",item.getSeats());
                        startActivityForResult(i,1);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LoadData();
    }
}