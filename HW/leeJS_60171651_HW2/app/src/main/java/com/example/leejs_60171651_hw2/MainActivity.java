package com.example.leejs_60171651_hw2;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    Chronometer chronometer1;
    RadioGroup rdoGroup;
    RadioButton rdoCal, rdoTime;
    DatePicker datePicker;
    TimePicker timePicker;
    TextView tvYear, tvMonth, tvDay, tvHour, tvMinute;
    int selectYear, selectMonth, selectDay;
    AutoCompleteTextView autoCompleteTv;
    boolean isReserving;
    String[] items = { "parents", "friend", "girlfriend", "boyfriend", "brother", "sister"};


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("이준석 과제2 식당 예약");

        isReserving = false;

        chronometer1 = (Chronometer) findViewById(R.id.chronometer1);

        rdoGroup = (RadioGroup) findViewById(R.id.rdoGroup);
        rdoCal = (RadioButton) findViewById(R.id.rdoCal);
        rdoTime = (RadioButton) findViewById(R.id.rdoTime);

        datePicker = (DatePicker) findViewById(R.id.datePicker);

        timePicker = (TimePicker) findViewById(R.id.timePicker);

        tvYear = (TextView) findViewById(R.id.tvYear);
        tvMonth = (TextView) findViewById(R.id.tvMonth);
        tvDay = (TextView) findViewById(R.id.tvDay);
        tvHour = (TextView) findViewById(R.id.tvHour);
        tvMinute = (TextView) findViewById(R.id.tvMinute);

        autoCompleteTv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTv);

        rdoGroup.setVisibility(View.INVISIBLE);
        datePicker.setVisibility(View.INVISIBLE);
        timePicker.setVisibility(View.INVISIBLE);

        chronometer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rdoGroup.getVisibility() == View.INVISIBLE) {
                    rdoGroup.setVisibility(View.VISIBLE);
                }
                else {
                    chronometer1.setBase(SystemClock.elapsedRealtime());
                    chronometer1.start();
                    chronometer1.setTextColor(Color.RED);
                    isReserving = true;
                }
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, items);
        autoCompleteTv.setAdapter(adapter);

        rdoCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.setVisibility(View.VISIBLE);
                timePicker.setVisibility(View.INVISIBLE);
            }
        });

        rdoTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.setVisibility(View.INVISIBLE);
                timePicker.setVisibility(View.VISIBLE);
            }
        });

        tvYear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (isReserving == true) {
                    chronometer1.stop();
                    chronometer1.setTextColor(Color.BLUE);

                    tvYear.setText(Integer.toString(selectYear));
                    tvMonth.setText(Integer.toString(selectMonth));
                    tvDay.setText(Integer.toString(selectDay));

                    tvHour.setText(Integer.toString(timePicker.getCurrentHour()));
                    tvMinute.setText(Integer.toString(timePicker.getCurrentMinute()));

                    isReserving = false;
                } else {
                    rdoGroup.setVisibility(View.INVISIBLE);
                    datePicker.setVisibility(View.INVISIBLE);
                    timePicker.setVisibility(View.INVISIBLE);
                }
                return false;
            }
        });

        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                selectYear = year;
                selectMonth = monthOfYear+1;
                selectDay = dayOfMonth;
            }
        });

    }
}