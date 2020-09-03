package com.e.volley.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.e.volley.model.Post;
import com.e.volley.model.PostRepository;

public class MainActivityViewModel extends AndroidViewModel {

    PostRepository postRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        postRepository = new PostRepository(application);
    }


    public MutableLiveData<Post[]> getPosts() {

        MutableLiveData<Post[]>posts = postRepository.getPostsUsingSingleTone();

        return posts;
    }


}
