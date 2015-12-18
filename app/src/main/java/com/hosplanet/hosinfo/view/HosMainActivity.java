package com.hosplanet.hosinfo.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.hosplanet.R;
import com.hosplanet.hosinfo.presenter.HosMainPresenter;
import com.hosplanet.hosinfo.presenter.HosMainPresenterImpl;

public class HosMainActivity extends AppCompatActivity implements HosMainPresenter.View {
    private HosMainPresenter hosMainPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hos_main);
        hosMainPresenter = new HosMainPresenterImpl(HosMainActivity.this);
        hosMainPresenter.setView(this);

        Intent intent = getIntent();
        String ykiho = intent.getStringExtra("com.hoplanet.ykiho");
        Log.i("GETYKIHO",ykiho);

    }
}
