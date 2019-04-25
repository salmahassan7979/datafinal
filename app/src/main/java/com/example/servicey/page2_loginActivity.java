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

import java.util.ArrayList;

public class page2_loginActivity extends AppCompatActivity {
    database db =new database(this);
    EditText  email,login_id;
    private Button button;
  Button  signup;
    ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2_login);
        email = findViewById(R.id.mail);
       login_id = findViewById(R.id.pswrdd);
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
    public void OpenActivity_added(){
        //// Intent intent= new Intent(this,activity_.class);
        Intent intent= new Intent (this,addservier.class);
        startActivity( intent);

    }
     public void add(View view) {
         String pass = login_id.getText().toString();

         String EMAIL = email.getText().toString();

         Boolean result = db.insertdatalogin(pass, EMAIL);

         //OpenActivity_added();
         if (result == true) {
             Toast.makeText(page2_loginActivity.this, "login is successfully", Toast.LENGTH_SHORT).show();
             //  name.setText("");
             //  email.setText("");
             OpenActivity_added();
         } else {
             OpenActivity_signup();
             Toast.makeText(page2_loginActivity.this, "please enter again", Toast.LENGTH_SHORT).show();
         } OpenActivity_added();
         }
    public void OpenActivity_signup(){
        //// Intent intent= new Intent(this,activity_.class);
        Intent intent= new Intent (this,signup.class);
        startActivity( intent);

    }
    public void showData(){
        ArrayList<String> listData= db.getAllrecord();
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,listData);
        lst.setAdapter(arrayAdapter);
    }
    }
