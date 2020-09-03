package com.e.volley.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.e.volley.R;
import com.e.volley.databinding.SinglePostLayoutBinding;
import com.e.volley.model.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    Post[] posts;

    public PostAdapter(Post[] posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        SinglePostLayoutBinding singlePostLayoutBinding = DataBindingUtil.inflate
                (LayoutInflater.from(parent.getContext()), R.layout.single_post_layout, parent, false);


        return new PostViewHolder(singlePostLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {

        Post post = posts[position];
        holder.postLayoutBinding.setPost(post);

    }

    @Override
    public int getItemCount() {
        return posts.length;
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        SinglePostLayoutBinding postLayoutBinding;

        public PostViewHolder(@NonNull SinglePostLayoutBinding postLayoutBinding) {
            super(postLayoutBinding.getRoot());

            this.postLayoutBinding = postLayoutBinding;
        }
    }

    public void updatePosts(Post[] posts){

        this.posts=posts;
        notifyDataSetChanged();

    }
}
