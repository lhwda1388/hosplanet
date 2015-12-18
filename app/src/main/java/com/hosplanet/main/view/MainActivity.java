package com.hosplanet.main.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hosplanet.api.AsyncResponse;
import com.hosplanet.api.HospitalInfoApiBean;
import com.hosplanet.api.HospitalInfoApiClient;
import com.hosplanet.api.HospitalInfoAsyncTask;
import com.hosplanet.R;
import com.hosplanet.main.presenter.MainPresenter;
import com.hosplanet.main.presenter.MainPresenterImpl;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements MainPresenter.View{
    private MainPresenter mainPresenter;
    private ListView hosListView;
    private HospitalListAdapter hospitalListAdapter;
    private TextView hosUrl;
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenterImpl(MainActivity.this);
        mainPresenter.setView(this);
        mainPresenter.getList();

        searchView = (SearchView)findViewById(R.id.searchView);
        searchView.setQueryHint(getString(R.string.searchVIewHint));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mainPresenter.getList();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    @Override
    public void getHostPitalList() {
        HospitalInfoApiBean hospitalInfoApiBean= new HospitalInfoApiBean();
        HospitalInfoAsyncTask h = new HospitalInfoAsyncTask(new AsyncResponse(){
            @Override
            public void processFinish(JSONObject jsonObject) throws Exception {
       String jjj=         "{                                                                                                              "+
                        "  \"response\": {                                                                                              "+
                        "    \"header\": {                                                                                              "+
                        "      \"resultCode\": \"00\",                                                                                  "+
                        "      \"resultMsg\": \"NORMAL SERVICE.\"                                                                       "+
                        "    },                                                                                                         "+
                        "    \"body\": {                                                                                                "+
                        "      \"items\": {                                                                                             "+
                        "        \"item\": [                                                                                            "+
                        "          {                                                                                                    "+
                        "            \"addr\": \"서울특별시 중랑구 신내로 156 (신내동)\",                                               "+
                        "            \"clCd\": \"11\",                                                                                  "+
                        "            \"clCdNm\": \"종합병원\",                                                                          "+
                        "            \"distance\": \"0\",                                                                               "+
                        "            \"drTotCnt\": \"188\",                                                                             "+
                        "            \"emdongNm\": \"신내동\",                                                                          "+
                        "            \"estbDd\": \"20110314\",                                                                          "+
                        "            \"gdrCnt\": \"4\",                                                                                 "+
                        "            \"hospUrl\": \"http://www.seoulmc.or.kr\",                                                         "+
                        "            \"intnCnt\": \"30\",                                                                               "+
                        "            \"postNo\": \"131865\",                                                                            "+
                        "            \"resdntCnt\": \"57\",                                                                             "+
                        "            \"sdrCnt\": \"97\",                                                                                "+
                        "            \"sgguCd\": \"110019\",                                                                            "+
                        "            \"sgguCdNm\": \"중랑구\",                                                                          "+
                        "            \"sidoCd\": \"110000\",                                                                            "+
                        "            \"sidoCdNm\": \"서울\",                                                                            "+
                        "            \"telno\": \"02-2276-7000\",                                                                       "+
                        "            \"XPos\": \"127.09854004628151\",                                                                  "+
                        "            \"YPos\": \"37.6132113197367\",                                                                    "+
                        "            \"yadmNm\": \"서울특별시서울의료원\",                                                              "+
                        "            \"ykiho\": \"                                                                                      "+
                        "                        JDQ4MTg4MSM1MSMkMSMkMCMkODkkMzgxMzUxIzExIyQyIyQ3IyQwMCQyNjEyMjIjNjEjJDEjJDgjJDgz       "+
                        "                        \"                                                                                     "+
                        "          },                                                                                                   "+
                        "          {                                                                                                    "+
                        "            \"addr\": \"서울특별시 중랑구 신내로 156 (신내동)\",                                               "+
                        "            \"clCd\": \"11\",                                                                                  "+
                        "            \"clCdNm\": \"종합병원2\",                                                                          "+
                        "            \"distance\": \"0\",                                                                               "+
                        "            \"drTotCnt\": \"188\",                                                                             "+
                        "            \"emdongNm\": \"신내동\",                                                                          "+
                        "            \"estbDd\": \"20110314\",                                                                          "+
                        "            \"gdrCnt\": \"4\",                                                                                 "+
                        "            \"hospUrl\": \"http://www.naver.com\",                                                         "+
                        "            \"intnCnt\": \"30\",                                                                               "+
                        "            \"postNo\": \"131865\",                                                                            "+
                        "            \"resdntCnt\": \"57\",                                                                             "+
                        "            \"sdrCnt\": \"97\",                                                                                "+
                        "            \"sgguCd\": \"110019\",                                                                            "+
                        "            \"sgguCdNm\": \"중랑구\",                                                                          "+
                        "            \"sidoCd\": \"110000\",                                                                            "+
                        "            \"sidoCdNm\": \"서울\",                                                                            "+
                        "            \"telno\": \"02-2276-7000\",                                                                       "+
                        "            \"XPos\": \"127.09854004628151\",                                                                  "+
                        "            \"YPos\": \"37.6132113197367\",                                                                    "+
                        "            \"yadmNm\": \"서울특별시서울의료원\",                                                              "+
                        "            \"ykiho\": \"                                                                                      "+
                        "                        JDQ4MTg4MSM1MSMkMSMkMCMkODkkMzgxMzUxIzExIyQyIyQ3IyQwMCQyNjEyMjIjNjEjJDEjJDgjJDgz       "+
                        "                        \"                                                                                     "+
                        "          }                                                                                                    "+
                        "        ]                                                                                                      "+
                        "      },                                                                                                       "+
                        "      \"numOfRows\": \"10\",                                                                                   "+
                        "      \"pageNo\": \"1\",                                                                                       "+
                        "      \"totalCount\": \"1\"                                                                                    "+
                        "    }                                                                                                          "+
                        "  }                                                                                                            "+
                        "}																												";
                jsonObject = new JSONObject(jjj);

                Log.i("JSONOBJECT",jsonObject.toString());
                JSONObject header = jsonObject.getJSONObject("response").getJSONObject("header");
                String resCode  = header.get("resultCode").toString();
                String resMsg = header.get("resultMsg").toString();

                if("00".equals(resCode)){
                    JSONObject body = jsonObject.getJSONObject("response").getJSONObject("body");
                    JSONArray jsonArray = body.getJSONObject("items").getJSONArray("item");
                    String numOfRows = body.get("numOfRows").toString();
                    String pageNo = body.get("pageNo").toString();
                    String totalCount = body.get("totalCount").toString();
                    Log.i("arraLength",Integer.toString(jsonArray.length()));

                    hosListView = (ListView)findViewById(R.id.hosListView);
                    hospitalListAdapter = new HospitalListAdapter(getApplicationContext(),R.layout.hos_info);
                    hospitalListAdapter.setMainPresenter(mainPresenter);
                    hosListView.setAdapter(hospitalListAdapter);
                    for(int i=0; i<jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i);
                        hospitalListAdapter.add(HospitalInfoApiClient.getJObjectFromHBean(item));
                    }

                }else{
                    Toast.makeText(getApplicationContext(), resMsg, Toast.LENGTH_LONG).show();

                }

            }
        });
        h.execute(hospitalInfoApiBean);
    }

    @Override
    public void goUrl(String url) {
        Log.i("MAINGOURL",url);
        if(url.equals("") || url == null){
            Toast.makeText(getApplicationContext(),"NOT FOUND URL.",Toast.LENGTH_LONG).show();
        }else{
            Intent intent = new Intent(this,HospUrlActivity.class);
            intent.putExtra("com.hosplanet.HOSPURL", url);
            startActivity(intent);
        }
    }
}
