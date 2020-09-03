package com.e.volley.model;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class PostRepository {

    private Application application;
    private MutableLiveData<Post[]> postsLiveData;
    public static String URL = "https://jsonplaceholder.typicode.com/posts";

    public PostRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<Post[]> getPosts() {

        if (postsLiveData == null) {
            postsLiveData = new MutableLiveData<>();
        }
        RequestQueue requestQueue = Volley.newRequestQueue(application);

        StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Nizam", "response successful");
                Post[] posts = new GsonBuilder().create().fromJson(response, Post[].class);
                postsLiveData.setValue(posts);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Nizam", error.getLocalizedMessage());

            }
        });

        requestQueue.add(request);
        return postsLiveData;


    }

    public MutableLiveData<Post[]> getPostsUseCustomRequestQue() {

        if (postsLiveData == null) {
            postsLiveData = new MutableLiveData<>();
        }

        Cache cache = new DiskBasedCache(application.getCacheDir(), 1024 * 1024);
        Network network = new BasicNetwork(new HurlStack());

        final RequestQueue requestQueue = new RequestQueue(cache, network);

        Log.d("Nizam", "custom requestQue");

        StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Nizam", "response successful");
                Post[] posts = new GsonBuilder().create().fromJson(response, Post[].class);
                postsLiveData.setValue(posts);
                requestQueue.stop();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Nizam", error.getLocalizedMessage());

            }
        });

        requestQueue.start();
        requestQueue.add(request);
        return postsLiveData;


    }

    public MutableLiveData<Post[]> getPostsUsingSingleTone() {

        if (postsLiveData == null) {
            postsLiveData = new MutableLiveData<>();
        }



        StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Nizam", "response successful");
                Post[] posts = new GsonBuilder().create().fromJson(response, Post[].class);
                postsLiveData.setValue(posts);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Nizam", error.getLocalizedMessage());

            }
        });


        RequestQueSingleTone.getInstance(application).addToRequestQue(request);
        return postsLiveData;


    }


}
