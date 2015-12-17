package com.hosplanet.hosplanet.main.presenter;

import android.app.Activity;

import com.hosplanet.hoplanet.api.HospitalInfoApiBean;
import com.hosplanet.hosplanet.main.model.MainModel;


/**
 * Created by hyunwoo-Lee on 2015-12-15.
 */
public class HospUrlPresenterImpl implements HospUrlPresenter {
    private Activity activity;
    private View view;


    public HospUrlPresenterImpl(Activity activity){
        this.activity = activity;
    }

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void closeIntent() {
        if(view != null){
            view.closeIntent();
        }
    }

    @Override
    public void showWebView(String hospUrl) {
        if(view != null){
            view.showWebView(hospUrl);
        }
    }
}
