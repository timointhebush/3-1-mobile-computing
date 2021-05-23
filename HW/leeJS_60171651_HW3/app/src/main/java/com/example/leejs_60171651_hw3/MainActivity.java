package com.example.leejs_60171651_hw3;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RelativeLayout baseLayout;
    ImageView imgView;
    EditText ET_angle;
    float angle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("이준석 과제3 강아지 친구들");

        baseLayout = (RelativeLayout) findViewById(R.id.baseLayout);
        imgView = (ImageView) findViewById(R.id.imgView);
        ET_angle = (EditText) findViewById(R.id.ET_angle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.itm_gureum:
                imgView.setImageResource(R.drawable.gureum);
                item.setChecked(true);
                return true;
            case R.id.itm_mongu:
                imgView.setImageResource(R.drawable.mongu);
                item.setChecked(true);
                return true;
            case R.id.itm_mimi:
                imgView.setImageResource(R.drawable.mimi);
                item.setChecked(true);
                return true;
            case R.id.itm_rotate:
                if (TextUtils.isEmpty(ET_angle.getText())) {
                    Toast.makeText(this.getApplicationContext(), "각도를 입력하세요", Toast.LENGTH_SHORT).show();
                    return false;
                } else {
                    angle = Float.parseFloat(ET_angle.getText().toString());
                    imgView.setRotation(angle);
                    return true;
                }
            default: return false;
        }
    }
}