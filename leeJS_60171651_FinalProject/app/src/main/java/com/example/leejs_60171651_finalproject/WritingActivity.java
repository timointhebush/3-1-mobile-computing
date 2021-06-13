package com.example.leejs_60171651_finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class WritingActivity extends AppCompatActivity {

    ViewFlipper vFlipper;
    Button btn_return, btn_date, btn_save, btn_writing, btn_reading, btn_company;
    TextView tv_genre, tv_content;
    EditText et_title, et_director, et_actor, et_review;
    DatePicker datePicker1;
    String fileName, review, company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);

        Intent intent = getIntent();
        String genre = intent.getStringExtra("Genre");

        tv_genre = (TextView) findViewById(R.id.tv_genre);
        tv_genre.setText(genre);

        et_title = (EditText) findViewById(R.id.et_title);
        et_director = (EditText) findViewById(R.id.et_director);
        et_actor = (EditText) findViewById(R.id.et_actor);
        et_review = (EditText) findViewById(R.id.et_review);

        btn_date = (Button) findViewById(R.id.btn_date);

        Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                fileName = year + "_" + (month+1) + "_" + dayOfMonth + ".txt";
            }
        }, cYear, cMonth, cDay);

        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        btn_company = (Button) findViewById(R.id.btn_company);
        registerForContextMenu(btn_company);

        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream outFs = openFileOutput(fileName, Context.MODE_PRIVATE);
                    review = "영화 제목 : " + et_title.getText().toString() + "\n"
                            + "장르 : " + genre + "\n"
                            + "감독 : " + et_director.getText().toString() + "\n"
                            + "배우 : " + et_actor.getText().toString() + "\n"
                            + "동행 : " + company + "\n"
                            + "후기 \n" + et_review.getText().toString();
                    outFs.write(review.getBytes());
                    outFs.close();
                    et_title.setText("");
                    et_director.setText("");
                    et_actor.setText("");
                    et_review.setText("");
                    Toast.makeText(getApplicationContext(), fileName+"이 저장됨", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        btn_writing = (Button) findViewById(R.id.btn_writing);
        btn_reading = (Button) findViewById(R.id.btn_reading);
        vFlipper = (ViewFlipper) findViewById(R.id.vFlipper);

        btn_writing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vFlipper.showPrevious();
            }
        });
        btn_reading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vFlipper.showNext();
            }
        });

        Button btn_return = (Button) findViewById(R.id.btn_return);
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        datePicker1 = (DatePicker) findViewById(R.id.datePicker1);
        tv_content = (TextView) findViewById(R.id.tv_content);
        datePicker1.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                fileName = year + "_" + (monthOfYear+1) + "_" + dayOfMonth + ".txt";
                review = readReview(fileName);
                tv_content.setText(review);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater mInflater = getMenuInflater();
        if (v == btn_company) {
            menu.setHeaderTitle("동행 선택");
            mInflater.inflate(R.menu.menu1, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemAlone:
                company = "혼자";
                btn_company.setText(company);
                return true;
            case R.id.itemFriend:
                company = "친구";
                btn_company.setText(company);
                return true;
            case R.id.itemLover:
                company = "연인";
                btn_company.setText(company);
                return true;
            case R.id.itemParents:
                company = "부모님";
                btn_company.setText(company);
                return true;
        }
        return false;
    }

    String readReview(String fileName) {
        String reviewStr = null;
        FileInputStream inFs;
        try {
            inFs = openFileInput(fileName);
            byte[] txt = new byte[500];
            inFs.read(txt);
            inFs.close();

            reviewStr = (new String(txt)).trim();
        } catch(IOException e) {
            reviewStr = "후기가 없습니다";
        }
        return reviewStr;
    }
}