package com.example.hw_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView text1, text2;
    Switch switchAgree;
    RadioGroup rGroup;
    RadioButton radioArray[] = new RadioButton[3];
    ImageView imgBurger;
    Button btnQuit, btnRerun;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("좋아하는 햄버거 브랜드 선택");

        text1 = (TextView) findViewById(R.id.Text1);
        switchAgree = (Switch) findViewById(R.id.switchAgree);

        text2 = (TextView) findViewById(R.id.Text2);
        rGroup = (RadioGroup) findViewById(R.id.Rgroup);
        radioArray[0] = (RadioButton) findViewById(R.id.RdoMac);
        radioArray[1] = (RadioButton) findViewById(R.id.RdoKing);
        radioArray[2] = (RadioButton) findViewById(R.id.RdoMom);

        imgBurger = (ImageView) findViewById(R.id.ImgBurger);
        btnQuit = (Button) findViewById(R.id.BtnQuit);
        btnRerun = (Button) findViewById(R.id.BtnRerun);

        switchAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switchAgree.isChecked() == true) {
                    text2.setVisibility(View.VISIBLE);
                    rGroup.setVisibility(View.VISIBLE);
                    imgBurger.setVisibility(View.VISIBLE);
                    btnQuit.setVisibility(View.VISIBLE);
                    btnRerun.setVisibility(View.VISIBLE);
                } else {
                    text2.setVisibility(View.INVISIBLE);
                    rGroup.setVisibility(View.INVISIBLE);
                    imgBurger.setVisibility(View.INVISIBLE);
                    btnQuit.setVisibility(View.INVISIBLE);
                    btnRerun.setVisibility(View.INVISIBLE);
                }
            }
        });

        final int draw[] = {R.drawable.macdonalds, R.drawable.burgerking, R.drawable.momstouch};

        for (int i=0; i<radioArray.length; i++) {
            final int index;
            index = i;
            radioArray[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgBurger.setImageResource(draw[index]);
                }
            });
        }

        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnRerun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text2.setVisibility(View.INVISIBLE);
                rGroup.setVisibility(View.INVISIBLE);
                imgBurger.setVisibility(View.INVISIBLE);
                btnQuit.setVisibility(View.INVISIBLE);
                btnRerun.setVisibility(View.INVISIBLE);

                rGroup.clearCheck();
                switchAgree.setChecked(false);
            }
        });

    }
}