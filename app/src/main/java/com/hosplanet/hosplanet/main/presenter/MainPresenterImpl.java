package com.hosplanet.hosplanet.main.presenter;

import android.app.Activity;

import com.hosplanet.hoplanet.api.AsyncResponse;
import com.hosplanet.hoplanet.api.HospitalInfoApiBean;
import com.hosplanet.hoplanet.api.HospitalInfoAsyncTask;
import com.hosplanet.hosplanet.main.model.MainModel;

import java.util.List;


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
    public void getList(HospitalInfoApiBean hospitalInfoApiBean) {
        if(view != null) {
            view.getHostPitalList(hospitalInfoApiBean);
        }
    }

}
