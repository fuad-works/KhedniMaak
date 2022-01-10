package com.pro.takemedrive;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.pro.takemedrive.Models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    EditText ed1,ed2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed1 = findViewById(R.id.editText);
        ed2 = findViewById(R.id.editText5);

    }

    public void DoLogin(View v)
    {
        String uname = ed1.getText().toString();
        String password = ed2.getText().toString();

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("يتم الآن تسجيل الدخول");
        dialog.show();

        String base_url = getString(R.string.baseUrl);;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create()).build();

        API api = retrofit.create(API.class);
        Call<User> call = api.Login(uname,password);

        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                final User user = response.body();

                if(user == null)
                    Toast.makeText(getBaseContext(),"خطأ باسم المستخدم أو كلمة المرور",Toast.LENGTH_LONG).show();
                else if(user.getActive() != 1)
                {
                    Toast.makeText(getBaseContext(),"حسابك مقفول, يرجى التواصل مع المدير.",Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
                else
                {
                    Toast.makeText(getBaseContext(),"تم تسجيل الدخول بنجاح",Toast.LENGTH_LONG).show();

                    SharedPreferences prefs = getBaseContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed = prefs.edit();
                    ed.putInt("user_id", user.getId());
                    ed.putInt("user_type", user.getUser_type());
                    ed.commit();

                    Intent i = new Intent(getBaseContext(),UserDashboardActivity.class);
                    if(user.getUser_type() == 2)
                    {
                        i = new Intent(getBaseContext(),AdminDashboardActivity.class);
                    }
                    startActivity(i);
                }

                dialog.dismiss();
            }


            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getBaseContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });

    }
}
