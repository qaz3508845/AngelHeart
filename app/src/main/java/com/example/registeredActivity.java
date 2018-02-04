package com.example;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.DataBase.registeredDAO;


public class registeredActivity extends AppCompatActivity {

    static final String DB_name = "customer";
    static SQLiteDatabase DB;


    registeredDAO db;

    EditText account_Edt;
    EditText password_Edt;
    EditText phone_Edt;
    Button registered_Btn;
    EditText confirmPassword_Edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        db=new registeredDAO(registeredActivity.this);


        DB = openOrCreateDatabase(DB_name, Context.MODE_PRIVATE,null);
        String sql = "CREATE TABLE people IF NOT EXISTS "+
                "(p_account VARCHAR(32),"+
                "p_password VARCHAR(32),"+
                "p_phone VARCHAR(32))";
        DB.execSQL(sql);

    }

    public void registeredBtnClick(View view){
        account_Edt=(EditText)findViewById(R.id.account_Edt);
        password_Edt=(EditText)findViewById(R.id.password_Edt);
        phone_Edt=(EditText)findViewById(R.id.phone_Edt);
        confirmPassword_Edt=(EditText)findViewById(R.id.confirmPassword_Edt);

        registered_Btn = (Button)findViewById(R.id.registered_Btn);

        ContentValues cv = new ContentValues(3);
        String account=account_Edt.getText().toString();
        String password=password_Edt.getText().toString();
        String phone=phone_Edt.getText().toString();

        cv.put("p_account",account);
        cv.put("p_password",account);
        cv.put("p_phone",account);
        DB.insert("people",null,cv);


//        if(account_Edt.getText().toString().equals("")||
//                password_Edt.getText().toString().equals("")||
//                phone_Edt.getText().toString().equals("")||
//                confirmPassword_Edt.getText().toString().equals("")
//                ){
//            Toast.makeText(registeredActivity.this,"資料輸入不完整,請輸入資料後再按註冊"
//                    ,Toast.LENGTH_LONG).show();
//        }else if(!(password_Edt.getText().toString().equals(confirmPassword_Edt.getText().toString()))){
//            Toast.makeText(registeredActivity.this,"你兩次密碼輸入不一致，請重新輸入密碼"
//                    ,Toast.LENGTH_LONG).show();
//        }
//        else {
//            String account=account_Edt.getText().toString();
//            String password=password_Edt.getText().toString();
//            String phone=phone_Edt.getText().toString();
//            boolean boo=db.insertRegistered(account,password,phone);
//            if(boo){
//                Toast.makeText(registeredActivity.this,"創建成功",Toast.LENGTH_LONG).show();
//                Intent intent=new Intent();
//                intent.setClass(registeredActivity.this,MainActivity.class);
//                startActivity(intent);
//                finish();
//
//            }else{
//                Toast.makeText(registeredActivity.this,"創建失敗",Toast.LENGTH_LONG).show();
//
//            }
//        }

//        Intent intent=new Intent();
//        intent.setClass(registeredActivity.this,MainActivity.class);
//        startActivity(intent);
//        finish();


    }

    public void loginBtnOnClick(View view) {
        Intent intent=new Intent();
        intent.setClass(registeredActivity.this,MainActivity.class);
        startActivity(intent);
        finish();

    }




}
