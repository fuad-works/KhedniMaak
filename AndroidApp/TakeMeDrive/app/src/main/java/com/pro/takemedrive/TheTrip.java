package com.pro.takemedrive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pro.takemedrive.Models.FullTrip;
import com.pro.takemedrive.Models.Participate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TheTrip extends AppCompatActivity {

    int trip_id = 0;
    TextView ed_trip_driver,ed_trip_start_place,ed_trip_end_place,ed_trip_date;
    int uid = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_trip);

        ed_trip_driver = findViewById(R.id.txt_ttrip_driver);
        ed_trip_start_place = findViewById(R.id.txt_ttrip_from);
        ed_trip_end_place = findViewById(R.id.txt_ttrip_to);
        ed_trip_date = findViewById(R.id.txt_ttrip_datetime);

        Intent i = getIntent();
        trip_id = i.getIntExtra("trip_id",0);
        if(trip_id != 0)
        {
            ed_trip_driver.setText(i.getStringExtra("driver"));
            ed_trip_start_place.setText("من: " + i.getStringExtra("start_place"));
            ed_trip_end_place.setText("إلى: " + i.getStringExtra("end_place"));
            ed_trip_date.setText(i.getStringExtra("the_time") + " - " + i.getStringExtra("the_date"));
        }

        SharedPreferences prefs = getBaseContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        uid = prefs.getInt("user_id",0);

        LoadData();
    }

    private void LoadData() {

        String url = getString(R.string.baseUrl);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);

        Call<List<Participate>> call = api.getparticipate(uid,trip_id);

        call.enqueue(new Callback<List<Participate>>() {

            @Override
            public void onResponse(Call<List<Participate>> call, Response<List<Participate>> response) {
                final List<Participate> trips = response.body();

                Button btn = findViewById(R.id.btn_toggle);
                if(trips == null || trips.size() == 0)
                    btn.setText("تسجيل بالرحلة");
                else
                    btn.setText("إلغاء التسجيل");

                }


            @Override
            public void onFailure(Call<List<Participate>> call, Throwable t) {
                Toast.makeText(getBaseContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });



    }

    public void Toggle(View v)
    {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("يتم الآن انشاء الرحلة الجديدة");
        dialog.show();
        RequestQueue queue = Volley.newRequestQueue(this);
        String base_url = getString(R.string.baseUrl);;
        String url = base_url + "doparticipate.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("1"))
                        {
                            Toast.makeText(getBaseContext(),"تم الحفظ بنجاح",Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                            finish();
                        }
                        else
                        {
                            Toast.makeText(getBaseContext(),"لم يتم تقديم الطلب",Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(getBaseContext(),"حصل خطأ بالإتصال" + "\n" + error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String,String> getParams() {
                Map<String,String> params = new HashMap<String,String>();
                params.put("trip_id",trip_id + "");
                params.put("user_id",uid + "");
                return params;
            }
        };
        queue.add(stringRequest);
    }
}