package com.hosplanet.main.presenter;

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
