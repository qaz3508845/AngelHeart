package com.example.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.ShowEmergencyListView.emergencyItem;
import com.example.ShowViewList.ShowItem;

/**
 * Created by qaz35 on 2018/3/24/024.
 */

public class emergencyDAO {
    public static final String TAG = "emergencyDAO";

    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private Context context;

    public emergencyDAO(Context context){
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


    public Cursor getAllData() {
        Cursor cursor = database.query(DBHelper.emergency_TABLE, new String[]{
                        DBHelper.emergency_TABLE_id
                        ,DBHelper.emergency_TABLE_nickName
                        ,DBHelper.emergency_TABLE_phone
                        ,DBHelper.emergency_TABLE_name

                }
                , null, null, null, null, null);
        return cursor;
    }

    public boolean insertPersonalInformation(String name,String nickName,String phone) {
        open();
        ContentValues cv = new ContentValues();

        cv.put(DBHelper.emergency_TABLE_name,name);
        cv.put(DBHelper.emergency_TABLE_nickName,nickName);
        cv.put(DBHelper.emergency_TABLE_phone,phone);


        try {
            database.insert(DBHelper.emergency_TABLE, null, cv);
            Log.e(TAG, "資料新建成功");

            return true;

        } catch (Exception e) {
            Log.e(TAG, "資料新建失敗" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}
