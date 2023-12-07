package com.example.hfoodplanner.ui.dish;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hfoodplanner.databinding.ActivityCategorySelectedBinding;

public class DishActivity extends AppCompatActivity {

    private ActivityCategorySelectedBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCategorySelectedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}
