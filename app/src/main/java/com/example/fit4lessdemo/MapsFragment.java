package com.example.fit4lessdemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class MapsFragment extends Fragment {
    private Toast mToast = null;
    public static String location = "";
    String date = "";
    private OnMapReadyCallback callback = new OnMapReadyCallback() {


        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng overlea = new LatLng(43.705876, -79.345018);
            LatLng eglington = new LatLng(43.726484, -79.299765);
            LatLng lawrenceW = new LatLng(43.716624, -79.447209);
            LatLng queensway = new LatLng(43.619619, -79.524876);
            LatLng eastmall = new LatLng(43.637587,-79.557395);
            LatLng kennedy = new LatLng(43.767431, -79.280682);
            LatLng kingston = new LatLng(43.770240, -79.183724);
            LatLng lawrenceE = new LatLng(43.760231, -79.228115);
            LatLng toronto = new LatLng(43.6532, -79.3832);
            googleMap.addMarker(new MarkerOptions().position(overlea).title("45 Overlea Blvd"));
            googleMap.addMarker(new MarkerOptions().position(eglington).title("1880 Eglington Ave"));
            googleMap.addMarker(new MarkerOptions().position(lawrenceW).title("700 Lawrence Ave W"));
            googleMap.addMarker(new MarkerOptions().position(queensway).title("1255 Queensway"));
            googleMap.addMarker(new MarkerOptions().position(eastmall).title("302 The East Mall"));
            googleMap.addMarker(new MarkerOptions().position(kennedy).title("1911 Kennedy Rd"));
            googleMap.addMarker(new MarkerOptions().position(kingston).title("4525 Kingston Rd"));
            googleMap.addMarker(new MarkerOptions().position(lawrenceE).title("3434 Lawrence Ave E"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(toronto, 11));

            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    if (mToast != null) mToast.cancel();
                    mToast = Toast.makeText(getContext(), "You have selected: "+ marker.getTitle() ,Toast.LENGTH_LONG);
                    mToast.show();

                    location = marker.getTitle();
                    return false;
                }
            });
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
    public static String getLocation(){
        return location;
    }
}