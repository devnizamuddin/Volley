package com.e.volley.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.e.volley.R;
import com.e.volley.adapter.PostAdapter;
import com.e.volley.model.Post;
import com.e.volley.viewmodel.Factory;
import com.e.volley.viewmodel.MainActivityViewModel;
import com.e.volley.viewmodel.MyViewModelFactory;

public class MainActivity extends AppCompatActivity {


    RecyclerView recycler_view;
    MainActivityViewModel viewModel;
    Post[] posts = new Post[]{};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler_view = findViewById(R.id.recycler_view);
        Log.d("Nizam", "oncreate");
        viewModel = new ViewModelProvider(this, new Factory(getApplication())).get(MainActivityViewModel.class);
       // viewModel = new ViewModelProvider(this, new MyViewModelFactory(getApplication())).get(MainActivityViewModel.class);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);

        recycler_view.setLayoutManager(layoutManager);
        final PostAdapter adapter = new PostAdapter(posts);
        recycler_view.setAdapter(adapter);

        viewModel.getPosts().observe(this, new Observer<Post[]>() {
            @Override
            public void onChanged(Post[] posts) {

                adapter.updatePosts(posts);
            }
        });
    }
}