package com.hosplanet.hosplanet.main.presenter;

import android.app.Activity;

import com.hosplanet.hosplanet.main.model.MainModel;


/**
 * Created by hyunwoo-Lee on 2015-12-15.
 */
public class MainPresenterImpl implements MainPresenter {
    private Activity activity;
    private MainPresenter.View view;
    private MainModel mainModel;

    public MainPresenterImpl(Activity activity){
        this.activity = activity;
        this.mainModel = new MainModel();
    }

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void onConfirm() {
        if(view != null){

        }
    }
}
