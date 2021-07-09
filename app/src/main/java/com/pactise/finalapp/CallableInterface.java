package com.pactise.finalapp;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CallableInterface {
    @GET("/v2/top-headlines?apiKey=c980bbf28f424a7caf27111c0a63706a")
    Call<NewsModel> getData(@Query("category") String cat,@Query("country") String con);
}
