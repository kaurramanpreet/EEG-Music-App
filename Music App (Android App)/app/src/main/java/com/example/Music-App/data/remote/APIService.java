package com.example.youtbe.data.remote;

import com.example.youtbe.data.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("/list")
    Call<List<Post>> getPost(@Query("age") String age,
                             @Query("lang") String lang,
                             @Query("genre") String genre);
}