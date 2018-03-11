package com.example;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.DataBase.DBHelper;
import com.example.DataBase.voiceDAO;
import com.example.ShowVoiceListView.voiceAdapter;
import com.example.ShowVoiceListView.voiceItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class voiceSettingActivity extends AppCompatActivity {


    private static final int REQ_CODE_SPEECH_INPUT = 100;
    ArrayList<String> voiceArr=new ArrayList<>();
//    TextView test;
    voiceDAO db;
    ListView Lv;
    voiceItem vItem;
    static int number;
    ArrayList<voiceItem> itemArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_setting);
//        test=(TextView)findViewById(R.id.test);
        db=new voiceDAO(voiceSettingActivity.this);
        Lv = (ListView)findViewById(R.id.emergency_Lv);
        int number=1;
        Cursor cursor= db.getAllData();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            vItem=new voiceItem();
//            vItem.setV_number(cursor.getInt(cursor.getColumnIndex(DBHelper.voice_TABLE_number)));
            vItem.setV_id(cursor.getInt(cursor.getColumnIndex(DBHelper.voice_TABLE_number)));
            Log.e("TEST",cursor.getInt(cursor.getColumnIndex(DBHelper.voice_TABLE_number))+"");
            vItem.setV_number(number++);
            vItem.setV_translation(cursor.getString(cursor.getColumnIndex(DBHelper.voice_TABLE_translation)));
            vItem.setV_translationed(cursor.getString(cursor.getColumnIndex(DBHelper.voice_TABLE_translationed)));
            vItem.setV_datetime(cursor.getString(cursor.getColumnIndex(DBHelper.voice_TABLE_datetime)));

            itemArrayList.add(vItem);
            cursor.moveToNext();

        }
        voiceAdapter adapter=new voiceAdapter(db,voiceSettingActivity.this,itemArrayList);
        Lv.setAdapter(adapter);

//        number=cursor.getCount();
//        test.setText("如果顯示無法連接上GOOGLE伺服器 請至設定");
    }


    public void addVoiceBtnOnClick(View view) {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH.toString());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "請說出想做的事");

        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            a.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        test=(TextView)findViewById(R.id.test);
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {//語音判別有無成功
                    final ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    voiceArr.add(result.get(0));
                    Log.e("inVoiceTest",result.get(0));

                    final View item= LayoutInflater.from(voiceSettingActivity.this).inflate(R.layout.activity_add_voice,null);

                    //彈出框
                    new AlertDialog.Builder(voiceSettingActivity.this)
                            .setTitle("請輸入你想做的事")//標題顯示列
                            .setView(item)
                            //確定按鈕
                            .setPositiveButton("確定輸入", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    EditText editText = (EditText) item.findViewById(R.id.meaning_Edt);
                                    String name = editText.getText().toString();
                                    if(TextUtils.isEmpty(name)){
                                        //無輸入時顯示
                                        Toast.makeText(getApplicationContext(), "沒有輸入任何東西,資料輸入失敗", Toast.LENGTH_SHORT).show();

                                    } else {
                                        //抓取目前系統時間
                                        Calendar mCal = Calendar.getInstance();
                                        CharSequence s = DateFormat.format("yyyy-MM-dd kk:mm:ss", mCal.getTime());
                                       //讀資料庫輸入傳回值 如果成功 true
                                        boolean boo= db.insertVoice(result.get(0),name, (String) s);
                                        if(boo){
                                            Toast.makeText(getApplicationContext(), "輸入成功: " + name, Toast.LENGTH_SHORT).show();
                                            Intent intent=new Intent(voiceSettingActivity.this,voiceSettingActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }else{
                                            Toast.makeText(getApplicationContext(), "資料庫輸入失敗", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                }
                            })
                            .show();


                }
                break;
            }

        }


    }
}
