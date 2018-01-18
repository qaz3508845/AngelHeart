package com.example.ShowViewList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.R;
import com.example.ShowViewList.ShowItem;

import java.util.ArrayList;

/**
 * Created by qaz35 on 2018/1/15/015.
 */

public class myAdapter extends BaseAdapter{

    private ArrayList<ShowItem> data;

    private LayoutInflater showInflater;


    public myAdapter(Context mContext,ArrayList<ShowItem> data) {
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
        ViewHolder holder;
        ShowItem showItem=data.get(position);
        if(view==null){
            view=showInflater.inflate(R.layout.showlist,null);
            holder=new ViewHolder((TextView)view.findViewById(R.id.id_Txv),
                    (TextView)view.findViewById(R.id.account_Txv),
                    (TextView)view.findViewById(R.id.password_Txv),
                    (TextView)view.findViewById(R.id.phone_Txv));
            view.setTag(holder);
        }else {
            holder=(ViewHolder)view.getTag();
        }

        holder.id_Txv.setText(showItem.getId()+"");
        holder.account_Txv.setText(showItem.getAccount());
        holder.password_Txv.setText(showItem.getPassword());
        holder.phone_Txv.setText(showItem.getPhone());



        return view;
    }
    private class ViewHolder {
        TextView id_Txv;
        TextView account_Txv;
        TextView password_Txv;
        TextView phone_Txv;

        public ViewHolder(TextView id_Txv, TextView account_Txv, TextView password_Txv, TextView phone_Txv) {
            this.id_Txv = id_Txv;
            this.account_Txv = account_Txv;
            this.password_Txv = password_Txv;
            this.phone_Txv = phone_Txv;
        }
    }
}
