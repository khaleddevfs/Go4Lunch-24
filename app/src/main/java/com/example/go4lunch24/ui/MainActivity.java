package com.example.go4lunch24.ui;





import android.Manifest;
import android.annotation.SuppressLint;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;




import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;

import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.go4lunch24.R;
import com.example.go4lunch24.databinding.ActivityMainBinding;

import com.example.go4lunch24.factory.Go4LunchFactory;
import com.example.go4lunch24.fragments.ListRestFragment;
import com.example.go4lunch24.fragments.MapsFragment;
import com.example.go4lunch24.fragments.WorkmatesFragment;
import com.example.go4lunch24.injections.Injection;
import com.example.go4lunch24.viewModel.MainViewModel;

import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding binding;


    private MainViewModel viewModel;


    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    private int AUTOCOMPLETE_REQUEST_CODE = 1;





    private String selectedRestaurantId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        viewModel = obtainViewModel();
        configureUI();

        // changing Action bar Title

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(R.string.hungry);
        }

      Places.initialize(getApplicationContext(), getString(R.string.MAPS_API_KEY));


        // connecting MapsFragment with activity

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_frame_layout, new MapsFragment())
                .commit();
    }

    private void requestLocationPermissions() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed because of this and that")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }


    private void initView() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

   private MainViewModel obtainViewModel() {
        Go4LunchFactory viewModelFactory = Injection.provideViewModelFactory();
        return new ViewModelProvider(this, viewModelFactory).get(MainViewModel.class);
    }

    private void configureUI() {
        this.configureToolbar();
        this.configureBottomView();
        this.configureDrawerLayout();
        this.configureNavigationView();
    }

    //----- TOOLBAR -----
    private void configureToolbar() {
        setSupportActionBar(binding.mainToolbar);
    }



    //----- Bottom Navigation -----

    private void configureBottomView() {
        binding.mainBottomNavigationView.setOnNavigationItemReselectedListener(item -> onBottomNavigation(item.getItemId()));
    }



    @SuppressLint("WrongConstant")
    public boolean onBottomNavigation(int itemId) {
      /*  Fragment selectedFragment = null;

        switch (itemId) {
            case R.id.bottom_navigation_menu_map_button:
                selectedFragment = new MapsFragment();
                break;
            case R.id.bottom_navigation_menu_list_button:
                selectedFragment = new ListRestFragment();
                break;
            case R.id.bottom_navigation_menu_workMates_button:
                selectedFragment = new WorkmatesFragment();
                break;
        }

        if (selectedFragment != null) {
            MainActivity.this
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_frame_layout, selectedFragment)
                    .commit();
        }

       */
        return true;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_search_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.search_menu) {
            startAutocompleteActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //----- DRAWER -----

    private void configureDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.mainDrawerLayout, binding.mainToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorWhite));
        binding.mainDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }


    private void configureNavigationView() {
        binding.mainNavigationView.setNavigationItemSelectedListener(this);
        ImageView imageUser = binding.mainNavigationView.getHeaderView(0).findViewById(R.id.user_navigation_header_image_view_picture);
        TextView userNameTextView = binding.mainNavigationView.getHeaderView(0).findViewById(R.id.user_navigation_header_name_text);
        TextView emailTextview = binding.mainNavigationView.getHeaderView(0).findViewById(R.id.user_navigation_header_email_text);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            userNameTextView.setText(user.getDisplayName());
            emailTextview.setText(user.getEmail());
            Glide.with(this)
                    .load(user.getPhotoUrl())
                    .transform(new CircleCrop())
                    .into(imageUser);
        }
    }

    public void onBackPressed() {
        if (this.binding.mainDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.binding.mainDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    /*    int id = item.getItemId();
        switch (id) {
            case R.id.drawer_menu_lunch_button:
                if (selectedRestaurantId != null){
                    Intent intent = new Intent(MainActivity.this, RestaurantDetailsActivity.class);
                    intent.putExtra(RESTAURANT_PLACE_ID, selectedRestaurantId);
                    startActivity(intent);
                }
                break;

            case R.id.drawer_menu_settings_button:
                startActivity(new Intent(this, SettingsActivity.class));
                Log.d("setting activity ok", "setting on");
                break;

            case R.id.drawer_menu_logout_button:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
        this.binding.mainDrawerLayout.closeDrawer(GravityCompat.START);
        */
        return true;
    }

    // AutoComplete

    private void startAutocompleteActivity() {
        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.TYPES, Place.Field.LAT_LNG);
        Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fields)
                .setCountry("FR")
                .setTypeFilter(TypeFilter.ESTABLISHMENT)
                .build(this);
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
        Log.d("auto complete search", "auto complete ok");
    }


    // Drawer





    private void configureNotifications() {

        this.configureNotificationIntent();
        this.enableNotification();
    }



    private void configureNotificationIntent() {
    } // A completer

    private void enableNotification() {
    } // A completer







    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.main_frame_layout);
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place requestPlace = Autocomplete.getPlaceFromIntent(data);
                if (currentFragment instanceof MapsFragment) {
                    moveToRestaurantLocation((MapsFragment) currentFragment, requestPlace);
                } else {
                    displayDetailRestaurant(requestPlace);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void moveToRestaurantLocation(MapsFragment mapsFragment, Place requestPlace) {
        if (requestPlace.getTypes() != null) {
            for (Place.Type type : requestPlace.getTypes()) {
                if (type == Place.Type.RESTAURANT) {
                    mapsFragment.displayRestaurant(requestPlace.getLatLng(), requestPlace.getName(), requestPlace.getId());
                }
            }
        }
    }

    private void displayDetailRestaurant(Place requestPlace) {

    }


}