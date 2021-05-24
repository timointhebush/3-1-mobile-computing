package com.example.leejs_60171651_hw5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setTitle("투표 결과");

        Intent intent = getIntent();
        int[] voteResult = intent.getIntArrayExtra("VoteCount");

        Button btnStart, btnStop;
        final ViewFlipper vFlipper;
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        vFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);

        Integer ivRankID[] = {R.id.ivRank1, R.id.ivRank2, R.id.ivRank3, R.id.ivRank4, R.id.ivRank5,
                R.id.ivRank6, R.id.ivRank7, R.id.ivRank8, R.id.ivRank9};
        Integer ivRankSrcID[] = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5,
                R.drawable.pic6, R.drawable.pic7, R.drawable.pic8, R.drawable.pic9};

        ImageView iv[] = new ImageView[9];

        Integer imgRankIdx[] = new Integer[9];
        for (int i = 0; i<9; i++) {
            int maxIdx = i;
            for (int j=0; j<9; j++) {
                if (voteResult[j] > voteResult[maxIdx])
                    maxIdx = j;
            };
            imgRankIdx[i] = maxIdx;
            voteResult[maxIdx] = 0;
        };

        for (int i=0; i<9; i++) {
            iv[i] = (ImageView) findViewById(ivRankID[i]);
            iv[i].setImageResource(ivRankSrcID[imgRankIdx[i]]);
        }

        vFlipper.setFlipInterval(1000);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vFlipper.startFlipping();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vFlipper.stopFlipping();
            }
        });
    }
}