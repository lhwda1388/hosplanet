package com.hosplanet.hosinfo.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

import com.hosplanet.main.presenter.MainPresenter;

public class HosMainActivity extends AppCompatActivity implements HosMainPresenter.View {
    private HosMainPresenter hosMainPresenter;
    private TextView hosInfoName;
    private TextView hosInfoType;
    private TextView hosInfoAddr;
    private TextView hosInfoUrl;
    private TextView hosInfoTell;

    private ListView m_ListView;
    private ArrayAdapter<String> m_Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hos_main);
        hosInfoName = (TextView)findViewById(R.id.hosInfoName);
        hosInfoType = (TextView)findViewById(R.id.hosInfos);
        hosInfoAddr = (TextView)findViewById(R.id.hosInfoAddr);
        hosInfoUrl = (TextView)findViewById(R.id.hosInfoUrl);
        hosInfoTell = (TextView)findViewById(R.id.hosInfoTell);
        hosMainPresenter = new HosMainPresenterImpl(HosMainActivity.this);
        hosMainPresenter.setView(this);

        Intent intent = getIntent();
        HospitalInfoApiBean hBean = new HospitalInfoApiBean();
        String yadmNm = intent.getStringExtra("com.hoplanet.yadmNm");
        String clCd = intent.getStringExtra("com.hoplanet.clCd");
        String clCdNm = intent.getStringExtra("com.hoplanet.clCdNm");
        String addr = intent.getStringExtra("com.hoplanet.addr");
        String hospUrl = intent.getStringExtra("com.hoplanet.hospUrl");
        String telno = intent.getStringExtra("com.hoplanet.telno");

        hBean.setYadmnm(yadmNm);
        hBean.setClCd(clCd);
        hBean.setClCdNm(clCdNm);
        hBean.setAddr(addr);
        hBean.setHospUrl(hospUrl);
        hBean.setTelno(telno);

        hosMainPresenter.getInfo(hBean);

        // Android에서 제공하는 string 문자열 하나를 출력 가능한 layout으로 어댑터 생성
        m_Adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);

        // Xml에서 추가한 ListView 연결
        m_ListView = (ListView) findViewById(R.id.reviewList);

        // ListView에 어댑터 연결
        m_ListView.setAdapter(m_Adapter);

        // ListView에 아이템 추가
        m_Adapter.add("하스스톤");
        m_Adapter.add("몬스터 헌터");
        m_Adapter.add("디아블로");
        m_Adapter.add("와우");
        m_Adapter.add("리니지");
        m_Adapter.add("안드로이드");
        m_Adapter.add("아이폰");
    }

    @Override
    public void getInfo(HospitalInfoApiBean hospitalInfoApiBean) {
        HospitalInfoAsyncTask hTask = new HospitalInfoAsyncTask(new AsyncResponse() {
            @Override
            public void processFinish(JSONObject jsonObject) throws Exception {

                JSONObject header = jsonObject.getJSONObject("response").getJSONObject("header");
                String resCode  = header.get("resultCode").toString();
                String resMsg = header.get("resultMsg").toString();

                if("00".equals(resCode)) {

                    JSONObject body = jsonObject.optJSONObject("response").optJSONObject("body");
                    JSONObject items = body.optJSONObject("items");
                    JSONObject item = null;

                    if (items == null) {
                        Toast.makeText(getApplicationContext(), R.string.noList, Toast.LENGTH_LONG).show();
                    } else {
                        item = items.optJSONObject("item");
                        hosInfoName.setText(item.getString("yadmNm"));
                        hosInfoType.setText(item.getString("clCdNm"));
                        hosInfoAddr.setText(item.getString("addr"));
                        hosInfoUrl.setText(item.getString("hospUrl"));
                        hosInfoTell.setText(item.getString("telno"));

                        hosInfoUrl.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "URL 클릭", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }else{
                    Toast.makeText(getApplicationContext(), resMsg, Toast.LENGTH_LONG).show();
                }
            }

        });
        hTask.execute(hospitalInfoApiBean);

    }
}
