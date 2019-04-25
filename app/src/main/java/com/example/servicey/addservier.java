package com.example.servicey;

import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.ArrayList;



public class addservier extends AppCompatActivity implements feedadapter.OnItemClickListener {
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

        mRecyclerView = findViewById(R.id.recycle);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        recycleview = new ArrayList<>();
        view = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
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
                                recycleview.add(new recycleviewfeed(imageUrl, creatorName, likeCount,mspname));
                            }
//zawedt 3la elfeedadapter array tany 3l4an yb2a feh el details
                            mfeedAdapter = new feedadapter(addservier.this, recycleview,view);
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
        getdetails clickedItem = view.get(position);
recycleviewfeed click = recycleview.get(position);

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
}