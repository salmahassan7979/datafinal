package com.example.servicey;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class addimage {
    private static addimage maddimage;
    private RequestQueue requestQueue;

    private static Context mctx;
private addimage(Context context){
    mctx=context;
    requestQueue=getRequestQueue();
}

    private RequestQueue getRequestQueue() {
    if(requestQueue==null)
        requestQueue = Volley.newRequestQueue(mctx.getApplicationContext());
    return requestQueue;
    }
public static synchronized addimage getInstance(Context context){
    if(maddimage==null){
        maddimage=new addimage(context);
    }
    return maddimage;
}
public<T> void addToRequest(Request<T> request){
    getRequestQueue().add(request);
}

}
