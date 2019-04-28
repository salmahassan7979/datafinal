package com.example.servicey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import static com.example.servicey.addservier.EXTRA_CREATOR;
import static com.example.servicey.addservier.EXTRA_LIKES;
import static com.example.servicey.addservier.EXTRA_URL;
import static com.example.servicey.addservier.EXTRA_cost;
import static com.example.servicey.addservier.EXTRA_date;
import static com.example.servicey.addservier.EXTRA_direction;
import static com.example.servicey.addservier.EXTRA_discrebtion;
import static com.example.servicey.addservier.EXTRA_server;
import static com.example.servicey.addservier.EXTRA_time;

public class detailcarpenter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailarts);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String creatorName = intent.getStringExtra(EXTRA_CREATOR);
        int likeCount = intent.getIntExtra(EXTRA_LIKES, 0);
        String servier = intent.getStringExtra(EXTRA_server);
        String time = intent.getStringExtra(EXTRA_time);
        String date = intent.getStringExtra(EXTRA_date);
        String discrebtion = intent.getStringExtra(EXTRA_discrebtion);
        String cost = intent.getStringExtra(EXTRA_cost);
        String direction = intent.getStringExtra(EXTRA_direction);


        ImageView imageView = findViewById(R.id.image_view_detail);
        TextView textViewCreator = findViewById(R.id.service_name);
        RatingBar textViewLikes = findViewById(R.id.ratingBar_detail);
        TextView textViewserver = findViewById(R.id.spname);
        TextView textViewtime = findViewById(R.id.time);
        TextView textViewdate = findViewById(R.id.date);
        TextView textViewcost = findViewById(R.id.cost);
        TextView textViewdirection = findViewById(R.id.direction);
        TextView textViewdiscrebtion = findViewById(R.id.discrebtion);
        Picasso.with(this).load(imageUrl).into(imageView);
        textViewCreator.setText(creatorName);
        textViewLikes.setRating(likeCount);
        textViewserver.setText(servier);
        textViewtime.setText(time);
        textViewdate.setText(date);
        textViewcost.setText(cost);
        textViewdirection.setText(direction);
        textViewdiscrebtion.setText(discrebtion);
    }
}