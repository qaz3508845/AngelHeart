package com.example;


import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.DataBase.DBHelper;
import com.example.DataBase.voiceDAO;
import com.example.ShowViewList.ShowItem;

import java.util.ArrayList;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link voiceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class voiceFragment extends Fragment {

    voiceDAO db;
    TextView translationTxv;

    TextToSpeech toSpeech;
    int resultA;
    String text;

    private static final int REQ_CODE_SPEECH_INPUT = 105;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public voiceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment voiceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static voiceFragment newInstance(String param1, String param2) {
        voiceFragment fragment = new voiceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_voice, container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = new voiceDAO(getContext());

        translationTxv = (TextView) view.findViewById(R.id.translationed_Txv);
        toSpeech=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS){
                    resultA=toSpeech.setLanguage(Locale.CHINESE);
                }else{
                    Toast.makeText(getContext(),"不好意思,您的設備不支援語音功能",Toast.LENGTH_SHORT).show();
                }
            }
        });


        Button voice_btn = (Button)view.findViewById(R.id.voice_Btn);
        voice_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.TAIWAN.toString());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "請問有什麼需要幫忙的地方?");

                try {
                    startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
                } catch (ActivityNotFoundException a) {
                    a.printStackTrace();
                }

            }
        });


        Button show_start_btn = (Button)view.findViewById(R.id.show_Start_Btn);
        show_start_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        Button setting_btn = (Button)view.findViewById(R.id.setting_Btn);
        setting_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(getContext(), voiceSettingActivity.class);
                startActivity(intent);
            }
        });


        Button blueToothBtn = (Button)view.findViewById(R.id.blueToothBtn);
        blueToothBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getContext(), blueToothActivity.class);
                startActivity(intent);
            }
        });



    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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

                            Toast.makeText(getContext(), "不好意思,您的設備不支援語音功能", Toast.LENGTH_SHORT).show();
                        }else{
                            toSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
                        }
                    }
                    else{
                        translationTxv.setText("資料庫沒有或語意不清楚");

                        text=translationTxv.getText().toString();
                        if(resultA==TextToSpeech.LANG_MISSING_DATA || resultA==TextToSpeech.LANG_NOT_SUPPORTED) {

                            Toast.makeText(getContext(), "不好意思,您的設備不支援語音功能", Toast.LENGTH_SHORT).show();
                        }else{
                            toSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
                        }
                    }

                }
            }
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(toSpeech!=null){
            toSpeech.stop();
            toSpeech.shutdown();
        }
    }


}
