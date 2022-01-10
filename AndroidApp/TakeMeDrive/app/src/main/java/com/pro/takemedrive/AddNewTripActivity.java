package com.pro.takemedrive;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddNewTripActivity extends AppCompatActivity {

    int trip_id = 0;
    EditText ed_trip_car,ed_trip_start_place,ed_trip_end_place,ed_trip_date,ed_trip_time,ed_trip_duration,ed_trip_seats,ed_trip_price;
    int uid = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_trip);

        SharedPreferences prefs = getBaseContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        uid = prefs.getInt("user_id",0);

        ed_trip_car = findViewById(R.id.ed_trip_car);
        ed_trip_start_place = findViewById(R.id.ed_trip_start_place);
        ed_trip_end_place = findViewById(R.id.ed_trip_end_place);
        ed_trip_date = findViewById(R.id.ed_trip_date);
        ed_trip_time = findViewById(R.id.ed_trip_time);
        ed_trip_duration = findViewById(R.id.ed_trip_duration);
        ed_trip_seats = findViewById(R.id.ed_trip_seats);
        ed_trip_price = findViewById(R.id.ed_trip_price);
        Button btn = findViewById(R.id.btn_del);

        Intent i = getIntent();
        trip_id = i.getIntExtra("trip_id",0);
        if(trip_id != 0)
        {
            ed_trip_car.setText(i.getStringExtra("car"));
            ed_trip_start_place.setText(i.getStringExtra("start_place"));
            ed_trip_end_place.setText(i.getStringExtra("end_place"));
            ed_trip_date.setText(i.getStringExtra("the_date"));
            ed_trip_time.setText(i.getStringExtra("the_time"));
            ed_trip_duration.setText(i.getIntExtra("duration",0) + "");
            ed_trip_seats.setText(i.getIntExtra("seats",0) + "");
            ed_trip_price.setText(i.getIntExtra("price",0) + "");


            btn.setVisibility(View.VISIBLE);
        }
    }

    public void removeit(View v)
    {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("يتم الآن الحذف");
        dialog.show();
        RequestQueue queue = Volley.newRequestQueue(this);
        String base_url = getString(R.string.baseUrl);;
        String url = base_url + "deletetrip.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("1"))
                        {
                            Toast.makeText(getBaseContext(),"تم الحذف بنجاح",Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                            finish();
                        }
                        else
                        {
                            Toast.makeText(getBaseContext(),"لم يتم تقديم الطلب",Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    }
                }, new Response.ErrorListener() {
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
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void DoSave(View v)
    {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("يتم الآن انشاء الرحلة الجديدة");
        dialog.show();
        RequestQueue queue = Volley.newRequestQueue(this);
        String base_url = getString(R.string.baseUrl);;
        String url = base_url + "newtrip.php";

        if(trip_id > 0)
            url = base_url + "edittrip.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
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
                }, new Response.ErrorListener() {
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
                params.put("driver_id",uid + "");
                params.put("car",ed_trip_car.getText().toString());
                params.put("start_place",ed_trip_start_place.getText().toString());
                params.put("end_place",ed_trip_end_place.getText().toString());
                params.put("the_date",ed_trip_date.getText().toString());
                params.put("the_time",ed_trip_time.getText().toString());
                params.put("duration",ed_trip_duration.getText().toString());
                params.put("price",ed_trip_price.getText().toString());
                params.put("seats",ed_trip_seats.getText().toString());
                return params;
            }
        };
        queue.add(stringRequest);
    }
}