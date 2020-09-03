package com.e.volley.model;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;

public class RequestQueSingleTone {

    private Context context;
    private RequestQueue requestQueue;
    private static RequestQueSingleTone instance;

    private RequestQueSingleTone(Context context) {

        this.context = context;
        requestQueue = getRequestQue();

    }
    public static synchronized RequestQueSingleTone getInstance(Context context) {

        if (instance == null) {

            instance = new RequestQueSingleTone(context.getApplicationContext());
        }

        return instance;

    }

    public RequestQueue getRequestQue() {

        if (requestQueue == null) {

            Cache cache = new DiskBasedCache(context.getCacheDir(), 1024 * 1024);
            Network network = new BasicNetwork(new HurlStack());

            requestQueue = new RequestQueue(cache, network);
            requestQueue.start();

        }

        return requestQueue;

    }

    public void addToRequestQue(StringRequest request) {

        requestQueue.add(request);
    }
}
