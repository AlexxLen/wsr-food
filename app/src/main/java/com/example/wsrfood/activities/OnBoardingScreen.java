package com.example.wsrfood.activities;

import static com.example.wsrfood.utilities.Constants.IS_NETWORK_AVAILABLE_EXTRA;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import com.example.wsrfood.R;
import com.example.wsrfood.databinding.ActivityOnBoardingScreenBinding;

public class OnBoardingScreen extends AppCompatActivity {
    private float startX;
    private boolean isNetworkAvailable;
    private ActivityOnBoardingScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnBoardingScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        isNetworkAvailable = getIntent().getExtras().getBoolean(IS_NETWORK_AVAILABLE_EXTRA);
        setupListeners();
    }

    private void setupListeners() {
        binding.linearLayout2.setOnClickListener(view -> {
            startActivity(OnBoardingScreen2.newIntent(this, isNetworkAvailable));
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                float endX = event.getX();
                if (startX > endX) {
                    startActivity(OnBoardingScreen2.newIntent(this, isNetworkAvailable));
                }
                break;
        }
        return false;
    }

    public static Intent newIntent(Context context, boolean isNetworkAvailable) {
        Intent intent = new Intent(context, OnBoardingScreen.class);
        intent.putExtra(IS_NETWORK_AVAILABLE_EXTRA, isNetworkAvailable);
        return intent;
    }
}