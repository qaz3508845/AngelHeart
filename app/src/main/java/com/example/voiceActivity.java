package com.example;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.DataBase.DBHelper;
import com.example.DataBase.voiceDAO;
import com.example.ShowVoiceListView.voiceItem;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

public class voiceActivity extends AppCompatActivity {

    voiceDAO db;
    TextView translationTxv;

    TextToSpeech toSpeech;
    int resultA;
    String text;

    private static final int REQ_CODE_SPEECH_INPUT = 105;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);

        db = new voiceDAO(voiceActivity.this);

        translationTxv = (TextView) findViewById(R.id.translationed_Txv);
        toSpeech=new TextToSpeech(voiceActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS){
                    resultA=toSpeech.setLanguage(Locale.CHINESE);
                }else{
                    Toast.makeText(getApplicationContext(),"不好意思,您的設備不支援語音功能",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void TTS(View view){

    }

    public void voiceBtnOnClick(View view) {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH.toString());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "請問有什麼需要幫忙的地方?");

        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            a.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Cursor cursor = db.getAllData();
        ArrayList transLation = new ArrayList();
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {//語音判別有無成功
                    final ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//                    voiceArr.add(result.get(0));

                    cursor.moveToFirst();
                    Log.e("voiceTest_result", result.get(0));
                    while (!cursor.isAfterLast()) {
                        Log.e("voiceTest_cursor", cursor.getString(cursor.getColumnIndex(DBHelper.voice_TABLE_translation)));
                        if (cursor.getString(cursor.getColumnIndex(DBHelper.voice_TABLE_translation)).equals(result.get(0))) {
                            transLation.add(cursor.getString(cursor.getColumnIndex(DBHelper.voice_TABLE_translationed)));
                        }
                        cursor.moveToNext();

                    }
                    String s = "";
                    for (int i = 0; i < transLation.size(); i++) {
                        s += transLation.get(i) + ",";
                    }
                    if (transLation.size() > 0) {
                        translationTxv.setText("可能是:" + s);
                        text=translationTxv.getText().toString();
                        if(resultA==TextToSpeech.LANG_MISSING_DATA || resultA==TextToSpeech.LANG_NOT_SUPPORTED) {

                            Toast.makeText(getApplicationContext(), "不好意思,您的設備不支援語音功能", Toast.LENGTH_SHORT).show();
                        }else{
                            toSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
                        }
                    }
                    else{
                        translationTxv.setText("資料庫沒有或語意不清楚");

                        text=translationTxv.getText().toString();
                        if(resultA==TextToSpeech.LANG_MISSING_DATA || resultA==TextToSpeech.LANG_NOT_SUPPORTED) {

                            Toast.makeText(getApplicationContext(), "不好意思,您的設備不支援語音功能", Toast.LENGTH_SHORT).show();
                        }else{
                            toSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
                        }
                    }

                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(toSpeech!=null){
            toSpeech.stop();
            toSpeech.shutdown();
        }
    }

    public void voiceOnClick(View view) {
        Intent intent = new Intent();
        intent.setClass(voiceActivity.this, voiceActivity.class);
        startActivity(intent);
        finish();
    }

    public void personalInformationOnClick(View view) {
        Intent intent = new Intent();
        intent.setClass(voiceActivity.this, personalInformationActivity.class);
        startActivity(intent);
        finish();

    }

    public void emergencyContactOnClick(View view) {
        Intent intent = new Intent();
        intent.setClass(voiceActivity.this, emergencyContactActivity.class);
        startActivity(intent);
        finish();
    }

    public void mapViewOnClick(View view) {
        Intent intent = new Intent();
        intent.setClass(voiceActivity.this, mapViewActivity.class);
        startActivity(intent);
        finish();
    }

    public void showStartOnClick(View view) {
        Intent intent = new Intent();
        intent.setClass(voiceActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void settingOnClick(View view) {
        Intent intent = new Intent();
        intent.setClass(voiceActivity.this, voiceSettingActivity.class);
        startActivity(intent);


    }
}
