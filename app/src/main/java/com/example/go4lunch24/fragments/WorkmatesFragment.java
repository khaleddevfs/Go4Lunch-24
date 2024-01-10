package com.example.go4lunch24.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.go4lunch24.databinding.FragmentWorkmatesBinding;

import java.util.ArrayList;
import java.util.List;


public class WorkmatesFragment extends Fragment {

    private FragmentWorkmatesBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWorkmatesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        configureFragmentOnCreateView();
        return view;
    }

    protected void configureFragmentOnCreateView() {


    }







}