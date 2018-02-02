package com.example;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.speech.tts.Voice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.DataBase.DBHelper;
import com.example.DataBase.personalInformationDAO;
import com.example.ShowViewList.Show;

public class MainActivity extends AppCompatActivity {
    //YA
    personalInformationDAO db;
    TextView account_Edt;
    TextView password_Edt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new personalInformationDAO(MainActivity.this);
//        db.close();
    }

    public void registerOnClick(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, registeredActivity.class);


        startActivity(intent);
//        finish();
    }

    public void loginOnClick(View view) {
        db = new personalInformationDAO(MainActivity.this);
        account_Edt = (EditText) findViewById(R.id.account_Edt);
        password_Edt = (EditText) findViewById(R.id.password_Edt);

        // Get text from email and passord field
        final String email = account_Edt.getText().toString();
        final String password = password_Edt.getText().toString();

        // Initialize  AsyncLogin() class with email and password
        new AsyncLogin().execute(email, password);


        Intent intent = new Intent();
        intent.setClass(MainActivity.this, voiceActivity.class);
        startActivity(intent);
        finish();
    }

    private class AsyncLogin extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            return null;
        }
    }


    public void clearTableOnClick(View view) {
        db.DROP();
    }

}