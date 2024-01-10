package com.example.go4lunch24.ui;



import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


import com.example.go4lunch24.databinding.ActivityLoginBinding;
import com.example.go4lunch24.viewModel.LoginViewModel;




public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding loginBinding;

    private LoginViewModel viewModel;

    public static final int RC_SIGN_IN = 100;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        initView();


        // Obtention de l'instance du ViewModel
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);


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


    private void checkSessionUser() {
        //viewModel.updateCurrentUser();
        if (viewModel.isCurrentUserLogged()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                //viewModel.updateCurrentUser();
                Intent loginIntent = new Intent(this, MainActivity.class);
                startActivity(loginIntent);
            } else {
                Toast.makeText(this, "marche pas ", Toast.LENGTH_SHORT).show();
            }
        }

    }
}