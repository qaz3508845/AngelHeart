package com.example.ShowViewList;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.DataBase.DBHelper;
import com.example.DataBase.registeredDAO;
import com.example.R;

import java.util.ArrayList;

public class Show extends AppCompatActivity {

    ListView Lv;
    registeredDAO db;
    ShowItem showItem;

    ArrayList<ShowItem> itemArrayList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        Lv = (ListView)findViewById(R.id.show_Lv);

        db=new registeredDAO(Show.this);

        Cursor cursor= db.getAllData();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            showItem=new ShowItem();
            showItem.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.registered_TABLE_id)));
            showItem.setAccount(cursor.getString(cursor.getColumnIndex(DBHelper.registered_TABLE_account)));
            showItem.setPassword(cursor.getString(cursor.getColumnIndex(DBHelper.registered_TABLE_password)));
            showItem.setPhone(cursor.getString(cursor.getColumnIndex(DBHelper.registered_TABLE_phone)));

            itemArrayList.add(showItem);
            cursor.moveToNext();

        }
//        showItem=new ShowItem();
//        showItem.setId(123);
//        showItem.setAccount("ASD");
//        showItem.setPassword("ASDSAD");
//        showItem.setPhone("sdadsad");
//        itemArrayList.add(showItem);

        myAdapter adapter=new myAdapter(Show.this,itemArrayList);
        Lv.setAdapter(adapter);

    }
}
