package com.example.ShowVoiceListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.R;
import com.example.ShowViewList.ShowItem;
import com.example.ShowViewList.myAdapter;
import com.example.voiceSettingActivity;

import java.util.ArrayList;

/**
 * Created by qaz35 on 2018/1/24/024.
 */

public class voiceAdapter extends BaseAdapter {
    private ArrayList<voiceItem> data;

    private LayoutInflater showInflater;

    public voiceAdapter(Context mContext, ArrayList<voiceItem> data) {
        this.data=data;
        showInflater=LayoutInflater.from(mContext);
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        voiceAdapter.ViewHolder holder;
        voiceItem vItem=data.get(position);
        if(view==null){
            view=showInflater.inflate(R.layout.voicelist,null);
            holder=new voiceAdapter.ViewHolder((TextView)view.findViewById(R.id.voiceNumber),
                    (TextView)view.findViewById(R.id.voiceTranslation));
            view.setTag(holder);
        }else {
            holder=(voiceAdapter.ViewHolder)view.getTag();
        }

//        holder.voiceNumber.setText(i++ +"");
        holder.voiceNumber.setText(vItem.getV_number()+"");
        holder.voiceTranslation.setText(vItem.getV_translationed());
        return view;
    }
    private class ViewHolder {
        TextView voiceNumber;
        TextView voiceTranslation;

        public ViewHolder(TextView voiceNumber, TextView voiceTranslation) {
            this.voiceNumber = voiceNumber;
            this.voiceTranslation = voiceTranslation;
        }
    }
}
