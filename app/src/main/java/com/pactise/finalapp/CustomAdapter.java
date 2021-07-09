package com.pactise.finalapp;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private ArrayList<Article> articles;
    private Activity activity;

    public CustomAdapter(ArrayList<Article> articles, Activity activity) {
        this.articles = articles;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return articles.size();
    }

    @Override
    public Object getItem(int position) {
        return articles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null)
            view = activity.getLayoutInflater().inflate(R.layout.list_item, parent, false);
        TextView textView = view.findViewById(R.id.tv);
        ImageView imageView = view.findViewById(R.id.iv);
        textView.setText(articles.get(position).getTitle().substring(0, 35) + "...");
        String downloadedImage = articles.get(position).getUrlToImage();
        if (downloadedImage != null && !downloadedImage.isEmpty()) {
            Picasso
                    .get()
                    .load(articles.get(position).getUrlToImage())
                    .placeholder(R.drawable.ic_loop)
                    .into(imageView);
        } else
            imageView.setImageResource(R.drawable.ic_broken_image);
        return view;
    }
}
