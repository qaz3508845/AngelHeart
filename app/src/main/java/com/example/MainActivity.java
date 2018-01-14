package com.example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void registerOnClick(View view) {
        Intent intent=new Intent();
        intent.setClass(MainActivity.this, registeredActivity.class);

        startActivity(intent);
        finish();
    }
}
