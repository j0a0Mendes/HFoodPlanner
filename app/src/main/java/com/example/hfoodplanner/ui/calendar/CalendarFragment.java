package com.example.hfoodplanner.ui.calendar;

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
    public static final int lowerIndexThreshold = -7;
    public static final int upperIndexThreshold = 7;

    public ImageView previousArrow;
    public ImageView nextArrow;
    private ArrayList<Integer> weekDays;

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CalendarViewModel dishCategoriesViewModel =
                new ViewModelProvider(this).get(CalendarViewModel.class);

        binding = FragmentCalendarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

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
        TextView tv_currentDay = root.findViewById(R.id.current_year);
        tv_currentDay.setText(activity.getCurrentWeekDay());


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


        int green = getResources().getColor(holo_green_light);

        switch (activity.getCurrentWeekDay()){
            case "MONDAY":
                cv_Mon.setCardBackgroundColor(green);
                break;
            case "TUESDAY":
                cv_Tue.setCardBackgroundColor(green);
                break;
            case "WEDNESDAY":
                cv_Wed.setCardBackgroundColor(green);
                break;
            case "THURSDAY":
                cv_Thu.setCardBackgroundColor(green);
                break;
            case "FRIDAY":
                cv_Fri.setCardBackgroundColor(green);
                break;
            case "SATURDAY":
                cv_Sat.setCardBackgroundColor(green);
                break;
            case "SUNDAY":
                cv_Sun.setCardBackgroundColor(green);
                break;
        }
    }


    public void goOnNextDay(){
        if(currentDayIndex +1 <= 1){        //substitute with final threshold variables
            currentDayIndex += 1;

            if(currentDayIndex == 1){       //substitute with final threshold variables
                removeNextArrow();
            }else{
                addPreviousArrow();
            }
        }
    }

    public void goOnPreviousDay(){
        if(currentDayIndex -1 >= -1){        //substitute with final threshold variables   //can go 7 days before the current one
            currentDayIndex -= 1;            //substitute with final threshold variables   //decreases the index

            if(currentDayIndex == -1){
                removePreviousArrow();
            }else{
                addNextArrow();
            }

        }
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
