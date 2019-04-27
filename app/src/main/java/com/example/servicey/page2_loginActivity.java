package com.example.servicey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class page2_loginActivity extends AppCompatActivity {
    database db =new database(this);
    EditText  email,login_id;
    private Button button;
  Button  signup;
    ListView lst;
    private RequestQueue mRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2_login);
        email = findViewById(R.id.emaill);
       login_id = findViewById(R.id.psw);
        mRequestQueue = Volley.newRequestQueue(this);
       // parseJSON();
        button = findViewById(R.id.signin);
        signup=findViewById(R.id.signup);
      //  lst=(ListView)findViewById(R.id.list);
        signup.setOnClickListener(new View.OnClickListener() {

                                      @Override
                                      public void onClick(View m) {
                                          OpenActivity_signup();
                                      }
                                  });
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

    OpenActivity_added();


            }

        });
    }
    public void OpenActivity_added() {
        String url = "https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(page2_loginActivity.this, "OK,Account created", Toast.LENGTH_SHORT).show();
                       act();
                    }


                }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            protected Map<String,String>getparams() throws AuthFailureError{
                Map<String,String> params=new HashMap<>();
                params.put("username","xxx");
                params.put("password","yyy");
                return params;
            }
        };
    }
    public void OpenActivity_signup() {
            //// Intent intent= new Intent(this,activity_.class);
            Intent intent = new Intent(this, signup.class);
            startActivity(intent);

    }

public void act(){
    Intent intent= new Intent(this,addservier.class);
     startActivity( intent);
}
    }