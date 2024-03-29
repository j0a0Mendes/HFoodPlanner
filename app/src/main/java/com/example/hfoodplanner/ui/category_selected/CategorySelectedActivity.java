package com.example.hfoodplanner.ui.category_selected;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

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

        getSupportActionBar().hide();

        if(extras != null){
            category = extras.getString("category");
        }


        binding = ActivityCategorySelectedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        ((TextView) findViewById(R.id.text_category_selected)).setText(category);

        ImageView backButton = findViewById(R.id.go_back_arrow);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
