package com.example.go4lunch24.fragments;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;


import com.example.go4lunch24.R;
import com.example.go4lunch24.databinding.FragmentMapsBinding;
import com.example.go4lunch24.factory.Go4LunchFactory;
import com.example.go4lunch24.injections.Injection;
import com.example.go4lunch24.models.Location;
import com.example.go4lunch24.viewModel.MapsViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;

import pub.devrel.easypermissions.EasyPermissions;

public class MapsFragment extends BaseFragment implements OnMapReadyCallback {

    private FragmentMapsBinding binding;

    private MapsViewModel viewModel;

    private GoogleMap googleMap;

    private MapView mapView;

    private Location location;


    private FusedLocationProviderClient fusedLocationClient;
    private static final int REQUEST_CODE_PERMISSION = 123;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMapsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        checkAndRequestLocationPermission();

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity());

        configureFragmentOnCreateView(view);
        configureMapView(savedInstanceState);
        return view;
    }

    private void checkAndRequestLocationPermission() {
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

    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (googleMap != null) {
            String STATE_KEY_MAP_CAMERA = "keymap";
            outState.putParcelable(STATE_KEY_MAP_CAMERA, googleMap.getCameraPosition());
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;

        updateLocationUI();


    }

    private void updateLocationUI() {
        // Vérifier si la permission ACCESS_FINE_LOCATION est accordée
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // La permission est déjà accordée, vous pouvez maintenant demander la localisation
            getLastLocation();
        } else {
            // Demander la permission à l'utilisateur
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_PERMISSION);
        }
    }

    // Gérer la réponse de l'utilisateur à la demande de permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // L'utilisateur a accordé la permission, vous pouvez maintenant demander la localisation
                getLastLocation();
            } else {
                // L'utilisateur a refusé la permission, gérer cela en conséquence
                // Vous pourriez afficher un message à l'utilisateur indiquant que la localisation est nécessaire pour une fonctionnalité spécifique, etc.
            }
        }
    }


    private void getLocationUser() {
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(requireActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();

                            // Utiliser le géocodage inverse pour obtenir l'adresse
                            Geocoder geocoder = new Geocoder(requireContext(), Locale.getDefault());

                            try {
                                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                                if (addresses != null && addresses.size() > 0) {
                                    Address address = addresses.get(0);
                                    String userAddress = address.getAddressLine(0);

                                    // Ajouter un marqueur sur la carte à la localisation de l'utilisateur
                                    LatLng userLocation = new LatLng(latitude, longitude);
                                    googleMap.addMarker(new MarkerOptions().position(userLocation).title("Utilisateur").snippet(userAddress));
                                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(userLocation));
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                })
                .addOnFailureListener( requireActivity(), new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Gestion des erreurs lors de la récupération de la localisation
                        e.printStackTrace();
                    }
                });

    }
    @SuppressLint("PotentialBehaviorOverride")
    private void onMarkerClick() {
        googleMap.setOnMarkerClickListener( marker -> {
            String placeId = (String) marker.getTag();

            return false;
        });
    }


    @Override
    public void getLocationUser(Location locationUser) {
        location = locationUser;
        viewModel.fetchWorkMateGoing()


    }

    @Override
    protected void configureFragmentOnCreateView(View view) {
        viewModel = obtainViewModel();



    }

    private MapsViewModel obtainViewModel() {
        Go4LunchFactory viewModelFactory = Injection.provideViewModelFactory();
        return new ViewModelProvider(requireActivity(), viewModelFactory).get(MapsViewModel.class);
    }



}

