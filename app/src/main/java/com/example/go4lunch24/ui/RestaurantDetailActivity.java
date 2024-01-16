package com.example.go4lunch24.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.HttpException;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.go4lunch24.R;
import com.example.go4lunch24.adapters.WorkMateDetailAdapter;
import com.example.go4lunch24.databinding.ActivityRestaurantDetailBinding;
import com.example.go4lunch24.factory.Go4LunchFactory;
import com.example.go4lunch24.injections.Injection;
import com.example.go4lunch24.models.Restaurant;
import com.example.go4lunch24.viewModel.RestaurantDetailViewModel;

public class RestaurantDetailActivity extends AppCompatActivity {


    private final String TAG = RestaurantDetailActivity.class.getSimpleName();

    public static final String RESTAURANT_PLACE_ID = "placeId";
    private Restaurant restaurant;
    private ActivityRestaurantDetailBinding binding;
    private RestaurantDetailViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initListener();
        initViewModel();
        getRestaurantPlaceId();
    }

    private void initView() {
        binding = ActivityRestaurantDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    private void initListener() {
        binding.restaurantDetailCallButton.setOnClickListener(v -> openDialer(restaurant.getPhoneNumber()));
        binding.restaurantDetailLikeButton.setOnClickListener(v -> viewModel.updateRestaurantLiked(restaurant));
        binding.restaurantDetailWebsiteButton.setOnClickListener(v -> openWebSite(restaurant.getWebSite()));
        binding.restaurantDetailFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewModel.updateRestaurantPiked(restaurant);

            }
        });
    }

    private void initViewModel() {
        Go4LunchFactory viewModelFactory = Injection.provideViewModelFactory();
        viewModel = new ViewModelProvider(this, viewModelFactory).get(RestaurantDetailViewModel.class);
    }

    private void getRestaurantPlaceId() {
        if (getIntent().hasExtra(RESTAURANT_PLACE_ID)) {
            String placeId = getIntent().getStringExtra(RESTAURANT_PLACE_ID);
            getRestaurantDetail(placeId);
            initWorkMatesList();
        }
    }

    private void getRestaurantDetail(String placeId) {
        viewModel.getRestaurantDetail(placeId).observe(this, this::displayInfoRestaurant);
    }


    //j'ai modifie cette classe pour resourdre le probleme d'affichage des photos des restaurants
    private void displayInfoRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;

        // Ajouter un log pour afficher le nom du restaurant
        Log.d("RestaurantDetail", "Nom du restaurant : " + restaurant.getName());
        binding.restaurantDetailName.setText(restaurant.getName());

        // Ajouter un log pour afficher l'adresse du restaurant
        Log.d("RestaurantDetail", "Adresse du restaurant : " + restaurant.getAddress());
        binding.restaurantDetailAddress.setText(restaurant.getAddress());

        if (restaurant.getPhotoReference() != null) {
            // Ajouter un log pour indiquer le chargement de la photo
            Log.d("RestaurantDetail", "Chargement de la photo du restaurant...");

            Glide.with(RestaurantDetailActivity.this)
                    .load(restaurant.getPhotoReference())
                    .apply(RequestOptions.centerCropTransform())
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            // Ajouter un log en cas d'échec du chargement de la photo
                            if (e != null && e.getRootCauses() != null && e.getRootCauses().size() > 0) {
                                for (Throwable cause : e.getRootCauses()) {
                                    if (cause instanceof HttpException) {
                                        int statusCode = ((HttpException) cause).getStatusCode();
                                        if (statusCode == 403) {
                                            Log.e("RestaurantDetail", "Erreur 403 - Accès interdit lors du chargement de la photo : " + cause.getMessage());
                                            // Autres actions à entreprendre en cas d'erreur 403, si nécessaire
                                        }
                                    }
                                }
                            }
                            return false;
                        }


            @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            // Ajouter un log lorsque la photo est chargée avec succès
                            Log.d("RestaurantDetail", "Chargement de la photo réussi");
                            return false;
                        }
                    })
                    .into(binding.restaurantDetailPicture);
        }


        // Ajouter un log pour indiquer le début de la récupération des informations du restaurant
        Log.d("RestaurantDetail", "Récupération des informations du restaurant...");
        viewModel.fetchInfoRestaurant(restaurant);

        // Ajouter un log pour indiquer le début de la récupération des informations sur les amis qui aiment le restaurant
        Log.d("RestaurantDetail", "Récupération des informations sur les amis qui aiment le restaurant...");
        viewModel.fetchWorkMateLike(restaurant);
    }



    private void initWorkMatesList() {
        WorkMateDetailAdapter workMateDetailAdapter = new WorkMateDetailAdapter();
        binding.workMatesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.workMatesRecyclerView.setAdapter(workMateDetailAdapter);

        viewModel.mWorkMateList.observe(this, workMateDetailAdapter::setWorkMateList);
        viewModel.isRestaurantLiked.observe(this, this::changeLikeStatus);
        viewModel.isRestaurantPicked.observe(this, this::changeChoiceStatus);
    }

    /**
     * Display if the restaurant is liked by the workmate
     */
    private void changeLikeStatus(Boolean isLiked) {
        int resourceId = (isLiked) ? R.drawable.ic_baseline_star_rate_24 : R.drawable.ic_baseline_menu_star_border_24;
        Drawable drawable = ContextCompat.getDrawable(this, resourceId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), 60);
        binding.restaurantDetailLikeButton.setCompoundDrawables(null, drawable, null, null);
        binding.restaurantDetailLikeButton.setBackgroundResource(resourceId);
    }

    private void changeChoiceStatus(Boolean isSelected) {
        int resourceId = (isSelected) ? R.drawable.ic_baseline_check_circle_24_ok : R.drawable.ic_baseline_uncheck_circle_24;
        int color = (isSelected) ? Color.GREEN : Color.GRAY;
        Drawable drawable = ContextCompat.getDrawable(this, resourceId);
        drawable.mutate().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        binding.restaurantDetailFab.setImageDrawable(drawable);
    }

    /**
     * Open the website
     *
     * @param webSite : string : url to open
     */
    private void openWebSite(String webSite) {
        if (webSite != null) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webSite));
            startActivity(intent);
        } else {
            Toast.makeText(RestaurantDetailActivity.this, getString(R.string.text_no_web_site), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Open the dialer
     *
     * @param phone : string : phone number to display
     */
    private void openDialer(String phone) {
        if ((phone != null) && (phone.trim().length() > 0)) {
            Intent lIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(phone)));
            startActivity(lIntent);
        } else {
            Toast.makeText(RestaurantDetailActivity.this, getString(R.string.text_no_phone_number), Toast.LENGTH_SHORT).show();
        }
    }
}