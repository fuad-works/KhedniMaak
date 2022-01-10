package com.pro.takemedrive;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class TheUserActivity extends AppCompatActivity {

    int user_id = 0;
    String user_name = "";
    int active = 0;
    int user_type = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_user);

        Button btn_active = findViewById(R.id.btn_activate);
        Button btn_upgrade = findViewById(R.id.btn_upgrade);
        TextView tv = findViewById(R.id.textView3);
        Intent i = getIntent();

        user_name = i.getStringExtra("user_name");
        user_id = i.getIntExtra("user_id",0);
        active = i.getIntExtra("active",0);
        user_type = i.getIntExtra("user_type",0);

        tv.setText(user_name);

        if(active == 1)
            btn_active.setText("قفل الحساب");
        else
            btn_active.setText("إلغاء القفل");

        if(user_type == 2)
            btn_upgrade.setText("إلغاء الترقية من مدير");
        else
            btn_upgrade.setText("الترقية لمدير");
    }

    public void Upgrade(View v)
    {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("تتم المعالجة");
        dialog.show();
        RequestQueue queue = Volley.newRequestQueue(this);
        String base_url = getString(R.string.baseUrl);;
        String url = base_url + "upuser.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("1"))
                        {
                            Toast.makeText(getBaseContext(),"تم بنجاح",Toast.LENGTH_LONG).show();  dialog.dismiss();  finish();
                        }
                        else
                        {
                            Toast.makeText(getBaseContext(),"لم يتم تقديم الطلب",Toast.LENGTH_LONG).show();  dialog.dismiss();
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
                params.put("user_id",user_id + "");
                int user_type1 = 1;
                if(user_type == 1)
                    user_type1 = 2;
                params.put("user_type",user_type1 + "");
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void Activ(View v)
    {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("تتم المعالجة");
        dialog.show();
        RequestQueue queue = Volley.newRequestQueue(this);
        String base_url = getString(R.string.baseUrl);;
        String url = base_url + "activeuser.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("1"))
                        {
                            Toast.makeText(getBaseContext(),"تم بنجاح",Toast.LENGTH_LONG).show();  dialog.dismiss();  finish();
                        }
                        else
                        {
                            Toast.makeText(getBaseContext(),"لم يتم تقديم الطلب",Toast.LENGTH_LONG).show();  dialog.dismiss();
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
                params.put("user_id",user_id + "");
                int active1 = 0;
                if(active == 0)
                    active1 = 1;
                params.put("active",active1 + "");
                return params;
            }
        };
        queue.add(stringRequest);
    }
}