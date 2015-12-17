package com.hosplanet.hosplanet.main.view;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hosplanet.hoplanet.api.HospitalInfoApiBean;
import com.hosplanet.hosplanet.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyunwoo-Lee on 2015-12-17.
 */
public class HospitalListAdapter extends ArrayAdapter<HospitalInfoApiBean>{
    List<HospitalInfoApiBean> list = new ArrayList<HospitalInfoApiBean>();

    public HospitalListAdapter(Context context, int resource) {
        super(context, resource);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.hos_info,parent,false);
        }
        HospitalInfoApiBean data = getItem(position);
        TextView hosInfo = (TextView)v.findViewById(R.id.hosName);
        hosInfo.setText(data.getClCdNm());
        return v;
    }
}
