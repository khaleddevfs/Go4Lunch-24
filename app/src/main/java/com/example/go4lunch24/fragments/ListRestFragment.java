package com.example.go4lunch24.fragments;

import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.go4lunch24.adapters.RestaurantAdapter;
import com.example.go4lunch24.databinding.FragmentListRestBinding;
import com.example.go4lunch24.factory.Go4LunchFactory;
import com.example.go4lunch24.injections.Injection;
import com.example.go4lunch24.models.Restaurant;
import com.example.go4lunch24.viewModel.RestaurantViewModel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class ListRestFragment extends BaseFragment {

    public static final String TAG = ListRestFragment.class.getSimpleName();

    private FragmentListRestBinding binding;

    private RestaurantAdapter adapter;

    private RestaurantViewModel viewModel;




    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentListRestBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        configureFragmentOnCreateView(view);
        this.configureFragmentOnCreateView(view);
        return view;
    }

    @Override
    public void getLocationUser(Location location) {
        if (isAdded()) {
            fetchRestaurantList(location);
        }

    }

    @Override
    protected void configureFragmentOnCreateView(View view) {
        viewModel = obtainViewModel();
        initRecyclerView();
    }


    private RestaurantViewModel obtainViewModel() {
        Go4LunchFactory viewModelFactory = Injection.provideViewModelFactory();
        return new ViewModelProvider(requireActivity(), viewModelFactory).get(RestaurantViewModel.class);
    }

    private void initRecyclerView() {
        adapter = new RestaurantAdapter();
        binding.restaurantRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.restaurantRecyclerView.setAdapter(adapter);
    }

    private void fetchRestaurantList(Location location) {
        viewModel.fetchWorkMatesGoing();
        viewModel.workMatesIdMutableLiveData
                .observe(getViewLifecycleOwner(), workMateIds ->
                        viewModel.getRestaurants()
                                .observe(getViewLifecycleOwner(), list ->
                                        changeAndNotifyAdapterChange(list, workMateIds )));
    }

    private void changeAndNotifyAdapterChange(List<Restaurant> restaurants, List<String> workMateIds) {
        adapter.setRestaurantList(restaurants, workMateIds);
    }




}