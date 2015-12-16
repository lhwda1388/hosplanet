package com.hosplanet.hosplanet.main.presenter;

/**
 * Created by hyunwoo-Lee on 2015-12-15.
 */
public interface MainPresenter {
    void setView(MainPresenter.View view);

    void onConfirm();

    public interface View {

    }
}
