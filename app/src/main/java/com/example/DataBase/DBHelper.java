package com.example.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by qaz35 on 2018/1/14/014.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String TAG = "DBHelper";//此class名稱 用於顯示TAG

    private static final String DATABASE_NAME = "AngelHeart_DB";
    private static final int DATABASE_VERSION = 5;

    public static final String personalInformation_TABLE = "personalInformation";//資料表名稱
    public static final String personalInformation_TABLE_id = "p_id";
    public static final String personalInformation_TABLE_name = "p_name";
    public static final String personalInformation_TABLE_phone = "p_phone";
    public static final String personalInformation_TABLE_account = "p_account";
    public static final String personalInformation_TABLE_password = "p_password";
    public static final String personalInformation_TABLE_address = "p_address";
    public static final String personalInformation_TABLE_datetime = "p_datetime";


    public static final String voice_TABLE = "voice";
    public static final String voice_TABLE_number = "v_number";
    public static final String voice_TABLE_translationed = "v_translationed";
    public static final String voice_TABLE_translation = "v_translation";
    public static final String voice_TABLE_datetime = "v_datetime";


    public static final String onlineVoice_TABLE = "onlileVoice";
    public static final String ov_voice_TABLE_id = "p_id";
    public static final String ov_voice_TABLE_translationed = "ov_translationed";
    public static final String ov_voice_TABLE_translation = "ov_translation";
    public static final String ov_voice_TABLE_datetime = "ov_datetime";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    private static final String SQL_CREATE_TABLE_personalInformation = "CREATE TABLE " + personalInformation_TABLE + "("
            + personalInformation_TABLE_id + " INTEGER,"
            + personalInformation_TABLE_name + " TEXT,   "
            + personalInformation_TABLE_phone + " TEXT, "
            + personalInformation_TABLE_account + " TEXT, "
            + personalInformation_TABLE_password + " TEXT, "
            + personalInformation_TABLE_address + " TEXT, "
            + personalInformation_TABLE_datetime + " TEXT "
            + ");";


    private static final String SQL_CREATE_TABLE_voice = "CREATE TABLE " + voice_TABLE + "("
            + voice_TABLE_number + " INTEGER "
            + voice_TABLE_translation + " TEXT, "
            + voice_TABLE_translationed + " TEXT, "
            + voice_TABLE_datetime + " TEXT "
            + ");";


    private static final String SQL_CREATE_TABLE_onlineVoice = "CREATE TABLE " + onlineVoice_TABLE + "("
            + ov_voice_TABLE_id + " INTEGER, "
            + ov_voice_TABLE_translation + " TEXT, "
            + ov_voice_TABLE_translationed + " TEXT, "
            + ov_voice_TABLE_datetime + " TEXT "
            + ");";


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.w(TAG, "建立成功");
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_personalInformation);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_voice);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_onlineVoice);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading the database from version" + oldVersion + " to " + newVersion);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + personalInformation_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + onlineVoice_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + voice_TABLE);
        onCreate(sqLiteDatabase);
    }


}
