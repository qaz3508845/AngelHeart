package com.example;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.location.Location;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.util.Log;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener ,LocationListener {

    double latitude; //儲存取得緯度
    double longitude; //儲存取得經度
    LatLng sydney;
    Marker marker;

    GoogleApiClient mGoogleApiClient; //Google Play services Location API物件
    LocationRequest locationRequest;


    private static final int REQUEST_LOCATION = 2;
    private GoogleMap mMap;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createLocationRequest();

        //依API範例建立mGoogleApiClient物件 此時還未連線到Google伺服器
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    //自定義位置變動方法
    private void createLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(5000); //更新間隔5000毫秒
        locationRequest.setFastestInterval(2000); //最短間隔2000毫秒
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
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
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
//            return;
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION);

        } else {
            setupMyLocation();
        }


        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(22, 120);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.getUiSettings().setZoomControlsEnabled(true); //啟用縮放控制鍵
    }

    @SuppressLint("MissingPermission")
    private void setupMyLocation() {
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                //透過位置服務,取得目前裝置位置
                return false;
            }
        });
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //使用者允許權限
                    setupMyLocation();
                } else {
                    //使用者拒絕授權,停用MyLocation功能
                }
                break;
        }
    }

    //當mGoogleApiClient連上線時執行
    @SuppressLint("MissingPermission")
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        @SuppressLint("MissingPermission") Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if(location != null){
            latitude =location.getLatitude();
            longitude =location.getLongitude();
            sydney = new LatLng(latitude, longitude);
            marker = mMap.addMarker(new MarkerOptions().position(sydney).title("我目前的位置(經度,緯度)").snippet(latitude+","+longitude));
            marker.showInfoWindow();
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                    sydney,15));

            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,locationRequest,this);

        }
    }

    @Override
    public void onConnectionSuspended(int i) {
//
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
    }

    @Override
    protected void onStop() {
//        marker.remove();
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onPause() {
        marker.remove();
        super.onPause();
    }

    @Override
    public void onLocationChanged(Location location) {
        marker.remove();

        latitude =location.getLatitude();
        longitude = location.getLongitude();

        sydney = new LatLng(latitude, longitude);
        marker = mMap.addMarker(new MarkerOptions().position(sydney).title("我目前的位置(經度,緯度)").snippet(latitude+","+longitude));
        marker.showInfoWindow();

        Log.e("LOCATION",latitude+","+longitude);

//        mMap.animateCamera(CameraUpdateFactory.newLatLng(sydney));

    }
}
