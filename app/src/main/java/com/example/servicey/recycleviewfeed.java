package com.example.servicey;

public class recycleviewfeed {
    private String mImageUrl;
    private String mservice;
    private String mspname;
    private int mrate;

    public recycleviewfeed(String imageUrl, String service, int rate,String spname) {
        mImageUrl = imageUrl;
        mservice = service;
        mspname=spname;
        mrate =rate;
    }
    public String getspname() {
        return mspname;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getservice() {
        return mservice;
    }

    public int getrateCount() {
        return mrate;
    }
}
