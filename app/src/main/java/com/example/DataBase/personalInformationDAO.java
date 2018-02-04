package com.example.DataBase;
/**
 * Created by qaz35 on 2018/1/20/020.
 */

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.example.SendRequest;
import com.example.ShowViewList.ShowItem;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;


/**
 * Created by qaz35 on 2018/1/14/014.
 */

public class personalInformationDAO {
    public static final String TAG = "registeredDAO";

    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private Context context;

    public personalInformationDAO(Context context) {
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

    public void insertPersonalInformation(String name, String phone, String account, String password, String address) {
//        open();
//        ContentValues cv = new ContentValues();

//        new SendRequest().execute(name, account, password, phone, address);
//        SendRequest SR=new SendRequest();


    }

    //    public boolean insertPersonalInformation(int id,String name ,String phone,String account,String password
//    ,String address,String dateTime) {
    public boolean insertPersonalInformation(ShowItem showItem) {
        open();
        ContentValues cv = new ContentValues();

        cv.put(DBHelper.personalInformation_TABLE_id, showItem.getP_id());
        cv.put(DBHelper.personalInformation_TABLE_name, showItem.getP_name());
        cv.put(DBHelper.personalInformation_TABLE_phone, showItem.getP_phone());
        cv.put(DBHelper.personalInformation_TABLE_account, showItem.getP_account());
        cv.put(DBHelper.personalInformation_TABLE_password, showItem.getP_password());
        cv.put(DBHelper.personalInformation_TABLE_address, showItem.getP_address());
        cv.put(DBHelper.personalInformation_TABLE_datetime, showItem.getP_datetime());

        try {
            database.insert(DBHelper.personalInformation_TABLE, null, cv);
            Log.e(TAG, "資料新建成功");

            return true;

        } catch (Exception e) {
            Log.e(TAG, "資料新建失敗" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public Cursor getAllData() {
//        Cursor cursor= database.query(DBHelper.personalInformation_TABLE,new String[]{DBHelper.personalInformation_TABLE_id
//                        , DBHelper.personalInformation_TABLE_name,DBHelper.personalInformation_TABLE_phone
//                        ,DBHelper.personalInformation_TABLE_account,DBHelper.personalInformation_TABLE_password
//                        ,DBHelper.personalInformation_TABLE_address,DBHelper.personalInformation_TABLE_datetime
//                }
        Cursor cursor = database.query(DBHelper.personalInformation_TABLE, new String[]{
                        DBHelper.personalInformation_TABLE_id
                        , DBHelper.personalInformation_TABLE_account
                        , DBHelper.personalInformation_TABLE_password
                        , DBHelper.personalInformation_TABLE_phone

                }
                , null, null, null, null, null);
        return cursor;
    }

    public boolean loginUser(String account, String password) {
        Cursor cursor = database.rawQuery(
                "SELECT " + DBHelper.personalInformation_TABLE_account
                        + " FROM " + DBHelper.personalInformation_TABLE
                        + " WHERE " + DBHelper.personalInformation_TABLE_account + " = '" + account + "' "
                        + " AND " + DBHelper.personalInformation_TABLE_password + " = '" + password + "' "
                , null);
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }


    public void DROP() {
        database.execSQL("DROP TABLE IF EXISTS " + DBHelper.personalInformation_TABLE);
        dbHelper.onCreate(database);

    }



}
