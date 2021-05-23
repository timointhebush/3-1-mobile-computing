package com.example.leejs_60171651_midexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    Button btn_prev, btn_next;
    ViewFlipper viewFlipper;
    TextView textView_nougat, textView_oreo, textView_pie, textView_androidQ, textView_androidR;
    ImageView imageView_bird;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater mInflater = getMenuInflater();
        if (v == imageView_bird) {
            mInflater.inflate(R.menu.menu1, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_doubleSize :
                imageView_bird.setScaleX(2);
                imageView_bird.setScaleY(2);
                return true;
            case R.id.item_ogSize:
                imageView_bird.setScaleX((float) 1);
                imageView_bird.setScaleY((float) 1);
                return true;
            default: return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("이준석 60171651 중간시험");

        btn_prev = (Button) findViewById(R.id.btn_prev);
        btn_next = (Button) findViewById(R.id.btn_next);

        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);

        textView_nougat = (TextView) findViewById(R.id.textView_nougat);
        textView_oreo = (TextView) findViewById(R.id.textView_oreo);
        textView_pie = (TextView) findViewById(R.id.textView_pie);
        textView_androidQ = (TextView) findViewById(R.id.textView_androidQ);
        textView_androidR = (TextView) findViewById(R.id.textView_androidR);

        imageView_bird = (ImageView) findViewById(R.id.imageView_bird);

        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.showPrevious();
            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.showNext();
            }
        });

        textView_nougat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), textView_nougat.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        textView_oreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), textView_oreo.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        textView_pie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), textView_pie.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        textView_androidQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), textView_androidQ.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        textView_androidR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), textView_androidR.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        registerForContextMenu(imageView_bird);



        imageView_bird.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });

    }
}