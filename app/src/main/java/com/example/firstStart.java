package com.example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Handler;
import android.view.WindowManager;

public class firstStart extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_first_start);
        final Intent intent = new Intent();

        // 延遲一秒載入新頁面
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                intent.setClass(firstStart.this,newSlideActivity.class);
                startActivity(intent);
                // 漸變動畫

//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
            }
        }, 1000);
    }
}
