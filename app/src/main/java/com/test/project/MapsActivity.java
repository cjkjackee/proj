package com.test.project;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        LatLng majie = new LatLng(24.800523, 120.990793);
        double X[] = {24.816296,24.800523,24.798279,24.802317,24.816158,24.808904,24.801572,24.801884,24.807304};
        double Y[] = {120.980127,120.990793,120.965273,120.969697,120.962112,120.955652,120.963418,120.962818,120.976490};
        String name[] = {"國立臺灣大學醫學院附設醫院新竹分院","馬偕紀念醫院新竹分院","國泰醫院新竹分院","南門綜合醫院","國軍新竹醫院","和平醫院","台灣省私立桃園仁愛之家附設新生醫院","新中興醫院","惠民醫院"};
        List<LatLng> hospital = new ArrayList<>();

        for (int i=0; i<X.length-1; i++){
            hospital.add( new LatLng(X[i],Y[i]));
        }

        Iterator it = hospital.iterator();
        int i = 0;
        while (it.hasNext()){
            mMap.addMarker(new MarkerOptions().position((LatLng) it.next()).title(name[i++]));
        }

        mMap.addMarker(new MarkerOptions().position(majie).title("馬偕紀念醫院新竹分院"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(majie,15));
    }
}
