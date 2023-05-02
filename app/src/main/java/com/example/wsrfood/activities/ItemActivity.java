package com.example.wsrfood.activities;

import static com.example.wsrfood.utilities.Constants.DISH_EXTRA;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.wsrfood.activities.main.MainActivity;
import com.example.wsrfood.databinding.ActivityItemBinding;
import com.example.wsrfood.models.Dish;

public class ItemActivity extends AppCompatActivity {
    private ActivityItemBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Dish dish = getIntent().getSerializableExtra(DISH_EXTRA, Dish.class);
        Glide.with(this).load(dish.getIcon()).into(binding.imageViewDishIcon);
        binding.textViewDishName.setText(dish.getName());
        binding.textViewDishPrice.setText("N" + dish.getPrice());
        binding.buttonAddToCart.setOnClickListener(view -> {
            startActivity(MainActivity.newIntent(this));
        });
        binding.buttonGoBack.setOnClickListener(view -> {
            startActivity(MainActivity.newIntent(this));
        });
    }

    public static Intent newIntent(Context context, Dish dish) {
        Intent intent = new Intent(context, ItemActivity.class);
        intent.putExtra(DISH_EXTRA, dish);
        return intent;
    }
}