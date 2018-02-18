package com.example;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

/**
 * Created by user on 2018/2/11/011.
 */

public class BaseActivity extends FragmentActivity {

    protected Context mContext;

    @Override
    protected void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        super.onCreate(arg0);
        mContext = this;
    }

    protected void Toasters(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

}