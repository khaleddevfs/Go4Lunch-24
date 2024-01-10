package com.example.go4lunch24.fragments;

import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.go4lunch24.databinding.FragmentListRestBinding;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class ListRestFragment extends BaseFragment {

    public static final String TAG = ListRestFragment.class.getSimpleName(); // ca sert a quoi le tag



    private FragmentListRestBinding binding;



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
    public void getLocationUser(Location locationUser) {

    }

    @Override
    protected void configureFragmentOnCreateView(View view) {

    }


}