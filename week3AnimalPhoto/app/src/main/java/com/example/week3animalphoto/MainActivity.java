package com.example.week3animalphoto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView text1, text2;
    CheckBox checkStart;
    RadioGroup rGroup;
    RadioButton radioMongu, radioGureum, radioTeolmongu;
    Button btnOk;
    ImageView imgPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("애완동물 사진 보기");
        text1 = (TextView) findViewById(R.id.Text1);
        checkStart = (CheckBox) findViewById(R.id.CheckStart);

        text2 = (TextView) findViewById(R.id.Text2);
        rGroup = (RadioGroup) findViewById(R.id.Rgroup);
        radioMongu = (RadioButton) findViewById(R.id.RadioMongu);
        radioGureum = (RadioButton) findViewById(R.id.RadioGureum);
        radioTeolmongu = (RadioButton) findViewById(R.id.RadioTeolmongu);

        btnOk = (Button) findViewById(R.id.BtnOK);
        imgPet = (ImageView) findViewById(R.id.ImgPet);

        checkStart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkStart.isChecked() == true) {
                    text2.setVisibility(View.VISIBLE);
                    rGroup.setVisibility(View.VISIBLE);
                    btnOk.setVisibility(View.VISIBLE);
                    imgPet.setVisibility(View.VISIBLE);
                } else {
                    text2.setVisibility(View.INVISIBLE);
                    rGroup.setVisibility(View.INVISIBLE);
                    btnOk.setVisibility(View.INVISIBLE);
                    imgPet.setVisibility(View.INVISIBLE);
                }
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (rGroup.getCheckedRadioButtonId()) {
                    case R.id.RadioMongu:
                        imgPet.setImageResource(R.drawable.mongu); break;
                    case R.id.RadioGureum:
                        imgPet.setImageResource(R.drawable.gureum); break;
                    case R.id.RadioTeolmongu:
                        imgPet.setImageResource(R.drawable.teolmongu); break;
                    default:
                        Toast.makeText(getApplicationContext(), "동물 먼저 선택하세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}