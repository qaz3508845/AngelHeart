package com.example.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by qaz35 on 2018/1/14/014.
 */

public class registeredDAO {
    public static final String TAG = "registeredDAO";

    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private Context context;

    public registeredDAO(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        try {
            open();
        } catch (SQLException e) {
            Log.e(TAG, "SQLException on opening database" + e.getMessage());
            e.printStackTrace();
        }
    }

    private void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean insertRegistered(String account, String password, String phone) {
        open();
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.registered_TABLE_account, account);
        cv.put(DBHelper.registered_TABLE_password, password);
        cv.put(DBHelper.registered_TABLE_phone, phone);
        try {
            database.insert(DBHelper.registered_TABLE, null, cv);
            Log.e(TAG, "資料新建成功");
            return true;

        } catch (Exception e) {
            Log.e(TAG, "資料新建失敗" + e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    public Cursor getAllData(){
        Cursor cursor= database.query(DBHelper.registered_TABLE,new String[]{DBHelper.registered_TABLE_id,
        DBHelper.registered_TABLE_account,DBHelper.registered_TABLE_password,DBHelper.registered_TABLE_phone}
        ,null,null,null,null,null);
        return cursor;
    }


}
