package com.example.android.desafio_fluxit.view.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.desafio_fluxit.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapFragment extends Fragment {

    public static final String KEY_LATITUDE = "latitud";
    public static final String KEY_LONGITUDE = "longitud";


    public MapFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        setMap();
        return rootView;
    }


    private void setMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.mapFragment);

        assert mapFragment != null;
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                mMap.clear();

                Bundle arguments = getArguments();
                assert arguments != null;
                double latitude = arguments.getDouble(KEY_LATITUDE);
                double longitude = arguments.getDouble(KEY_LONGITUDE);

                CameraPosition googlePlex = CameraPosition.builder()
                        .target(new LatLng(latitude, longitude))
                        .zoom(15)
                        .bearing(0)
                        .tilt(45)
                        .build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 3500, null);
                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(latitude, longitude)));

            }
        });
    }

}
