package com.pro.takemedrive;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
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

public class RegisterActivity extends AppCompatActivity {

    EditText ed1,ed2,ed3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ed1 = findViewById(R.id.ed_reg_user_name);
        ed2 = findViewById(R.id.ed_reg_pass);
        ed3 = findViewById(R.id.ed_reg_pass_rep);

    }

    public void DoRegister(View v)
    {
        if(!ed2.getText().toString().equals(ed3.getText().toString()))
        {
            Toast.makeText(this,"كلمة المرور غير موافقة للتأكيد",Toast.LENGTH_LONG).show();
            return;
        }

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("يتم الآن تسجيل الحساب");
        dialog.show();
        RequestQueue queue = Volley.newRequestQueue(this);
        String base_url = getString(R.string.baseUrl);;
        String url = base_url + "register.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("1"))
                        {
                            Toast.makeText(getBaseContext(),"تم التسجيل بنجاح",Toast.LENGTH_LONG).show();  dialog.dismiss();  finish();
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
                params.put("user_name",ed1.getText().toString());
                params.put("password",ed2.getText().toString());
                return params;
            }
        };
        queue.add(stringRequest);
    }
}
