package com.hosplanet.main.presenter;

import android.app.Activity;


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
