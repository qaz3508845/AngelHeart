package com.example;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

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
 * Created by qaz35 on 2018/2/2/002.
 */
public class SendRequest extends AsyncTask<String, Void, String> {
    ShowItem showItem;
    URL url;
    private Context context;
//    ProgressDialog pdLoading = new ProgressDialog(context);
    public AsyncTaskResult<String> connectionTestResult = null;


    protected void onPreExecute() {
//        pdLoading.setMessage("\tLoading...");
//        pdLoading.setCancelable(true);
//        pdLoading.show();

    }

    protected String doInBackground(String... arg0) {

        try {

            url = new URL("http://192.168.137.122/AngelHeart/personal_insert.php");//伺服器位置
//                URL url=new URL("http://192.168.137.86/Test/test.php");
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
//                    return new String("false : " + responseCode);

                return responseCode + "";
            }
        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        } finally {
//            pdLoading.dismiss();
        }
    }

    @Override
    protected void onPostExecute(String result) {



        this.connectionTestResult.taskFinish( result );
//        Toast.makeText(getApplicationContext(), result,
//                Toast.LENGTH_LONG).show();
//        HelloWorld=(TextView)findViewById(R.id.HelloWorld);
//        HelloWorld.setText(result);
        Log.e("testAAA", result);

//        if (result.equals("true")) {

//            pdLoading.dismiss();


//        } else {
            Log.e("RESULT TEST", result);

//            pdLoading.dismiss();
//                try {
//                    Log.e("test", result);
//                    JSONObject jsonObject = new JSONObject(result);
//                    showItem = new ShowItem(jsonObject.getInt("p_id")
//                            , jsonObject.getString("p_name")
//                            , jsonObject.getString("p_phone")
//                            , jsonObject.getString("p_account")
//                            , jsonObject.getString("p_password")
//                            , jsonObject.getString("p_address")
//                            , jsonObject.getString("p_datetime")
//                    );
//
//
//                    pdLoading.dismiss();
//                    boo = insertPersonalInformation(showItem);
//
//                } catch (JSONException e) {
//                    Log.e("LogError", result);
//                    e.printStackTrace();
////
//                } finally {
//                    pdLoading.dismiss();
//                }

//        }

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

