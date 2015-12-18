package com.hosplanet.main.presenter;

import com.hosplanet.api.HospitalInfoApiBean;

/**
 * Created by hyunwoo-Lee on 2015-12-15.
 */
public interface MainPresenter {
    void setView(MainPresenter.View view);

    void getList(Object object);
    void goUrl(String url);

    public interface View {
        void getList(Object object);
        void goUrl(String url);
    }
}
