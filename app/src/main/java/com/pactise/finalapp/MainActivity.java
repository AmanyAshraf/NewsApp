package com.pactise.finalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private String sentCategory ,sentCountry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        sentCategory=getIntent().getStringExtra(Constants.CATEGORY_KEY);
        sentCountry=getIntent().getStringExtra(Constants.COUNTRY_KEY);

        loadData(sentCategory,sentCountry);

    }
    public void showListView(ArrayList<Article> articles){
        CustomAdapter adapter =new CustomAdapter(articles,this);
        ListView listView=findViewById(R.id.lv);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Uri link =Uri.parse(articles.get(position).getUrl());
            Intent intent =new Intent(Intent.ACTION_VIEW,link);
            startActivity(intent);
        });
    }
    private void loadData(String category,String country){
        ProgressBar progressBar=findViewById(R.id.pb);
        Retrofit retrofit =new Retrofit
                .Builder()
                .baseUrl("https://newsapi.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CallableInterface callable= retrofit.create(CallableInterface.class);
        Call<NewsModel>newsModelCall= callable.getData(category,country);
        newsModelCall.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                progressBar.setVisibility(View.GONE);
                NewsModel newsModel =response.body();
                ArrayList<Article> articles=newsModel.getArticles();
                showListView(articles);
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.d("json",t.getMessage());

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() ==R.id.item){
            loadData(sentCategory,sentCountry);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
