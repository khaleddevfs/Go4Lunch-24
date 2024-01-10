package com.example.go4lunch24.ui;



import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.go4lunch24.databinding.ActivityLoginBinding;
import com.example.go4lunch24.viewModel.LoginViewModel;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding loginBinding;

    private LoginViewModel viewModel;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        initView();

        initListener();

    }




    private void initView() {
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = loginBinding.getRoot();
        setContentView(view);
    }

    private void initListener() {
        loginBinding.emailLoginButton.setOnClickListener(v -> viewModel.startLoginActivityEmail(LoginActivity.this));
        loginBinding.gmailLoginButton.setOnClickListener(v -> viewModel.startLoginActivityGoogle(LoginActivity.this));
        loginBinding.twitterLoginButton.setOnClickListener(v -> viewModel.startLoginActivityTwitter(LoginActivity.this));
    }






    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}