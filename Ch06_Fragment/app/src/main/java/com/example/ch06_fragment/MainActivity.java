package com.example.ch06_fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    Fragment1 fragment1;
    Fragment2 fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // XML에서 생성해서 가져오는 방식
        fragment1 = (Fragment1) getSupportFragmentManager().findFragmentById(R.id.fragment1);

        //자바코드에서 직접 생성하는 방식
       fragment2 = new Fragment2();
    }

    public void onFragmentChanged(int index) { //R.id.container는 메인 액티비티의 아이디
        if (index == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
        } else if (index == 2) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
        }
    }
}