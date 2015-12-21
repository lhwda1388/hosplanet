package com.hosplanet.hosinfo.presenter;

import com.hosplanet.api.HospitalInfoApiBean;
import com.hosplanet.main.presenter.MainPresenter;

/**
 * Created by hyunwoo-Lee on 2015-12-18.
 */
public interface HosMainPresenter {
    void setView(HosMainPresenter.View view);

    void getInfo(HospitalInfoApiBean hospitalInfoApiBean);
    public interface View {
        void getInfo(HospitalInfoApiBean hospitalInfoApiBean);
    }
}


