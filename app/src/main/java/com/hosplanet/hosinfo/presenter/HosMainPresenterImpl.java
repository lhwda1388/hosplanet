package com.hosplanet.hosinfo.presenter;

import android.app.Activity;

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
}
