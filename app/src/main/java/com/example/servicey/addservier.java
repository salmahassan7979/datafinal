package com.example.servicey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class addservier extends AppCompatActivity implements feedadapter.OnItemClickListener {
    private Spinner spinner;
    private ImageButton button;
    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_CREATOR = "service";
    public static final String EXTRA_server = "servier";
    public static final String EXTRA_LIKES = "rate";
    public static final String EXTRA_time = "time";
    public static final String EXTRA_date = "date";
    public static final String EXTRA_cost = "cost";
    public static final String EXTRA_discrebtion= "discrebtion";
    public static final String EXTRA_direction= "direction";
    database db =new database(this);
  //  private String mImageUrl;
    //private String mservice;
    private String mspname;
    //private int mrate;
    private RecyclerView mRecyclerView;
    private feedadapter mfeedAdapter;
    private ArrayList<recycleviewfeed> recycleview;
    private ArrayList<getdetails> view;
    private RequestQueue mRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addservier);

        mRecyclerView = findViewById(R.id.recycleart);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        recycleview = new ArrayList<>();
        view = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
        spinner = findViewById(R.id.spinner);
        button = findViewById(R.id.selectcategory);
        List<String> categories = new ArrayList<>();
        categories.add(0, "Choose Category");
        categories.add("Real Estate");
        categories.add("Arts");
        categories.add("Carpenter");
        categories.add("Plumbing");
        categories.add("Electricity");
        categories.add("Mechanics");
        categories.add("Other");


        //Style and populate the spinner
        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, categories);
        //Dropdown layout style
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("Choose Category"))
                {
                    //do nothing
                }
                //tetkarr lkol category
                else if(parent.getItemAtPosition(position).equals("Arts"))
                {
                    //on selecting a spinner item
                    String item = parent.getItemAtPosition(position).toString();
                    OpenActivity_arts();
                    //show selected spinner item
                    Toast.makeText(parent.getContext(), "Selected: " +item, Toast.LENGTH_SHORT).show();

                    //anything else you want to do on item selection do here
                }
                else{
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected: " +item, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // TODO Auto-generated method stub
            }
        });
    }


    private void parseJSON() {
        String url = "https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //esm el table
                            JSONArray jsonArray = response.getJSONArray("hits");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                String creatorName = hit.getString("user");
                                String imageUrl = hit.getString("webformatURL");
                                int likeCount = hit.getInt("likes");
//t3'eer 3la 7asb eldata bta3ty
                                recycleview.add(new recycleviewfeed(imageUrl, creatorName, likeCount));
                            }
//zawedt 3la elfeedadapter array tany 3l4an yb2a feh el details
                            mfeedAdapter = new feedadapter(addservier.this, recycleview);
                            mRecyclerView.setAdapter(mfeedAdapter);
                            mfeedAdapter.setOnItemClickListener(addservier.this);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }

    public void showData(){
        ArrayList<String> listData= db.getAllrecord();
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,listData);
        //lst.setAdapter(arrayAdapter);
    }
//hena bytal3ly el7gat ely 5azntha fe el array bta3et el feed lakn ana 3awza kyam zyada(time,date..)
    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
       // getdetails clickedItem = view.get(position);
recycleviewfeed clickedItem = recycleview.get(position);

        detailIntent.putExtra(EXTRA_URL, clickedItem.getImageUrl());
        detailIntent.putExtra(EXTRA_CREATOR, clickedItem.getservice());
        detailIntent.putExtra(EXTRA_LIKES, clickedItem.getrateCount());
        detailIntent.putExtra(EXTRA_date, clickedItem.getdate());
        detailIntent.putExtra(EXTRA_discrebtion, clickedItem.getdiscrebtion());
        detailIntent.putExtra(EXTRA_time, clickedItem.gettime());
        detailIntent.putExtra(EXTRA_cost, clickedItem.getcost());
        detailIntent.putExtra(EXTRA_direction, clickedItem.getdirection());
        detailIntent.putExtra(EXTRA_server, clickedItem.getspname());


        startActivity(detailIntent);
    }
    public void OpenActivity_arts(){
        //// Intent intent= new Intent(this,activity_.class);
        Intent intent= new Intent (this,Arts.class);
        startActivity( intent);

    }
}