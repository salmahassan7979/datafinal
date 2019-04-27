package com.example.servicey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {
    database db =new database(this);
    EditText name, email,id,mobilenumber;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name = findViewById(R.id.username);
        email = findViewById(R.id.emaill);
        id = findViewById(R.id.psw);
        mobilenumber = findViewById(R.id.mobilee);
        button = findViewById(R.id.sup);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //  public void add(View view){
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

            }
            // }
        });
    }
        public void OpenActivity_added(){
        //// Intent intent= new Intent(this,activity_.class);
        Intent intent= new Intent (this,addservier.class);
        startActivity( intent);

    }
}
