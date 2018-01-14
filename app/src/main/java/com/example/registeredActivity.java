package com.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class registeredActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
    }

    public void registeredBtnClick(View view){
        Toast.makeText(this,"Hello",Toast.LENGTH_LONG);
    }
}
