package com.hosplanet.hosinfo.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hosplanet.R;
import com.hosplanet.api.AsyncResponse;
import com.hosplanet.api.HospitalInfoApiBean;
import com.hosplanet.api.HospitalInfoAsyncTask;
import com.hosplanet.common.util.CommonUtil;
import com.hosplanet.hosinfo.presenter.HosMainPresenter;
import com.hosplanet.hosinfo.presenter.HosMainPresenterImpl;
import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;

import org.json.JSONObject;



public class HosMainActivity extends NMapActivity implements HosMainPresenter.View {
    private HosMainPresenter hosMainPresenter;
    private TextView hosInfoName;
    private TextView hosInfoType;
    private TextView hosInfoAddr;
    private TextView hosInfoUrl;
    private TextView hosInfoTell;
    private ListView m_ListView;
    private ArrayAdapter<String> m_Adapter;
    private NMapView nMapView;
    private NMapController nMapController;

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
        Double xPos= intent.getDoubleExtra("com.hoplanet.xPos", 0);
        Double yPos= intent.getDoubleExtra("com.hoplanet.yPos", 0);

        hBean.setYadmnm(yadmNm);
        hBean.setClCd(clCd);
        hBean.setClCdNm(clCdNm);
        hBean.setAddr(addr);
        hBean.setHospUrl(hospUrl);
        hBean.setTelno(telno);
        hBean.setxPos(xPos);
        hBean.setyPos(yPos);

        hosMainPresenter.getInfo(hBean);
        m_Adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
        m_ListView = (ListView) findViewById(R.id.reviewList);
        m_ListView.setAdapter(m_Adapter);
        m_Adapter.add("하스스톤");
        m_Adapter.add("몬스터 헌터");
        m_Adapter.add("디아블로");
        m_Adapter.add("와우");
        m_Adapter.add("리니지");
        m_Adapter.add("안드로이드");
        m_Adapter.add("아이폰");

        hosMainPresenter.callMapView(hBean);
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
                        hosInfoUrl.setText(CommonUtil.sNullChk(item.optString("hospUrl"), ""));
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

    @Override
    public void callMapView(final HospitalInfoApiBean hospitalInfoApiBean) {
        // create map view
        nMapView = (NMapView)findViewById(R.id.map_view);
        nMapController = nMapView.getMapController();
        nMapView.setApiKey(CommonUtil.mapApiKey);
        // initialize map view
        nMapView.setClickable(true);
        nMapView.setEnabled(true);
        nMapView.setFocusable(true);
        nMapView.setFocusableInTouchMode(true);
        nMapView.requestFocus();


        nMapView.setOnMapStateChangeListener(new NMapView.OnMapStateChangeListener() {
            @Override
            public void onMapInitHandler(NMapView nMapView, NMapError nMapError) {
                if (nMapError == null) { // success
                    Log.i("SUCESSS???????","SUCESSS???????");
                    nMapController.setMapCenter(new NGeoPoint(hospitalInfoApiBean.getyPos(), hospitalInfoApiBean.getxPos()), 11);
                } else { // fail
                    Log.e("HosMainActivity", "onMapInitHandler: error=" + nMapError.toString());
                }
            }

            @Override
            public void onMapCenterChange(NMapView nMapView, NGeoPoint nGeoPoint) {

            }

            @Override
            public void onMapCenterChangeFine(NMapView nMapView) {

            }

            @Override
            public void onZoomLevelChange(NMapView nMapView, int i) {

            }

            @Override
            public void onAnimationStateChange(NMapView nMapView, int i, int i1) {

            }
        });

        nMapView.setOnMapViewTouchEventListener(new NMapView.OnMapViewTouchEventListener() {
            @Override
            public void onLongPress(NMapView nMapView, MotionEvent motionEvent) {

            }

            @Override
            public void onLongPressCanceled(NMapView nMapView) {

            }

            @Override
            public void onTouchDown(NMapView nMapView, MotionEvent motionEvent) {

            }

            @Override
            public void onTouchUp(NMapView nMapView, MotionEvent motionEvent) {

            }

            @Override
            public void onScroll(NMapView nMapView, MotionEvent motionEvent, MotionEvent motionEvent1) {

            }

            @Override
            public void onSingleTapUp(NMapView nMapView, MotionEvent motionEvent) {

            }
        });
    }

}
