package com.example.DataBase;
/**
 * Created by qaz35 on 2018/1/20/020.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.example.ShowViewList.ShowItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
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

    public void insertPersonalInformation(String name,String account, String password, String phone,String address) {
//        open();
//        ContentValues cv = new ContentValues();

        new SendRequest().execute(name,account,password,phone,address);



//        cv.put(DBHelper.personalInformation_TABLE_account, account);
//        cv.put(DBHelper.personalInformation_TABLE_password, password);
//        cv.put(DBHelper.personalInformation_TABLE_phone, phone);
//        try {
//            database.insert(DBHelper.personalInformation_TABLE, null, cv);
//            Log.e(TAG, "資料新建成功");
//            return true;
//
//        } catch (Exception e) {
//            Log.e(TAG, "資料新建失敗" + e.getMessage());
//            e.printStackTrace();
//            return false;
//        }

    }
//    public boolean insertPersonalInformation(int id,String name ,String phone,String account,String password
//    ,String address,String dateTime) {
    public boolean insertPersonalInformation(ShowItem showItem){
        open();
        ContentValues cv = new ContentValues();

        cv.put(DBHelper.personalInformation_TABLE_id,showItem.getP_id());
        cv.put(DBHelper.personalInformation_TABLE_name,showItem.getP_name());
        cv.put(DBHelper.personalInformation_TABLE_phone,showItem.getP_phone());
        cv.put(DBHelper.personalInformation_TABLE_account,showItem.getP_account());
        cv.put(DBHelper.personalInformation_TABLE_password,showItem.getP_password());
        cv.put(DBHelper.personalInformation_TABLE_address,showItem.getP_address());
        cv.put(DBHelper.personalInformation_TABLE_datetime,showItem.getP_datetime());

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
                        + " AND "+DBHelper.personalInformation_TABLE_password+" = '"+password+"' "
        ,null);
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }


    public void DROP() {
        database.execSQL("DROP TABLE IF EXISTS " + DBHelper.personalInformation_TABLE);
        dbHelper.onCreate(database);

    }






    class SendRequest extends AsyncTask<String, Void, String> {
        ShowItem showItem;
        URL url;
        protected void onPreExecute() {
        }

        protected String doInBackground(String... arg0) {

            try {

//                url= new URL("http://192.168.137.122/AngelHeart/insert.php");//伺服器位置
                URL url=new URL("http://192.168.137.86/Test/test.php");
//                URL url=new URL("http://192.168.137.122/AngelHeart/test.php");
                JSONObject postDataParams = new JSONObject();//創建json


                //放進json資料
                postDataParams.put("p_name", arg0[0]);
                postDataParams.put("p_phone", arg0[1]);
                postDataParams.put("p_account", arg0[2]);
                postDataParams.put("p_password", arg0[3]);
                postDataParams.put("p_address", arg0[4]);

                Log.e("params", postDataParams.toString());


                //HTTP連接
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //如果時間超過 就不進行post
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                //輸出到網站上面
                OutputStream os = conn.getOutputStream();//讀入上傳資訊
                BufferedWriter writer = new BufferedWriter(//
                        new OutputStreamWriter(os, "UTF-8"));//輸出成UTF-8
//            writer.write(String.valueOf(postDataParams));
                writer.write(getPostDataString(postDataParams)); //送出去給網站
                writer.flush();//以防漏掉資訊
                //關閉
                writer.close();
                os.close();

                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line = "";

                    while ((line = in.readLine()) != null) {

                        sb.append(line);
//                        break;
                    }

                    in.close();
                    return sb.toString();

                } else {
                    return new String("false : " + responseCode);
                }
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {

//        Toast.makeText(getApplicationContext(), result,
//                Toast.LENGTH_LONG).show();
//        HelloWorld=(TextView)findViewById(R.id.HelloWorld);
//        HelloWorld.setText(result);
//            Log.e("test",result);


            try{
                Log.e("test",result);
                JSONObject jsonObject = new JSONObject(result);
                showItem=new ShowItem(jsonObject.getInt("p_id")
                        ,jsonObject.getString("p_name")
                        ,jsonObject.getString("p_phone")
                        ,jsonObject.getString("p_account")
                        ,jsonObject.getString("p_password")
                        ,jsonObject.getString("p_address")
                        ,jsonObject.getString("p_datetime")
                );

                insertPersonalInformation(showItem);

            }
            catch(JSONException e) {
                Log.e("LogError",result);
                e.printStackTrace();
//
            }

        }



        public String getPostDataString(JSONObject params) throws Exception {

            StringBuilder result = new StringBuilder();
            boolean first = true;

            Iterator<String> itr = params.keys();

            while (itr.hasNext()) {

                String key = itr.next();
                Object value = params.get(key);

                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(key, "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(value.toString(), "UTF-8"));

            }
            return result.toString();
        }
    }










}
