package com.example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class emergencyContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contact);
    }

    public void voiceOnClick(View view) {
        Intent intent = new Intent();
        intent.setClass(emergencyContactActivity.this, voiceActivity.class);
        startActivity(intent);
        finish();
    }

    public void personalInformationOnClick(View view) {
        Intent intent = new Intent();
        intent.setClass(emergencyContactActivity.this, personalInformationActivity.class);
        startActivity(intent);
        finish();
    }

    public void emergencyContactOnClick(View view) {
        Intent intent = new Intent();
        intent.setClass(emergencyContactActivity.this, emergencyContactActivity.class);
        startActivity(intent);
        finish();
    }

    public void mapViewOnClick(View view) {
        Intent intent = new Intent();
        intent.setClass(emergencyContactActivity.this, mapViewActivity.class);
        startActivity(intent);
        finish();
    }


}
