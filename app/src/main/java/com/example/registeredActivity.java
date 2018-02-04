package com.example;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.DataBase.personalInformationDAO;


public class registeredActivity extends AppCompatActivity implements AsyncTaskResult<String> {

    personalInformationDAO db;

    EditText account_Edt;
    EditText password_Edt;
    EditText phone_Edt;
    Button registered_Btn;
    EditText confirmPassword_Edt;
    EditText address_Edt;
    EditText name_Edt;


    ProgressDialog pdLoading ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        db = new personalInformationDAO(registeredActivity.this);

        pdLoading= new ProgressDialog(registeredActivity.this);
    }

    public void registeredBtnClick(View view) {
        account_Edt = (EditText) findViewById(R.id.account_Edt);
        password_Edt = (EditText) findViewById(R.id.password_Edt);
        phone_Edt = (EditText) findViewById(R.id.phone_Edt);
        confirmPassword_Edt = (EditText) findViewById(R.id.confirmPassword_Edt);
        address_Edt = (EditText) findViewById(R.id.address_Edt);
        name_Edt = (EditText) findViewById(R.id.name_Edt);

        if (account_Edt.getText().toString().equals("") ||
                password_Edt.getText().toString().equals("") ||
                phone_Edt.getText().toString().equals("") ||
                confirmPassword_Edt.getText().toString().equals("")
                ) {
            Toast.makeText(registeredActivity.this, "資料輸入不完整,請輸入資料後再按註冊"
                    , Toast.LENGTH_LONG).show();
        } else if (!(password_Edt.getText().toString().equals(confirmPassword_Edt.getText().toString()))) {
            Toast.makeText(registeredActivity.this, "你兩次密碼輸入不一致，請重新輸入密碼"
                    , Toast.LENGTH_LONG).show();
        } else {
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();
            String account = account_Edt.getText().toString();
            String password = password_Edt.getText().toString();
            String phone = phone_Edt.getText().toString();
            String address = address_Edt.getText().toString();
            String name = name_Edt.getText().toString();
//            boolean boo=db.insertPersonalInformation(account,password,phone);
//            db.insertPersonalInformation(name, phone, account, password, address);
            SendRequest SR = new SendRequest();
            SR.connectionTestResult = this;
            SR.execute(name, phone, account, password, address);


//            if (boo) {
//                Toast.makeText(registeredActivity.this, "創建成功", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent();
//                intent.setClass(registeredActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//
//            } else {
//                Toast.makeText(registeredActivity.this, "創建失敗,可能是伺服器未啟用", Toast.LENGTH_LONG).show();
////
//            }
        }

//        Intent intent=new Intent();
//        intent.setClass(registeredActivity.this,MainActivity.class);
//        startActivity(intent);
//        finish();


    }

    public void loginBtnOnClick(View view) {
        Intent intent = new Intent();
        intent.setClass(registeredActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void taskFinish(String result) {
        Log.e("TASKFINISH", result);
        if (result.equals("true")) {
            Toast.makeText(this, "註冊成功", Toast.LENGTH_LONG).show();
            Intent intent = new Intent();
            intent.setClass(registeredActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            pdLoading.dismiss();
        } else {
            Toast.makeText(this, "註冊失敗,帳號可能重複,請換一組帳號在試一次,如果還是不行,請聯絡服務人員", Toast.LENGTH_LONG).show();
            pdLoading.dismiss();
        }
    }
}
