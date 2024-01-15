package com.example.hfoodplanner;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.hfoodplanner.databinding.ActivityMainBinding;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static ArrayList<Integer> weekDays;

    DayOfWeek currentDayOfWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_calendar, R.id.navigation_dish_categories, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        //create list of current week days
        weekDays = new ArrayList<Integer>();

        //Get days of the week
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setCurrentDayAndWeekDays();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setCurrentDayAndWeekDays() {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Find the most recent Monday
        LocalDate monday = currentDate.with(DayOfWeek.MONDAY);

        // Get the current day of the week
        currentDayOfWeek = currentDate.getDayOfWeek();
        //Log.d("DATE_TIME", "Current Day of the Week: " + currentDayOfWeek);

        // Get the current day of the month
        int currentDayOfMonth = currentDate.getDayOfMonth();
        //Log.d("DATE_TIME", "Current Day of the Month: " + currentDayOfMonth);

        // Get the days from the past Monday until the next Sunday
        for (int i = 0; i <= DayOfWeek.SUNDAY.getValue() - DayOfWeek.MONDAY.getValue(); i++) {
            LocalDate dayOfWeek = monday.plusDays(i);
            int dayOfMonth = dayOfWeek.getDayOfMonth();
            //Log.d("DATE_TIME", "Day " + (i + 1) + ": " + dayOfWeek.getDayOfWeek() + ", Day of the Month: " + dayOfMonth);
            weekDays.add(dayOfMonth);
        }
    }

    public ArrayList<Integer> getWeekDays(){
        return weekDays;
    }

    public String getCurrentWeekDay(){ return currentDayOfWeek.toString();}

}