package com.example.hfoodplanner.ui.calendar;

import static android.R.color.darker_gray;
import static android.R.color.holo_blue_light;
import static android.R.color.holo_green_light;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.hfoodplanner.MainActivity;
import com.example.hfoodplanner.R;
import com.example.hfoodplanner.databinding.FragmentCalendarBinding;
import com.example.hfoodplanner.ui.calendar.CalendarViewModel;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.util.ArrayList;

public class CalendarFragment extends Fragment {

    private FragmentCalendarBinding binding;

    private MainActivity activity;
    private int currentDayIndex = 0;

    public int upperIndexThreshold = 6;

    public ImageView previousArrow;
    public ImageView nextArrow;
    private ArrayList<Integer> weekDays;

    private String daySelected = "";

    private TextView tv_currentDay;

    private View root;

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CalendarViewModel dishCategoriesViewModel =
                new ViewModelProvider(this).get(CalendarViewModel.class);

        binding = FragmentCalendarBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        activity = (MainActivity)getActivity();

        //get weekdays info from main activity
        weekDays = activity.getWeekDays();

        Log.d("Week", String.valueOf(weekDays));

        //set the today's date
        currentDayIndex = 0;

        setUpWeekMechanism(root);

        previousArrow = root.findViewById(R.id.arrow_previous);
        previousArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goOnPreviousDay();
            }
        });

        nextArrow = root.findViewById(R.id.arrow_next);
        nextArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goOnNextDay();
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setUpWeekMechanism(View root) {
        //updating current selected day
        tv_currentDay = root.findViewById(R.id.current_year);
        tv_currentDay.setText(activity.getCurrentWeekDay());

        daySelected = activity.getCurrentWeekDay();
        //it will start on the first day, you will not go backwards no the week
        //removePreviousArrow();


        //set the week days of the month
        TextView tv_firstDay = root.findViewById(R.id.month_day_one);
        tv_firstDay.setText(weekDays.get(0).toString());
        TextView tv_secondDay = root.findViewById(R.id.month_day_two);
        tv_secondDay.setText(weekDays.get(1).toString());
        TextView tv_thirdDay = root.findViewById(R.id.month_day_three);
        tv_thirdDay.setText(weekDays.get(2).toString());
        TextView tv_forthDay = root.findViewById(R.id.month_day_four);
        tv_forthDay.setText(weekDays.get(3).toString());
        TextView tv_fifthDay = root.findViewById(R.id.month_day_five);
        tv_fifthDay.setText(weekDays.get(4).toString());
        TextView tv_sixthDay = root.findViewById(R.id.month_day_six);
        tv_sixthDay.setText(weekDays.get(5).toString());
        TextView tv_seventhDay = root.findViewById(R.id.month_day_seven);
        tv_seventhDay.setText(weekDays.get(6).toString());

        CardView cv_Mon = root.findViewById(R.id.day_one);
        CardView cv_Tue = root.findViewById(R.id.day_two);
        CardView cv_Wed = root.findViewById(R.id.day_three);
        CardView cv_Thu = root.findViewById(R.id.day_four);
        CardView cv_Fri = root.findViewById(R.id.day_five);
        CardView cv_Sat = root.findViewById(R.id.day_six);
        CardView cv_Sun = root.findViewById(R.id.day_seven);


        int blue = getResources().getColor(holo_blue_light);
        int gray = getResources().getColor(darker_gray);

        switch (activity.getCurrentWeekDay()){
            case "MONDAY":
                cv_Mon.setCardBackgroundColor(blue);
                break;
            case "TUESDAY":
                upperIndexThreshold = 5;
                cv_Mon.setCardBackgroundColor(gray);
                cv_Tue.setCardBackgroundColor(blue);
                break;
            case "WEDNESDAY":
                upperIndexThreshold = 4;
                cv_Mon.setCardBackgroundColor(gray);
                cv_Tue.setCardBackgroundColor(gray);
                cv_Wed.setCardBackgroundColor(blue);
                break;
            case "THURSDAY":
                upperIndexThreshold = 3;
                cv_Mon.setCardBackgroundColor(gray);
                cv_Tue.setCardBackgroundColor(gray);
                cv_Wed.setCardBackgroundColor(gray);
                cv_Thu.setCardBackgroundColor(blue);
                break;
            case "FRIDAY":
                upperIndexThreshold = 2;
                cv_Mon.setCardBackgroundColor(gray);
                cv_Tue.setCardBackgroundColor(gray);
                cv_Wed.setCardBackgroundColor(gray);
                cv_Thu.setCardBackgroundColor(gray);
                cv_Fri.setCardBackgroundColor(blue);
                break;
            case "SATURDAY":
                upperIndexThreshold = 1;
                cv_Mon.setCardBackgroundColor(gray);
                cv_Tue.setCardBackgroundColor(gray);
                cv_Wed.setCardBackgroundColor(gray);
                cv_Thu.setCardBackgroundColor(gray);
                cv_Fri.setCardBackgroundColor(gray);
                cv_Sat.setCardBackgroundColor(blue);
                break;
            case "SUNDAY":
                cv_Mon.setCardBackgroundColor(gray);
                cv_Tue.setCardBackgroundColor(gray);
                cv_Wed.setCardBackgroundColor(gray);
                cv_Thu.setCardBackgroundColor(gray);
                cv_Fri.setCardBackgroundColor(gray);
                cv_Sat.setCardBackgroundColor(gray);
                cv_Sun.setCardBackgroundColor(blue);
                break;
        }
    }


    public void goOnNextDay(){
        if(currentDayIndex +1 <= upperIndexThreshold){        //substitute with final threshold variables
            currentDayIndex += 1;

            if(currentDayIndex == upperIndexThreshold){       //substitute with final threshold variables
                removeNextArrow();
            }else{
                addPreviousArrow();
            }

            daySelected = getAdjacentDay(daySelected,true);
            updateSelectedNextDay();

            tv_currentDay.setText(daySelected);

        }
    }

    public void goOnPreviousDay(){
        if(currentDayIndex -1 >= 0){        //substitute with final threshold variables   //can go 7 days before the current one
            currentDayIndex -= 1;            //substitute with final threshold variables   //decreases the index

            if(currentDayIndex == 0){
                removePreviousArrow();
            }else{
                addNextArrow();
            }

            daySelected = getAdjacentDay(daySelected,false);
            updateSelectedPreviousDay();

            tv_currentDay.setText(daySelected);

        }
    }

    public void updateSelectedNextDay(){
        CardView cv_Mon = root.findViewById(R.id.day_one);
        CardView cv_Tue = root.findViewById(R.id.day_two);
        CardView cv_Wed = root.findViewById(R.id.day_three);
        CardView cv_Thu = root.findViewById(R.id.day_four);
        CardView cv_Fri = root.findViewById(R.id.day_five);
        CardView cv_Sat = root.findViewById(R.id.day_six);
        CardView cv_Sun = root.findViewById(R.id.day_seven);

        int blue = getResources().getColor(holo_blue_light);
        int white = getResources().getColor(android.R.color.white);

        switch (daySelected){
            case "MONDAY":
                cv_Mon.setCardBackgroundColor(blue);
                break;
            case "TUESDAY":
                cv_Mon.setCardBackgroundColor(white);
                cv_Tue.setCardBackgroundColor(blue);
                break;
            case "WEDNESDAY":
                cv_Tue.setCardBackgroundColor(white);
                cv_Wed.setCardBackgroundColor(blue);
                break;
            case "THURSDAY":
                cv_Wed.setCardBackgroundColor(white);
                cv_Thu.setCardBackgroundColor(blue);
                break;
            case "FRIDAY":
                cv_Thu.setCardBackgroundColor(white);
                cv_Fri.setCardBackgroundColor(blue);
                break;
            case "SATURDAY":
                cv_Fri.setCardBackgroundColor(white);
                cv_Sat.setCardBackgroundColor(blue);
                break;
            case "SUNDAY":
                cv_Sat.setCardBackgroundColor(white);
                cv_Sun.setCardBackgroundColor(blue);
                break;
        }
    }

    public void updateSelectedPreviousDay(){
        CardView cv_Mon = root.findViewById(R.id.day_one);
        CardView cv_Tue = root.findViewById(R.id.day_two);
        CardView cv_Wed = root.findViewById(R.id.day_three);
        CardView cv_Thu = root.findViewById(R.id.day_four);
        CardView cv_Fri = root.findViewById(R.id.day_five);
        CardView cv_Sat = root.findViewById(R.id.day_six);
        CardView cv_Sun = root.findViewById(R.id.day_seven);

        int blue = getResources().getColor(holo_blue_light);
        int white = getResources().getColor(android.R.color.white);

        switch (daySelected){
            case "MONDAY":
                cv_Mon.setCardBackgroundColor(blue);
                cv_Tue.setCardBackgroundColor(white);
                break;
            case "TUESDAY":
                cv_Wed.setCardBackgroundColor(white);
                cv_Tue.setCardBackgroundColor(blue);
                break;
            case "WEDNESDAY":
                cv_Thu.setCardBackgroundColor(white);
                cv_Wed.setCardBackgroundColor(blue);
                break;
            case "THURSDAY":
                cv_Fri.setCardBackgroundColor(white);
                cv_Thu.setCardBackgroundColor(blue);
                break;
            case "FRIDAY":
                cv_Sat.setCardBackgroundColor(white);
                cv_Fri.setCardBackgroundColor(blue);
                break;
            case "SATURDAY":
                cv_Sun.setCardBackgroundColor(white);
                cv_Sat.setCardBackgroundColor(blue);
                break;
            case "SUNDAY":
                cv_Sun.setCardBackgroundColor(blue);
                break;
        }
    }


    public static String getAdjacentDay(String currentDay, boolean getNextDay) {
        String[] daysOfWeek = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};

        // Convert the input to uppercase to handle case variations
        String currentDayUpperCase = currentDay.toUpperCase();

        // Find the index of the current day in the array
        int currentDayIndex = -1;
        for (int i = 0; i < daysOfWeek.length; i++) {
            if (daysOfWeek[i].equals(currentDayUpperCase)) {
                currentDayIndex = i;
                break;
            }
        }

        // Check if the input day is valid
        if (currentDayIndex == -1) {
            return "Invalid day";
        }

        // Calculate the index of the adjacent day using modular arithmetic
        int adjacentDayIndex;
        if (getNextDay) {
            adjacentDayIndex = (currentDayIndex + 1) % 7;
        } else {
            adjacentDayIndex = (currentDayIndex - 1 + 7) % 7;
        }

        // Return the name of the adjacent day
        return daysOfWeek[adjacentDayIndex];
    }

    public void removePreviousArrow(){
        previousArrow.setVisibility(View.INVISIBLE);
        previousArrow.setClickable(false);
    }

    public void addPreviousArrow(){
        previousArrow.setVisibility(View.VISIBLE);
        previousArrow.setClickable(true);
    }

    public void removeNextArrow(){
        nextArrow.setVisibility(View.INVISIBLE);
        nextArrow.setClickable(false);
    }

    public void addNextArrow(){
        nextArrow.setVisibility(View.VISIBLE);
        nextArrow.setClickable(true);
    }



}
