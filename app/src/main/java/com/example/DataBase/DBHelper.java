package com.example.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by qaz35 on 2018/1/14/014.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String TAG="DBHelper";//此class名稱 用於顯示TAG

    private static final String DATABASE_NAME="AngelHeart_DB";
    private static final int DATABASE_VERSION=1;

    public static final String registered_TABLE="registered_TABLE";//資料表名稱
    public static final String registered_TABLE_id="id";
    public static final String registered_TABLE_account="account";
    public static final String registered_TABLE_password="password";
    public static final String registered_TABLE_phone="phone";

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    public DBHelper(Context context,String name,SQLiteDatabase.CursorFactory factory,int version){
        super(context,DATABASE_NAME,factory,DATABASE_VERSION);
    }

    private static final String SQL_CREATE_TABLE_registered="CREATE TABLE " + registered_TABLE + "("
            + registered_TABLE_id + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + registered_TABLE_account + " TEXT, "
            + registered_TABLE_password + " TEXT, "
            + registered_TABLE_phone + " TEXT, "
            +");";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.w(TAG,"建立成功");
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_registered);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w(TAG,"Upgrading the database from version" + oldVersion+ " to "+newVersion);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ registered_TABLE);
        onCreate(sqLiteDatabase);
    }
}
