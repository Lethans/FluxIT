package com.example.android.desafio_fluxit.utils.service;

import com.example.android.desafio_fluxit.model.Results;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {

    @GET(" ")
    Call<Results> getUsers(@Query("results") String results,
                             @Query("page") Integer currentPage,
                             @Query("seed") String seed);
}
