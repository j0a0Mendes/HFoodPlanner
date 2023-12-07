package com.example.hfoodplanner.ui.login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.example.hfoodplanner.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}
