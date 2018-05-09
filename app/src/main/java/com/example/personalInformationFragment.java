package com.example;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.DataBase.DBHelper;
import com.example.DataBase.personalInformationDAO;
import com.example.ShowViewList.ShowItem;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link personalInformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class personalInformationFragment extends Fragment {

    personalInformationDAO db;
    TextView name2;
    TextView phone2;
    TextView address2;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public personalInformationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment personalInformationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static personalInformationFragment newInstance(String param1, String param2) {
        personalInformationFragment fragment = new personalInformationFragment();
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
        View view = inflater.inflate(R.layout.fragment_personal_information, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        db=new personalInformationDAO(getContext());
//        name2=(TextView)view.findViewById(R.id.name2_Txv);
//        phone2=(TextView)view.findViewById(R.id.phone2_Txv);
//        address2=(TextView)view.findViewById(R.id.address2_Txv);
//        Cursor cursor=db.getAllData();
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()){
////            Log.w("personalInformation",cursor.getString(cursor.getColumnIndex(DBHelper.personalInformation_TABLE_name))+"");
//            name2.setText(Html.fromHtml("<u>"+cursor.getString(cursor.getColumnIndex(DBHelper.personalInformation_TABLE_name))+"</u>"));
//            phone2.setText(Html.fromHtml("<u>"+cursor.getString(cursor.getColumnIndex(DBHelper.personalInformation_TABLE_phone))+"</u>"));
//            address2.setText(Html.fromHtml("<u>"+cursor.getString(cursor.getColumnIndex(DBHelper.personalInformation_TABLE_address))+"</u>"));
//            cursor.moveToNext();
//        }

//        Button btn = (Button)view.findViewById(R.id.personalSet_Btn);
//        btn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                final View item= LayoutInflater.from(getContext()).inflate(R.layout.activity_set_personal_information,null);
//
//                //彈出框
//                new AlertDialog.Builder(getContext())
//                        .setTitle("輸入你的個人資料")//標題顯示列
//                        .setView(item)
//                        //確定按鈕
//                        .setPositiveButton("確定輸入", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                EditText nameEdt = (EditText) item.findViewById(R.id.nameEdt);
//                                EditText phoneEdt=(EditText)item.findViewById(R.id.phoneEdt);
//                                EditText addressEdt=(EditText)item.findViewById(R.id.addressEdt);
//                                String name = nameEdt.getText().toString();
//                                String phone = phoneEdt.getText().toString();
//                                String address=addressEdt.getText().toString();
//                                if(TextUtils.isEmpty(name)||TextUtils.isEmpty(phone)||TextUtils.isEmpty(address)){
//                                    //無輸入時顯示
//                                    Toast.makeText(getContext(), "資料輸入不完全,請確認資料", Toast.LENGTH_SHORT).show();
//
//                                } else {
//                                    ShowItem showItem=new ShowItem();
//                                    showItem.setP_name(name);
//                                    showItem.setP_phone(phone);
//                                    showItem.setP_address(address);
////                            showItem.setP_password("");
////                            showItem.setP_account("");
////                            showItem.setP_id(0);
////                            showItem.setP_datetime("");
//                                    db.deleteInformation();
//                                    db.insertPersonalInformation(showItem);
//                                    name2.setText(Html.fromHtml("<u>"+nameEdt.getText().toString()+"</u>"));
//                                    phone2.setText(Html.fromHtml("<u>"+phoneEdt.getText().toString()+"</u>"));
//                                    address2.setText(Html.fromHtml("<u>"+addressEdt.getText().toString()+"</u>"));
//
//                                }
//
//                            }
//                        })
//                        .show();
//
//            }
//        });


    }

}
