package com.example.hfoodplanner.ui.category_selected;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.hfoodplanner.R;
import com.example.hfoodplanner.databinding.ActivityCategorySelectedBinding;
import com.example.hfoodplanner.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CategorySelectedActivity extends AppCompatActivity {

    private String category;
    private ActivityCategorySelectedBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();

        if(extras != null){
            category = extras.getString("category");
        }

        binding = ActivityCategorySelectedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ((TextView) findViewById(R.id.text_category_selected)).setText(category);
    }
}
