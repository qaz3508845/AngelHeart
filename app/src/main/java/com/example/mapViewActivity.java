package com.example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class mapViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);
    }

    public void voiceOnClick(View view) {
        Intent intent = new Intent();
        intent.setClass(mapViewActivity.this, voiceActivity.class);
        startActivity(intent);
        finish();
    }

    public void personalInformationOnClick(View view) {
        Intent intent = new Intent();
        intent.setClass(mapViewActivity.this, personalInformationActivity.class);
        startActivity(intent);
        finish();
    }

    public void emergencyContactOnClick(View view) {
        Intent intent = new Intent();
        intent.setClass(mapViewActivity.this, emergencyContactActivity.class);
        startActivity(intent);
        finish();
    }

    public void mapViewOnClick(View view) {
        Intent intent = new Intent();
        intent.setClass(mapViewActivity.this, mapViewActivity.class);
        startActivity(intent);
        finish();
    }
}
