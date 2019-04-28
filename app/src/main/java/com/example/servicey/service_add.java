package com.example.servicey;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class service_add extends AppCompatActivity {
    private static final int IMG_REQUEST = 1;
  private   EditText name, chrim, ID, mobile, servname, dis, tim, dat, direc, cos;
int i;
    private ImageButton imgCHREM;
    private ImageButton imgSERV;
    private ImageButton imgID;
    Bitmap bitmap;
    ImageView imgviewid;
    private Button button;
private String url="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_add);
        name = findViewById(R.id.servername);
        mobile = findViewById(R.id.mobile);
        ID = findViewById(R.id.ID);
        imgviewid = findViewById(R.id.imageView8);
        imgCHREM = findViewById(R.id.imgchem);
        imgID = findViewById(R.id.imgid);
        imgSERV = findViewById(R.id.imgserv);
        chrim = findViewById(R.id.Chreminal);
        servname = findViewById(R.id.Servicename);
        dis = findViewById(R.id.discrbtioon);
        tim = findViewById(R.id.time);
        dat = findViewById(R.id.date);
        direc = findViewById(R.id.Direction);
        cos = findViewById(R.id.cost);
         imgID.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View v) {
                 i=1;
                 selectimage();
                 upload();
                 Bitmap idbit=bitmap;
             }

         });
        imgSERV.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                i=2;
                selectimage();
                upload();
            }

        });
        imgCHREM.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                i=3;
                selectimage();
                upload();
            }

        });


    }

  //  @Override
  //  public void onClick(View v) {
    //    selectimage();
  //  }

    private void selectimage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMG_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == IMG_REQUEST&& resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri path=data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
               // imgviewid.setImageBitmap(bitmap);
                if(i==1)
                {imgID.setImageBitmap(bitmap);
                Bitmap idbit=bitmap;
                //imgviewid.setVisibility(View.VISIBLE);
                imgID.setVisibility(View.VISIBLE);}
                else if(i==3){
                imgCHREM.setImageBitmap(bitmap);
                imgCHREM.setVisibility(View.VISIBLE);}
                 else{ imgSERV.setImageBitmap(bitmap);
                    imgSERV.setVisibility(View.VISIBLE);}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void upload() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject= new JSONObject(response);
                            String Response= jsonObject.getString("Response");
                            Toast.makeText(service_add.this, "service added"+Response, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            protected Map<String, String> getparams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", name.getText().toString().trim());
                params.put("mobile", mobile.getText().toString().trim());


                params.put("servname", servname.getText().toString().trim());
                params.put("discribtion", dis.getText().toString().trim());
                params.put("timestamps", tim.getText().toString().trim());
                params.put("date", dat.getText().toString().trim());
                params.put("direction", direc.getText().toString().trim());
                params.put("cost", cos.getText().toString().trim());
                params.put("serviceimg", imagetostring(bitmap));
                params.put("criminalrecord", imagetostring(bitmap));
                params.put("id", imagetostring(bitmap));
                return params;
            }

        };
        addimage.getInstance(service_add.this).addToRequest(stringRequest);
    }
        private String imagetostring(Bitmap bitmap){
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] imgBytes = byteArrayOutputStream.toByteArray();
            return Base64.encodeToString(imgBytes, Base64.DEFAULT);

        }
    }