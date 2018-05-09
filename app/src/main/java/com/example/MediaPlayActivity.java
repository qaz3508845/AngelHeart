package com.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MediaPlayActivity extends AppCompatActivity {

    Button closeClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_play);
        closeClick = (Button) findViewById(R.id.closeClick);
        closeClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blueToothActivity.mMediaPlayer.release();
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (blueToothActivity.mMediaPlayer != null) {
            blueToothActivity.mMediaPlayer.release();
        }
    }
}
