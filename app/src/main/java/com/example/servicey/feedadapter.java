package com.example.servicey;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public  class feedadapter extends RecyclerView.Adapter<feedadapter.feedViewHolder> {
    private Context mcontext;
    private ArrayList<recycleviewfeed> mrecycle;
    private ArrayList<getdetails> mview;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    public feedadapter(Context context, ArrayList<recycleviewfeed> recycle,ArrayList<getdetails> view){
        mcontext =context;
        mrecycle=recycle;
        mview=view;
    }


    @Override
    public feedViewHolder onCreateViewHolder( ViewGroup parent, int i) {

        View v = LayoutInflater.from(mcontext).inflate(R.layout.recycleviewfeed, parent, false);
        return new feedViewHolder(v);
    }

    @Override
    public void onBindViewHolder( feedViewHolder feedViewHolder, int i) {
        recycleviewfeed currentItem = mrecycle.get(i);

        String imageUrl = currentItem.getImageUrl();
        String creatorName = currentItem.getservice();
        int likeCount = currentItem.getrateCount();
         String name = currentItem.getspname();

        feedViewHolder.sname.setText(creatorName);


        feedViewHolder.spname.setText(name);
        feedViewHolder.ratem.setRating(likeCount);
       // Picasso.get().load(imageUrl).placeholder(R.drawable.default_pic).into(imageView);mcontext
        Picasso.get().load(imageUrl).fit().centerInside().into(feedViewHolder.image);
        }

    @Override
    public int getItemCount() {
        return mrecycle.size() ;
    }

    public class  feedViewHolder  extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView spname;
        public TextView sname;
        public RatingBar ratem;
        public  feedViewHolder(View itemView){
            super(itemView);
            image = itemView.findViewById(R.id.imageView5);
            spname=itemView.findViewById(R.id.service_name);
            sname=itemView.findViewById(R.id.spname);
            ratem=itemView.findViewById(R.id.ratingBar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
