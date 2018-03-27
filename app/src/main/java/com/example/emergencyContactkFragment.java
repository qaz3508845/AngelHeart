package com.example;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.DataBase.DBHelper;
import com.example.DataBase.emergencyDAO;
import com.example.DataBase.personalInformationDAO;
import com.example.DataBase.voiceDAO;
import com.example.ShowEmergencyListView.emergencyAdapter;
import com.example.ShowEmergencyListView.emergencyItem;
import com.example.ShowViewList.Show;
import com.example.ShowViewList.ShowItem;
import com.example.ShowViewList.myAdapter;
import com.example.ShowVoiceListView.voiceItem;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link emergencyContactkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class emergencyContactkFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    ListView Lv;
    emergencyItem eItem;
    Button addEmergencyBtn, editEmergencyBtn;
    emergencyDAO db;

    public emergencyContactkFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment emergencyContactkFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static emergencyContactkFragment newInstance(String param1, String param2) {
        emergencyContactkFragment fragment = new emergencyContactkFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_emergency_contactk, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addEmergencyBtn = (Button) view.findViewById(R.id.addEmergency_Btn);
        Lv = (ListView) view.findViewById(R.id.emergency_Lv);
        db = new emergencyDAO(getContext());
        ArrayList<emergencyItem> itemArrayList = new ArrayList<>();
        Cursor cursor = db.getAllData();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            eItem = new emergencyItem();
            eItem.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.emergency_TABLE_id)));
            eItem.setName(cursor.getString(cursor.getColumnIndex(DBHelper.emergency_TABLE_name)));
            eItem.setNickName(cursor.getString(cursor.getColumnIndex(DBHelper.emergency_TABLE_nickName)));
            eItem.setPhone(cursor.getString(cursor.getColumnIndex(DBHelper.emergency_TABLE_phone)));
            itemArrayList.add(eItem);
            cursor.moveToNext();
        }

        final emergencyAdapter adapter = new emergencyAdapter(db,getContext(), itemArrayList);
        Lv.setAdapter(adapter);


        addEmergencyBtn.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(final View view) {
                Log.e("新增", "彈出");
                final View item = LayoutInflater.from(getContext()).inflate(R.layout.activity_add_emergency_information, null);
                new AlertDialog.Builder(getContext()).setTitle("請輸入緊急聯絡人資訊")
                        .setView(item).setPositiveButton("確定輸入", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText nameEdt = (EditText) item.findViewById(R.id.emergencyNameEdt);
                        EditText nickNameEdt = (EditText) item.findViewById(R.id.emergencyNickNameEdt);
                        EditText phoneEdt = (EditText) item.findViewById(R.id.emergencyPhoneEdt);
                        String name = nameEdt.getText().toString();
                        String phone = phoneEdt.getText().toString();
                        String nickName = nickNameEdt.getText().toString();
                        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(nickName)) {
                            Toast.makeText(getContext(), "資料輸入不完全,請確認資料已輸入", Toast.LENGTH_SHORT).show();
                        } else {
                            db.insertPersonalInformation(name, nickName, phone);
                            ArrayList<emergencyItem> itemArrayList = new ArrayList<>();
                            Cursor cursor = db.getAllData();
                            cursor.moveToFirst();
                            while (!cursor.isAfterLast()) {
                                eItem = new emergencyItem();
                                eItem.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.emergency_TABLE_id)));
                                eItem.setName(cursor.getString(cursor.getColumnIndex(DBHelper.emergency_TABLE_name)));
                                eItem.setNickName(cursor.getString(cursor.getColumnIndex(DBHelper.emergency_TABLE_nickName)));
                                eItem.setPhone(cursor.getString(cursor.getColumnIndex(DBHelper.emergency_TABLE_phone)));
                                itemArrayList.add(eItem);
                                cursor.moveToNext();
                            }

                            final emergencyAdapter adapter = new emergencyAdapter(db,getContext(), itemArrayList);
                            Lv.setAdapter(adapter);
                        }

                    }
                }).show();

            }
        });


    }
}
