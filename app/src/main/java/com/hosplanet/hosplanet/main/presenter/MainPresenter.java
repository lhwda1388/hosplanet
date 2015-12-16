package com.hosplanet.hosplanet.main.presenter;

import com.hosplanet.hoplanet.api.HospitalInfoApiBean;

import java.util.List;

/**
 * Created by hyunwoo-Lee on 2015-12-15.
 */
public interface MainPresenter {
    void setView(MainPresenter.View view);

    void getList(HospitalInfoApiBean hospitalInfoApiBean);

    public interface View {
        void getHostPitalList(HospitalInfoApiBean hospitalInfoApiBean);
    }
}
