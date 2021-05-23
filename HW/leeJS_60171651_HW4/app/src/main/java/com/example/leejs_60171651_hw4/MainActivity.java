package com.example.leejs_60171651_hw4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    final static int LINE = 1, CIRCLE = 2, RECT20 = 3, RECT60 = 4;
    static int curShape = LINE;
    // 메뉴를 통해 채우기, 선만 그리기를 선택하기 위한 변수 선언
    static Paint.Style paint_style = Paint.Style.STROKE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
        setTitle("이준석 과제4 도형그리기");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0, 1, 0, "선(Path) 그리기");
        menu.add(0, 2, 0, "원 그리기");
        SubMenu sub_roundRect = menu.addSubMenu("둥근 사각형 그리기");
        sub_roundRect.add(0, 3, 0, "반지름 20");
        sub_roundRect.add(0, 4, 0, "반지름 60");
        SubMenu sub_inside = menu.addSubMenu("내부 설정");
        sub_inside.add(0, 5, 0, "내부 채우기");
        sub_inside.add(0, 6, 0, "선만 그리기");
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                curShape = LINE;
                return true;
            case 2:
                curShape = CIRCLE;
                return true;
            case 3:
                curShape = RECT20;
                return true;
            case 4:
                curShape = RECT60;
                return true;
            case 5:
                paint_style = Paint.Style.FILL;
                return true;
            case 6:
                paint_style = Paint.Style.STROKE;
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class MyGraphicView extends View {
        int startX = -1, startY = -1, stopX = -1, stopY = -1;
        // 터치로 선 그리기 위한 Path 객체 선언.
        Path path1 = new Path();

        public MyGraphicView(Context context) {super(context);}

        @Override
        //기존의 switch-case에서 if를 추가하여 path로 선을 그릴 수 있도록 함.
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startX = (int) event.getX();
                    startY = (int) event.getY();
                    if (curShape == LINE)
                        path1.moveTo(startX, startY);
                    break;
                case MotionEvent.ACTION_MOVE:
                case MotionEvent.ACTION_UP:
                    stopX = (int) event.getX();
                    stopY = (int) event.getY();
                    if (curShape == LINE)
                        path1.lineTo(stopX, stopY);
                    this.invalidate();
                    break;
            }
            return true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);
            paint.setStyle(paint_style);
            paint.setColor(Color.RED);

            switch(curShape) {
                case LINE:
                    canvas.drawPath(path1, paint);
                    break;
                case CIRCLE:
                    int radius = (int) Math.sqrt(Math.pow(stopX - startX, 2) + Math.pow(stopY-startY, 2));
                    canvas.drawCircle(startX, startY, radius, paint);
                    break;
                case RECT20:
                    RectF rect20 = new RectF(startX, startY, stopX, stopY);
                    canvas.drawRoundRect(rect20, 20, 20, paint);
                    break;
                case RECT60:
                    RectF rect40 = new RectF(startX, startY, stopX, stopY);
                    canvas.drawRoundRect(rect40, 60, 60, paint);
                    break;
            }

        }
    }
}