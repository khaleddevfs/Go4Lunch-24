package com.example.go4lunch24.repositories;



import static com.example.go4lunch24.services.GooglePlacesService.BASE_URL_GOOGLE;
import static com.example.go4lunch24.services.GooglePlacesService.KEY_GOOGLE;
import static com.example.go4lunch24.services.GooglePlacesService.MAX_WIDTH_GOOGLE;
import static com.example.go4lunch24.services.GooglePlacesService.PHOTO_REF_GOOGLE;

import android.location.Location;
import android.util.Log;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.go4lunch24.R;
import com.example.go4lunch24.databinding.FragmentMapsBinding;
import com.example.go4lunch24.models.DetailsRestaurantResponseApi;
import com.example.go4lunch24.models.Restaurant;
import com.example.go4lunch24.models.RestaurantApi;
import com.example.go4lunch24.models.RestaurantResponseApi;
import com.example.go4lunch24.services.GooglePlacesService;
import com.example.go4lunch24.services.Retrofit;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RestaurantRepository {

    public static final String TAG = RestaurantRepository.class.getSimpleName();
    private double longUser = 0.0;
    private double latUser = 0.0;
    private final String key = "AIzaSyBxVtUrubQztvtLB5H-pAS6WYXGQz2WyMs";

    public final MutableLiveData<List<Restaurant>> restaurantListMutableLiveData = new MutableLiveData<>();

    private MutableLiveData<Restaurant> restaurantLiveData = new MutableLiveData<>();

    private final List<Restaurant> restaurants = new ArrayList<>();

    private static volatile RestaurantRepository INSTANCE;

    public RestaurantRepository(){

    }

    public static RestaurantRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RestaurantRepository();
        }
        return INSTANCE;
    }

    // Get the Restaurant List from Google

    public LiveData<List<Restaurant>> getGoogleRestaurantList(double latitude, double longitude){
        longUser = longitude;
        latUser = latitude;

        GooglePlacesService googlePlacesService = Retrofit.getClient().create(GooglePlacesService.class);
        String type = "restaurant";
        int proximityRadius = 800;
        Call<RestaurantResponseApi> responseApiCall = googlePlacesService.getNeatByPlaces(key, type, latitude + "," + longitude, proximityRadius);

        responseApiCall.enqueue(new Callback<RestaurantResponseApi>() {

            @Override
            public void onResponse(@NotNull Call<RestaurantResponseApi> call, @NotNull Response<RestaurantResponseApi> response) {
                for (RestaurantApi restaurantApi : response.body().getResults()) {
                    Restaurant restaurant = createRestaurant(restaurantApi);
                    restaurants.add(restaurant);
                    restaurantLiveData.setValue(restaurant);
                    restaurantListMutableLiveData.postValue(restaurants);
                }
            }

            @Override
            public void onFailure(@NotNull Call<RestaurantResponseApi> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure getGoogleRestaurantDetail");
            }
        });

         return restaurantListMutableLiveData;}


    public LiveData<Restaurant> getGoogleRestaurantDetail(String placeId) {
        Log.d("tagii", "getGoogleRestaurantDetail "+placeId);
        GooglePlacesService googlePlacesService = Retrofit.getClient().create(GooglePlacesService.class);
        String fields = "name,address_components,adr_address,formatted_address,formatted_phone_number,geometry,icon,id,international_phone_number,rating,website,utc_offset,opening_hours,photo,vicinity,place_id";
        Call<DetailsRestaurantResponseApi> detailsRestaurantResponseApiCall = googlePlacesService.getRestaurantDetail(key, placeId, fields);

        detailsRestaurantResponseApiCall.enqueue(new Callback<DetailsRestaurantResponseApi>() {

            @Override
            public void onResponse(@NotNull Call<DetailsRestaurantResponseApi> call, @NotNull Response<DetailsRestaurantResponseApi> response) {
                Log.d(TAG, "onResponse getGoogleRestaurantDetail");

                Log.d("tagii", "success: "+response.isSuccessful());
                Log.d("tagii", "getResults: "+response.body().getResult());
                if (response.isSuccessful() && response.body().getResult() != null) {
                    Log.d("tagii", "if");
                    Restaurant restaurant = createRestaurant(response.body().getResult());
                    Log.d("tagii", "restaurant id: "+restaurant.getRestaurantID());
                    restaurants.add(restaurant);
                    restaurantLiveData.setValue(restaurant);
                    restaurantListMutableLiveData.postValue(restaurants);
                } else {
                    Log.d("tagii", "else");
                }
            }

            @Override
            public void onFailure(@NotNull Call<DetailsRestaurantResponseApi> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure getGoogleRestaurantDetail");
            }
        });
        return restaurantLiveData;
    }

    private Restaurant createRestaurant(RestaurantApi result) {
        String uid = result.getPlaceId();
        Log.d("tagii", "uid "+uid);
        String name = result.getName();
        double latitude = result.getGeometry().getLocation().getLat();
        double longitude = result.getGeometry().getLocation().getLng();
        String photo = (result.getPhotos() != null) ? getPhoto(result.getPhotos().get(0).getPhotoReference()) : null;
        String address = result.getVicinity();
        int distance = (int) getDistance(latitude, longitude);
        boolean openNow = result.getOpeningHours() != null && result.getOpeningHours().getOpenNow();
        String webSite = result.getWebsite();
        String phoneNumber = result.getPhoneNumber();
        float rating = result.getRating();
        return new Restaurant(uid, name, latitude, longitude, address, openNow, distance, photo, rating, phoneNumber, webSite);
    }

    private String getPhoto(String photoReference) {
        return BASE_URL_GOOGLE + PHOTO_REF_GOOGLE + photoReference + MAX_WIDTH_GOOGLE + 400 + KEY_GOOGLE + key;
    }


    private float getDistance(double latRestaurant, double longRestaurant) {
        float[] distance = new float[1];
        Location.distanceBetween(latUser, longUser, latRestaurant, longRestaurant, distance);
        return distance[0];

    }

}

