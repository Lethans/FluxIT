package com.example.android.desafio_fluxit.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.desafio_fluxit.R;
import com.example.android.desafio_fluxit.model.User;
import com.example.android.desafio_fluxit.view.fragment.MapFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class DetailsActivity extends AppCompatActivity {
    public static final String KEY_USERNAME = "username";

    @BindView(R.id.details_profile_pic)
    CircleImageView profilePic;
    @BindView(R.id.details_nameAndLastName)
    TextView textViewFullName;
    @BindView(R.id.details_age)
    TextView textViewAge;
    @BindView(R.id.details_email)
    TextView textViewEmail;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        setData();
        setGoogleMaps();

    }

    public void setData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            user = (User) bundle.getSerializable(KEY_USERNAME);
            Glide.with(this)
                    .load(user.getPicture().getLarge())
                    .into(profilePic);
            String fullname = " " + user.getName().getFirst() + " " + user.getName().getLast();
            String age = " " + user.getDob().getAge();
            String email = " " + user.getEmail();

            textViewFullName.setText(fullname);
            textViewAge.setText(age);
            textViewEmail.setText(email);
        }
    }

    private void setGoogleMaps() {
        double latitude = user.getLocation().getCoordinates().getLatitude();
        double longitude = user.getLocation().getCoordinates().getLongitude();
        MapFragment mapsFragment = new MapFragment();
        Bundle bundle = new Bundle();
        bundle.putDouble(MapFragment.KEY_LATITUDE, latitude);
        bundle.putDouble(MapFragment.KEY_LONGITUDE, longitude);
        mapsFragment.setArguments(bundle);
        FragmentManager fragmentManager = DetailsActivity.this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mapContainer, mapsFragment).
                commit();
    }


}
