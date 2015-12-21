package com.hosplanet.hosinfo.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.hosplanet.R;
import com.hosplanet.api.AsyncResponse;
import com.hosplanet.api.HospitalInfoApiBean;
import com.hosplanet.api.HospitalInfoAsyncTask;
import com.hosplanet.hosinfo.presenter.HosMainPresenter;
import com.hosplanet.hosinfo.presenter.HosMainPresenterImpl;

import org.json.JSONArray;
import org.json.JSONObject;

public class HosMainActivity extends AppCompatActivity implements HosMainPresenter.View {
    private HosMainPresenter hosMainPresenter;
    private TextView hosInfoName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hos_main);
        hosInfoName = (TextView)findViewById(R.id.hosInfoName);
        hosMainPresenter = new HosMainPresenterImpl(HosMainActivity.this);
        hosMainPresenter.setView(this);

        Intent intent = getIntent();
        HospitalInfoApiBean hBean = new HospitalInfoApiBean();
        String yadmNm = intent.getStringExtra("com.hoplanet.yadmNm");
        String clCd = intent.getStringExtra("com.hoplanet.clCd");

        hBean.setYadmnm(yadmNm);
        hBean.setClCd(clCd);
        hosMainPresenter.getInfo(hBean);
    }

    @Override
    public void getInfo(HospitalInfoApiBean hospitalInfoApiBean) {
        HospitalInfoAsyncTask hTask = new HospitalInfoAsyncTask(new AsyncResponse() {
            @Override
            public void processFinish(JSONObject jsonObject) throws Exception {
                Log.i("INFO", jsonObject.toString());
                JSONObject header = jsonObject.getJSONObject("response").getJSONObject("header");
                String resCode  = header.get("resultCode").toString();
                String resMsg = header.get("resultMsg").toString();
                Log.i("JSONOBJECT", jsonObject.toString());
                if("00".equals(resCode)) {
                    JSONObject body = jsonObject.optJSONObject("response").optJSONObject("body");
                    JSONObject items = body.optJSONObject("items");
                    JSONObject item = null;
                    if (items == null) {
                        Toast.makeText(getApplicationContext(), R.string.noList, Toast.LENGTH_LONG).show();
                    } else {
                        item = items.optJSONObject("item");
                        hosInfoName.setText(item.getString("yadmNm"));
                    }
                }else{
                    Toast.makeText(getApplicationContext(), resMsg, Toast.LENGTH_LONG).show();
                }
            }

        });
        hTask.execute(hospitalInfoApiBean);

    }
}
