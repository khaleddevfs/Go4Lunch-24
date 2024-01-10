package com.example.go4lunch24.fragments;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;


import com.example.go4lunch24.R;
import com.example.go4lunch24.databinding.FragmentMapsBinding;
import com.example.go4lunch24.viewModel.MapsViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class MapsFragment extends BaseFragment implements OnMapReadyCallback, EasyPermissions.PermissionCallbacks {

    private FragmentMapsBinding binding;

    private MapsViewModel viewModel;

    private GoogleMap googleMap;

    private MapView mapView;

    private Location userLocation;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMapsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        configureFragmentOnCreateView(view);
        configureMapView(savedInstanceState);
        return view;
    }

    private void configureMapView(Bundle savedInstanceState) {
        mapView = binding.mapView;
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (googleMap != null) {
            String STATE_KEY_MAP_CAMERA = "keymap";
            outState.putParcelable(STATE_KEY_MAP_CAMERA, googleMap.getCameraPosition());
        }
    }

    @Override
        public void onMapReady(GoogleMap googleMap) {
            this.googleMap = googleMap;
            googleMap.setIndoorEnabled(false);
            updateLocationUI();
            googleMap.setOnCameraMoveListener(this::onMarkerClick);
        }

    private void updateLocationUI() {
        try {
            if (userLocation !=null) {
                googleMap.setMyLocationEnabled(true);
                googleMap.getUiSettings().setMyLocationButtonEnabled(true);
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(userLocation.getLatitude(), userLocation.getLongitude()), 16));
            }
        } catch (SecurityException e) {
            Log.e("Exception:", e.getMessage());
        }

        Log.d("location update", "update UI");

    }
    @SuppressLint("PotentialBehaviorOverride")
    private void onMarkerClick() {
        googleMap.setOnMarkerClickListener( marker -> {
            String placeId = (String) marker.getTag();

            return false;
        });
    }



  /*  @Override
    public void getLocationUser(Location locationUser) {
        userLocation = locationUser;
        viewModel.fetchWorkMatesGoing();
        viewModel.workMatesIdMutableLiveData.observe(getViewLifecycleOwner(), workMatesIds -> {
            Log.d("userloca", "location ok" + locationUser);
            viewModel.getRestaurantList(locationUser.getLatitude(), locationUser.getLongitude())
                    .observe(getViewLifecycleOwner(), restaurants -> {
                        Log.d("userloca", "location ok" + restaurants.size());
                        setMapMarkers(restaurants, workMatesIds);

                        if (googleMap != null) {
                            googleMap.animateCamera(CameraUpdateFactory
                                    .newLatLngZoom(new LatLng(locationUser.getLatitude(), locationUser.getLongitude()), 16));
                        }
                    });
        });
    }

   */


 /* @Override
    public void getLocationUser(Location locationUser) {
        userLocation = locationUser;
        viewModel.fetchWorkMatesGoing();
        viewModel.workMatesIdMutableLiveData
                .observe(getViewLifecycleOwner(), workMatesIds -> {
                    Log.d("userloca", "location ok" + locationUser);
                    viewModel.getRestaurantList(locationUser.getLatitude(), locationUser.getLongitude())
                            .observe(getViewLifecycleOwner(), restaurants -> {
                                Log.d("userloca", "location ok" + restaurants.size());

                                setMapMarkers(restaurants, workMatesIds);
                            });

                });
        if (googleMap != null) {
            googleMap.animateCamera(CameraUpdateFactory
                    .newLatLngZoom(new LatLng(locationUser.getLatitude(), locationUser.getLongitude()), 16));
        }
    }

  */


    @Override
    public void getLocationUser(Location location) {

    }






    

   /* private void setMapMarkers(List<Restaurant> restaurants, List<String> workMatesIds) {
        if (googleMap != null) {
            googleMap.clear();

            for (Restaurant restaurant : restaurants) {
                int iconResource = (workMatesIds.contains(restaurant.getRestaurantID())) ?
                        R.drawable.icon_location_selected : R.drawable.icon_location_normal;
                LatLng positionRestaurant = new LatLng(restaurant.getLatitude(), restaurant.getLongitude());
                googleMap.addMarker(new MarkerOptions()
                        .position(positionRestaurant)
                        .icon(BitmapDescriptorFactory.fromResource(iconResource))
                        .title(restaurant.getName()))
                        .setTag(restaurant.getRestaurantID());
                onMarkerClick();
            }
        }
    }

    */

    @Override
    protected void configureFragmentOnCreateView(View view) {

    }




}

