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
    private static final int DATABASE_VERSION = 11;

    public static final String personalInformation_TABLE = "personalInformation";//資料表名稱
    public static final String personalInformation_TABLE_id = "p_id";
    public static final String personalInformation_TABLE_name = "p_name";
    public static final String personalInformation_TABLE_phone = "p_phone";
    public static final String personalInformation_TABLE_account = "p_account";
    public static final String personalInformation_TABLE_password = "p_password";
    public static final String personalInformation_TABLE_address = "p_address";
    public static final String personalInformation_TABLE_datetime = "p_datetime";


    public static final String voice_TABLE = "voice";//語音 線下
    public static final String voice_TABLE_number = "v_number";
    public static final String voice_TABLE_translationed = "v_translationed";
    public static final String voice_TABLE_translation = "v_translation";
    public static final String voice_TABLE_datetime = "v_datetime";


    public static final String onlineVoice_TABLE = "onlileVoice"; //線上 語音
    public static final String ov_voice_TABLE_id = "p_id";
    public static final String ov_voice_TABLE_translationed = "ov_translationed";
    public static final String ov_voice_TABLE_translation = "ov_translation";
    public static final String ov_voice_TABLE_datetime = "ov_datetime";



    public static final String emergency_TABLE = "emergency";
    public static final String emergency_TABLE_id="e_id";
    public static final String emergency_TABLE_name="e_name";
    public static final String emergency_TABLE_nickName="e_nickName";
    public static final String emergency_TABLE_phone="e_phone";










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
            + voice_TABLE_number + " INTEGER PRIMARY KEY AUTOINCREMENT, "
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
    private static final String SQL_CREATE_TABLE_emergency = "CREATE TABLE " + emergency_TABLE + "("
            + emergency_TABLE_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + emergency_TABLE_name + " TEXT, "
            + emergency_TABLE_nickName + " TEXT, "
            + emergency_TABLE_phone + " TEXT "
            + ");";


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.w(TAG, "建立成功");
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_personalInformation);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_voice);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_onlineVoice);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_emergency);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading the database from version" + oldVersion + " to " + newVersion);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + personalInformation_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + onlineVoice_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + voice_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + emergency_TABLE);
        onCreate(sqLiteDatabase);
    }


}
