package com.example.go4lunch24.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.go4lunch24.databinding.SettingLayoutBinding;
import com.example.go4lunch24.factory.Go4LunchFactory;
import com.example.go4lunch24.injections.Injection;
import com.example.go4lunch24.viewModel.SettingViewModel;

public class SettingActivity extends AppCompatActivity {

    private SettingLayoutBinding binding;
    private SettingViewModel settingViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

        settingViewModel = obtainViewModel();

        handleSettings();
    }

    private void initView() {
        binding = SettingLayoutBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    private void handleSettings() {
        settingViewModel.configureSaveDataRepo(getApplicationContext());
        boolean statusNotification = settingViewModel.getStatusNotification(getApplicationContext());
        binding.notificationSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            settingViewModel.notificationStateChanged(isChecked, getApplicationContext());
        });

        binding.notificationSwitch.setChecked(statusNotification);
    }

    private SettingViewModel obtainViewModel() {
        Go4LunchFactory viewModelFactory = Injection.provideViewModelFactory();
        return new ViewModelProvider(this, viewModelFactory).get(SettingViewModel.class);
    }

}
