package com.example.android.desafio_fluxit.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.android.desafio_fluxit.R;
import com.example.android.desafio_fluxit.model.User;

import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {
    public static final String KEY_USERNAME = "username";
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        user = (User) bundle.getSerializable(KEY_USERNAME);


    }
}
