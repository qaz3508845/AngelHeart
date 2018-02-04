package com.example;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.DataBase.DBHelper;
import com.example.DataBase.personalInformationDAO;
import com.example.ShowViewList.ShowItem;

import java.util.Calendar;

public class personalInformationActivity extends AppCompatActivity {

    personalInformationDAO db;
    TextView name2;
    TextView phone2;
    TextView address2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        db=new personalInformationDAO(personalInformationActivity.this);
        name2=(TextView)findViewById(R.id.name2_Txv);
        phone2=(TextView)findViewById(R.id.phone2_Txv);
        address2=(TextView)findViewById(R.id.address2_Txv);
        Cursor cursor=db.getAllData();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
//            Log.w("personalInformation",cursor.getString(cursor.getColumnIndex(DBHelper.personalInformation_TABLE_name))+"");
            name2.setText(cursor.getString(cursor.getColumnIndex(DBHelper.personalInformation_TABLE_name)));
            phone2.setText(cursor.getString(cursor.getColumnIndex(DBHelper.personalInformation_TABLE_phone)));
            address2.setText(cursor.getString(cursor.getColumnIndex(DBHelper.personalInformation_TABLE_address)));
            cursor.moveToNext();
        }



    }

    public void personalSetBtnOnClick(View view) {

        final View item= LayoutInflater.from(personalInformationActivity.this).inflate(R.layout.activity_set_personal_information,null);

        //彈出框
        new AlertDialog.Builder(personalInformationActivity.this)
                .setTitle("輸入你的個人資料")//標題顯示列
                .setView(item)
                //確定按鈕
                .setPositiveButton("確定輸入", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText nameEdt = (EditText) item.findViewById(R.id.nameEdt);
                        EditText phoneEdt=(EditText)item.findViewById(R.id.phoneEdt);
                        EditText addressEdt=(EditText)item.findViewById(R.id.addressEdt);
                        String name = nameEdt.getText().toString();
                        String phone = phoneEdt.getText().toString();
                        String address=addressEdt.getText().toString();
                        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(phone)||TextUtils.isEmpty(address)){
                            //無輸入時顯示
                            Toast.makeText(getApplicationContext(), "資料輸入不完全,請確認資料", Toast.LENGTH_SHORT).show();

                        } else {
                            ShowItem showItem=new ShowItem();
                            showItem.setP_name(name);
                            showItem.setP_phone(phone);
                            showItem.setP_address(address);
//                            showItem.setP_password("");
//                            showItem.setP_account("");
//                            showItem.setP_id(0);
//                            showItem.setP_datetime("");
                            db.deleteInformation();
                            db.insertPersonalInformation(showItem);
                            Intent intent = new Intent();
                            intent.setClass(personalInformationActivity.this, personalInformationActivity.class);
                            startActivity(intent);
                            finish();

                        }

                    }
                })
                .show();


    }



    public void voiceOnClick(View view) {
        Intent intent = new Intent();
        intent.setClass(personalInformationActivity.this, voiceActivity.class);
        startActivity(intent);
        finish();
    }

    public void personalInformationOnClick(View view) {
        Intent intent = new Intent();
        intent.setClass(personalInformationActivity.this, personalInformationActivity.class);
        startActivity(intent);
        finish();
    }

    public void emergencyContactOnClick(View view) {
        Intent intent = new Intent();
        intent.setClass(personalInformationActivity.this, emergencyContactActivity.class);
        startActivity(intent);
        finish();
    }

    public void mapViewOnClick(View view) {
        Intent intent = new Intent();
        intent.setClass(personalInformationActivity.this, mapViewActivity.class);
        startActivity(intent);
        finish();
    }


}
