package com.example.ShowEmergencyListView;

import android.Manifest;
import android.Manifest.permission.*;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.DataBase.voiceDAO;
import com.example.MainActivity;
import com.example.R;

import java.util.ArrayList;

/**
 * Created by qaz35 on 2018/1/24/024.
 */

public class emergencyAdapter extends BaseAdapter {
    private ArrayList<emergencyItem> data;

    private LayoutInflater showInflater;
    //    voiceDAO db;
    Context mContext;
    private final static String CALL = "intent.action.CALL";

    public emergencyAdapter(Context mContext, ArrayList<emergencyItem> data) {
        this.data = data;
//        this.db = db;
        this.mContext = mContext;
        showInflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.indexOf(getItem(position));
    }



    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        final emergencyAdapter.ViewHolder holder;

        final emergencyItem vItem = data.get(position);
        if (view == null) {
            view = showInflater.inflate(R.layout.emergencylist, null);
            holder = new emergencyAdapter.ViewHolder((TextView) view.findViewById(R.id.emergencyName)
                    , (TextView) view.findViewById(R.id.emergencyNickName)
                    , (TextView) view.findViewById(R.id.telephoneName)
                    , (Button) view.findViewById(R.id.newsBtn)
                    , (Button) view.findViewById(R.id.telephoneBtn)
                    , (Button) view.findViewById(R.id.mapBtn)
                    , (ImageView) view.findViewById(R.id.emergencyImg));
            view.setTag(holder);
        } else {
            holder = (emergencyAdapter.ViewHolder) view.getTag();
        }

        holder.telephoneName.setText(vItem.getPhone());
        holder.emergencyName.setText(vItem.getName());
        holder.emergencyNickName.setText(vItem.getNickName());

        holder.telephoneBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + holder.telephoneName.getText().toString()));
//                Intent intent=new Intent(mContext, MainActivity.class);
                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) mContext,
                            new String[]{Manifest.permission.CALL_PHONE},
                            1);
                }else{
                    mContext.startActivity(intent);
                }

//                view.getContext().startActivity();

            }
        });

//        holder.button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                //从集合中删除所删除项的EditText的内容
//
//                Log.e("delete", position + "");
////                db.deleteInfo();
////                Log.e("Test",getItem(position).toString());
////                Log.e("TEST",getItemId(position)+"");
//                Log.e("TEST", vItem.getV_id() + "");
//                db.deleteInfo(vItem.getV_id() + "");
//                data.remove(position);
//                notifyDataSetChanged();
//
//            }
//        });


//        holder.voiceNumber.setText(vItem.getV_number() + "");
//        holder.voiceNumber.setText(vItem.getV_number() + " ");
//        holder.voiceTranslation.setText(vItem.getV_translationed());
        return view;


    }


    private class ViewHolder {
        TextView emergencyName;
        TextView emergencyNickName;
        TextView telephoneName;
        Button newsBtn, telephoneBtn, mapBtn;
        ImageView imageBtn;

        public ViewHolder(TextView emergencyName, TextView emergencyNickName, TextView telephoneName
                , Button newsBtn, Button telephoneBtn, Button mapBtn
                , ImageView imageBtn) {
            this.emergencyName = emergencyName;
            this.emergencyNickName = emergencyNickName;
            this.telephoneName = telephoneName;
            this.newsBtn = newsBtn;
            this.telephoneBtn = telephoneBtn;
            this.mapBtn = mapBtn;
            this.imageBtn = imageBtn;

        }

    }


}
