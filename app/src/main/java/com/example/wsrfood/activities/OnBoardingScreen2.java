package com.example.wsrfood.activities;

import static com.example.wsrfood.utilities.Constants.IS_NETWORK_AVAILABLE_EXTRA;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import com.example.wsrfood.activities.signIn.SignInActivity;
import com.example.wsrfood.activities.signUp.SignUpActivity;
import com.example.wsrfood.databinding.ActivityOnBoardingScreen2Binding;

public class OnBoardingScreen2 extends AppCompatActivity {
    private boolean isNetworkAvailable;
    private float endX;
    private ActivityOnBoardingScreen2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnBoardingScreen2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupListeners();
        isNetworkAvailable = getIntent().getExtras().getBoolean(IS_NETWORK_AVAILABLE_EXTRA);
    }

    private void setupListeners() {
        binding.linearLayout.setOnClickListener(view -> {
            startActivity(OnBoardingScreen.newIntent(this, isNetworkAvailable));

        });
        binding.buttonSignIn.setOnClickListener(view -> {
            startActivity(SignInActivity.newIntent(this));
        });
        binding.buttonSignUp.setOnClickListener(view -> {
            startActivity(SignUpActivity.newIntent(this));
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                endX = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                float endX = event.getX();
                if (this.endX < endX) {
                    startActivity(OnBoardingScreen.newIntent(this, isNetworkAvailable));
                }
                break;
        }
        return false;
    }

    public static Intent newIntent(Context context, boolean isNetworkAvailable) {
        Intent intent = new Intent(context, OnBoardingScreen2.class);
        intent.putExtra(IS_NETWORK_AVAILABLE_EXTRA, isNetworkAvailable);
        return intent;
    }
}