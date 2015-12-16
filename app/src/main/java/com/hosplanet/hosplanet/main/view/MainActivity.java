package com.hosplanet.hosplanet.main.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hosplanet.hoplanet.api.AsyncResponse;
import com.hosplanet.hoplanet.api.HospitalInfoApiBean;
import com.hosplanet.hoplanet.api.HospitalInfoAsyncTask;
import com.hosplanet.hosplanet.R;
import com.hosplanet.hosplanet.main.presenter.MainPresenter;
import com.hosplanet.hosplanet.main.presenter.MainPresenterImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainPresenter.View{
    private MainPresenter mainPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HospitalInfoApiBean hBean = new HospitalInfoApiBean();
        mainPresenter = new MainPresenterImpl(MainActivity.this);
        mainPresenter.setView(this);
        mainPresenter.getList(hBean);
    }

    @Override
    public void getHostPitalList(HospitalInfoApiBean hospitalInfoApiBean) {
        HospitalInfoAsyncTask h = new HospitalInfoAsyncTask(new AsyncResponse(){
            @Override
            public void processFinish(List<HospitalInfoApiBean> hospitalInfoApiBean) {

                System.out.println(hospitalInfoApiBean);
            }
        });
        h.execute(hospitalInfoApiBean);
    }
}
