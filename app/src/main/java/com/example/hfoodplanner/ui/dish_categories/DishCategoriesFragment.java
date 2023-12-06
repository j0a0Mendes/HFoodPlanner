package com.example.hfoodplanner.ui.dish_categories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.hfoodplanner.databinding.FragmentDishCategoriesBinding;

public class DishCategoriesFragment extends Fragment{

    private FragmentDishCategoriesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DishCategoriesViewModel dishCategoriesViewModel =
                new ViewModelProvider(this).get(DishCategoriesViewModel.class);

        binding = FragmentDishCategoriesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
