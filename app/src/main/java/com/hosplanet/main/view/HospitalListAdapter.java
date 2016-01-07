package com.hosplanet.main.view;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hosplanet.api.HospitalInfoApiBean;
import com.hosplanet.R;
import com.hosplanet.main.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyunwoo-Lee on 2015-12-17.
 */
public class HospitalListAdapter extends ArrayAdapter<HospitalInfoApiBean>{
    List<HospitalInfoApiBean> list = new ArrayList<HospitalInfoApiBean>();
    MainPresenter mainPresenter  = null;

    public HospitalListAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void setMainPresenter(MainPresenter mainPresenter){
        this.mainPresenter = mainPresenter;
    }
    public MainPresenter getMainPresenter(){
        return this.mainPresenter;
    }

    @Override
    public void add(HospitalInfoApiBean object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public HospitalInfoApiBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.hoslist_info,parent,false);
        }
        HospitalInfoApiBean data = getItem(position);
        TextView hosInfo = (TextView)v.findViewById(R.id.hosName);
        TextView hosUrl = (TextView)v.findViewById(R.id.hosUrl);

        hosInfo.setText(data.getYadmnm());
        hosUrl.setText(data.getHospUrl());
        hosUrl.setPaintFlags(hosUrl.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        hosUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.goUrl(getItem(position).getHospUrl());
            }
        });

        return v;
    }
}
