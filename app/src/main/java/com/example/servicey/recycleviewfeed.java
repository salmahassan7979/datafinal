package com.example.servicey;

public class recycleviewfeed {
    private String mImageUrl;
    private String mservice;
    private String mspname;
    private int mrate;
    private String mtime;
    private String mdate;
    private String mcost;
    private String mdirection;
    private String mdiscrebtion;
    public recycleviewfeed(String imageUrl, String service, int rate) {
        mImageUrl = imageUrl;
        mservice = service;

        mrate =rate;
    }
    public recycleviewfeed(String imageUrl, String service, int rate,String spname) {
        mImageUrl = imageUrl;
        mservice = service;
        mspname=spname;
        mrate =rate;
    }
    public recycleviewfeed(String imageUrl, String service, int rate,String spname,String time,String date
            ,String cost,String direction,String discrebtion) {
        mImageUrl = imageUrl;
        mservice = service;
        mspname=spname;
        mrate =rate;
        mtime=time;
        mdate=date;
        mcost=cost;
        mdiscrebtion=discrebtion;
        mdirection = direction;
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
    public String gettime() {
        return mtime;
    }

    public String getdate() {
        return mdate;
    }

    public String getcost() {
        return mcost;
    }
    public String getdiscrebtion() {
        return mdiscrebtion;
    }
    public String getdirection() {
        return mdirection;
    }
}
