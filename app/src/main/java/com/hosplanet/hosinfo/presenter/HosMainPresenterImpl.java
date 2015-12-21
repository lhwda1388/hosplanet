package com.hosplanet.hosinfo.presenter;

import android.app.Activity;

import com.hosplanet.api.HospitalInfoApiBean;

/**
 * Created by hyunwoo-Lee on 2015-12-18.
 */
public class HosMainPresenterImpl implements HosMainPresenter {
    private Activity activity;
    private View view;

    public HosMainPresenterImpl(Activity activity){
        this.activity = activity;
    }

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void getInfo(HospitalInfoApiBean hospitalInfoApiBean) {
        if(view != null){
            view.getInfo(hospitalInfoApiBean);
        }
    }
}
