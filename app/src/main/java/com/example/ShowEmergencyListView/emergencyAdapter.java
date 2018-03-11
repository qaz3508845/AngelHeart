package com.example.ShowEmergencyListView;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;


import com.example.DataBase.voiceDAO;
import com.example.R;
import com.example.ShowViewList.ShowItem;
import com.example.ShowViewList.myAdapter;
import com.example.voiceSettingActivity;

import java.util.ArrayList;

/**
 * Created by qaz35 on 2018/1/24/024.
 */

public class emergencyAdapter extends BaseAdapter {
    private ArrayList<emergencyItem> data;

    private LayoutInflater showInflater;
    voiceDAO db;
    public emergencyAdapter(voiceDAO db, Context mContext, ArrayList<emergencyItem> data) {
        this.data = data;
        this.db=db;
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
            view = showInflater.inflate(R.layout.voicelist, null);
            holder = new emergencyAdapter.ViewHolder((TextView) view.findViewById(R.id.voiceNumber)
                    , (TextView) view.findViewById(R.id.voiceTranslation)
                    , (Button) view.findViewById(R.id.voiceDelete));
            view.setTag(holder);
        } else {
            holder = (emergencyAdapter.ViewHolder) view.getTag();
        }

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                //从集合中删除所删除项的EditText的内容

                Log.e("delete",position+"");
//                db.deleteInfo();
//                Log.e("Test",getItem(position).toString());
//                Log.e("TEST",getItemId(position)+"");
                Log.e("TEST",vItem.getV_id()+"");
                db.deleteInfo(vItem.getV_id()+"");
                data.remove(position);
                notifyDataSetChanged();

            }
        });


//        holder.voiceNumber.setText(vItem.getV_number() + "");
        holder.voiceNumber.setText(vItem.getV_number()+" ");
        holder.voiceTranslation.setText(vItem.getV_translationed());
        return view;



    }


    private class ViewHolder {
        TextView voiceNumber;
        TextView voiceTranslation;
        Button button;

        public ViewHolder(TextView voiceNumber, TextView voiceTranslation, Button button) {
            this.voiceNumber = voiceNumber;
            this.voiceTranslation = voiceTranslation;
            this.button = button;
        }
    }


}
