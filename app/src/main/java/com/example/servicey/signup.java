package com.example.servicey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

//comment is sqlite if you need reges by sqlite
public class signup extends AppCompatActivity {
  //  database db =new database(this);
    EditText name, email,password,mobilenumber;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name = findViewById(R.id.username);
        email = findViewById(R.id.emaill);
        password = findViewById(R.id.psw);
        mobilenumber = findViewById(R.id.mobilee);
        button = findViewById(R.id.sup);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Reseg();

               /* public void add(View view){
                String pass = id.getText().toString();
                String NAME = name.getText().toString();
                String EMAIL = email.getText().toString();
                String mob = mobilenumber.getText().toString();
                Boolean result = db.insertdatasignup(pass, NAME, EMAIL, mob);


                if (result == true) {
                    Toast.makeText(signup.this, "OK,Account created", Toast.LENGTH_SHORT).show();
                    //  name.setText("");
                    //  email.setText("");
                } else
                    Toast.makeText(signup.this, "NO, please enter again", Toast.LENGTH_SHORT).show();

                OpenActivity_added();

            }*/
                // }
            }  });
    }
    private void Reseg(){
        button.setVisibility(View.GONE);
        final String pass = password.getText().toString().trim();
        final String NAME = name.getText().toString().trim();
        final String EMAIL = email.getText().toString().trim();
        String mob = mobilenumber.getText().toString().trim();
String url="192.168.1.145/android_register_login/register.php";
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonobject = new JSONObject(response);
                            String success= jsonobject.getString("success");
                            if(success.equals("1")){
                                Toast.makeText(signup.this, "register success !", Toast.LENGTH_SHORT).show();

                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(signup.this, "register Error"+e.toString(), Toast.LENGTH_SHORT).show();
                            button.setVisibility(View.VISIBLE);
                        }
                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(signup.this, "register Error"+error.toString(), Toast.LENGTH_SHORT).show();

                button.setVisibility(View.VISIBLE);
            }
        })
        {
            protected Map<String,String> getparams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("name",NAME);
                params.put("password",pass);
                params.put("email",EMAIL);
                return params;
            }
        };
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        mRequestQueue.add(request);
    }


        public void OpenActivity_added(){
        //// Intent intent= new Intent(this,activity_.class);
        Intent intent= new Intent (this,addservier.class);
        startActivity( intent);

    }
}
