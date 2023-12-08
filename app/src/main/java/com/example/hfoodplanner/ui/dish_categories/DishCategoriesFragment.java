package com.example.hfoodplanner.ui.dish_categories;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.hfoodplanner.R;
import com.example.hfoodplanner.databinding.FragmentDishCategoriesBinding;
import com.example.hfoodplanner.ui.category_selected.CategorySelectedActivity;

public class DishCategoriesFragment extends Fragment implements View.OnClickListener{

    private FragmentDishCategoriesBinding binding;
    private CardView meat, fish, vegetarian, vegan, snacks, deserts;

    public DishCategoriesFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DishCategoriesViewModel dishCategoriesViewModel =
                new ViewModelProvider(this).get(DishCategoriesViewModel.class);

        binding = FragmentDishCategoriesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        meat = root.findViewById(R.id.meat_category);
        fish = root.findViewById(R.id.fish_category);
        vegetarian = root.findViewById(R.id.vegetarian_category);
        vegan = root.findViewById(R.id.vegan_category);
        snacks = root.findViewById(R.id.snacks_category);
        deserts = root.findViewById(R.id.deserts_category);

        meat.setOnClickListener(this);
        fish.setOnClickListener(this);
        vegetarian.setOnClickListener(this);
        vegan.setOnClickListener(this);
        snacks.setOnClickListener(this);
        deserts.setOnClickListener(this);


        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), CategorySelectedActivity.class);

        String category_selected = "";

        if(view.getId() == R.id.meat_category){
            category_selected = "Meat";
        } else if (view.getId() == R.id.fish_category) {
            category_selected = "Fish";
        }else if (view.getId() == R.id.vegetarian_category) {
            category_selected = "Vegetarian";
        }else if (view.getId() == R.id.vegan_category) {
            category_selected = "Vegan";
        }else if (view.getId() == R.id.snacks_category) {
            category_selected = "Snacks";
        }else if (view.getId() == R.id.deserts_category) {
            category_selected = "Deserts";
        }

        intent.putExtra("category", category_selected);
        startActivity(intent);
        }
    }

