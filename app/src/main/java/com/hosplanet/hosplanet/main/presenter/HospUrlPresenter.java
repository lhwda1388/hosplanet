package com.hosplanet.hosplanet.main.presenter;

import com.hosplanet.hoplanet.api.HospitalInfoApiBean;

/**
 * Created by hyunwoo-Lee on 2015-12-15.
 */
public interface HospUrlPresenter {
    void setView(HospUrlPresenter.View view);

    void closeIntent();
    void showWebView(String hospUrl);
    public interface View {
        void closeIntent();
        void showWebView(String hospUrl);
    }
}
