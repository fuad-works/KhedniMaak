package com.pro.takemedrive;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.pro.takemedrive.Models.FullTrip;
import com.pro.takemedrive.Models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ManageUserActivity extends AppCompatActivity {

    int user_id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user);

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

        Call<List<User>> call = api.get_all_useres(user_id);

        call.enqueue(new Callback<List<User>>() {

            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                final List<User> userse = response.body();

                UserAdapter adapter = new UserAdapter(getBaseContext(), new UserAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(User item) {
                        //Toast.makeText(getBaseContext(),item.getCat_name(),Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getBaseContext(),TheUserActivity.class);
                        i.putExtra("user_id",item.getId());
                        i.putExtra("user_name",item.getUser_name());
                        i.putExtra("active",item.getActive());
                        i.putExtra("user_type",item.getUser_type());
                        startActivityForResult(i,1);
                    }
                },userse);

                RecyclerView rv1 = findViewById(R.id.mytripsrec);
                rv1.setAdapter(adapter);

                RecyclerView.LayoutManager layoutManager;

                layoutManager = new GridLayoutManager(getBaseContext(),1);

                rv1.setLayoutManager(layoutManager);

            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
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