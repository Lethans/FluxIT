package com.example.android.desafio_fluxit.dao;

import com.example.android.desafio_fluxit.model.Results;
import com.example.android.desafio_fluxit.utils.ResultListener;
import com.example.android.desafio_fluxit.utils.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserDao {

    public static final String BASE_URL = "https://randomuser.me/api/";
    private Retrofit retrofit;
    private UserService userService;

    public UserDao() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userService = retrofit.create(UserService.class);
    }

        public void getUsersDao(final ResultListener<Results> controllerListener, String results, Integer currentPage, String seed){
            Call<Results> call = userService.getUsers(results,currentPage,seed);
            call.enqueue(new Callback<Results>() {
                @Override
                public void onResponse(Call<Results> call, Response<Results> response) {
                    Results users = response.body();
                    controllerListener.onFinish(users);
                }
                @Override
                public void onFailure(Call<Results> call, Throwable t) {
                    String error = t.getMessage();
                    System.out.println("Ocurrio un error: " + error);
                    t.printStackTrace();
                }
            });
        }
    }


