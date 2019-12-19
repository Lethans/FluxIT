package com.example.android.desafio_fluxit.controller;

import com.example.android.desafio_fluxit.dao.UserDao;
import com.example.android.desafio_fluxit.model.Results;
import com.example.android.desafio_fluxit.utils.ResultListener;

public class UserController {

    private static final String RESULTS = "20";

    private UserDao userDao;
    private Integer currentPage = 1;
    private String seed = "";
    private Boolean isLoading = false;


    public UserController() {
        this.userDao = new UserDao();
    }

    public void getUsersController(final ResultListener<Results> listenerDeLaVista) {
        if (!isLoading) {
            isLoading = true;
            userDao.getUsersDao(new ResultListener<Results>() {
                @Override
                public void onFinish(Results result) {
                    seed = result.getInfo().getSeed();
                    currentPage++;
                    listenerDeLaVista.onFinish(result);
                    isLoading = false;
                }
            }, RESULTS, currentPage, seed);
        }
    }
}
