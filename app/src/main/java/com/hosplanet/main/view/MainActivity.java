package com.hosplanet.main.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SearchView;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hosplanet.api.AsyncResponse;
import com.hosplanet.api.HospitalInfoApiBean;
import com.hosplanet.api.HospitalInfoApiClient;
import com.hosplanet.api.HospitalInfoAsyncTask;
import com.hosplanet.R;
import com.hosplanet.hosinfo.view.HosMainActivity;
import com.hosplanet.main.presenter.MainPresenter;
import com.hosplanet.main.presenter.MainPresenterImpl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements MainPresenter.View{
    private MainPresenter mainPresenter;
    private ListView hosListView;
    private HospitalListAdapter hospitalListAdapter;
    private SearchView searchView;
    private Button btnAllList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenterImpl(MainActivity.this);
        mainPresenter.setView(this);
        mainPresenter.getList(null);

        searchView = (SearchView)findViewById(R.id.searchView);
        searchView.setQueryHint(getString(R.string.searchVIewHint));
        //searchView.setQuery("가톨릭대학교인천성모병원", true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i("Query: ", query);
                mainPresenter.getList(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        btnAllList = (Button)findViewById(R.id.btnAllList);
        btnAllList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                searchView.setQuery("", false);
                mainPresenter.getList(null);
            }
        });
    }

    @Override
    public void getList(Object object) {
        HospitalInfoApiBean hospitalInfoApiBean= new HospitalInfoApiBean();
        if(object != null){
            Log.i("SEARCHVIEWTEXT",(String)object);
            hospitalInfoApiBean.setYadmnm((String) object);
        }else{
            hospitalInfoApiBean.setYadmnm(null);
        }

        HospitalInfoAsyncTask h = new HospitalInfoAsyncTask(new AsyncResponse(){
            @Override
            public void processFinish(JSONObject jsonObject) throws Exception {
                JSONObject header = jsonObject.getJSONObject("response").getJSONObject("header");
                String resCode  = header.get("resultCode").toString();
                String resMsg = header.get("resultMsg").toString();
                Log.i("JSONOBJECT",jsonObject.toString());
                if("00".equals(resCode)){
                    JSONObject body = jsonObject.optJSONObject("response").optJSONObject("body");
                    JSONObject items = body.optJSONObject("items");
                    JSONArray jsonArray = null;
                    if(items == null){
                        Toast.makeText(getApplicationContext(), R.string.noList, Toast.LENGTH_LONG).show();
                    }else{
                        jsonArray = items.optJSONArray("item");
                    }

                    String numOfRows = body.get("numOfRows").toString();
                    String pageNo = body.get("pageNo").toString();
                    String totalCount = body.get("totalCount").toString();

                    hosListView = (ListView)findViewById(R.id.hosListView);
                    hospitalListAdapter = new HospitalListAdapter(getApplicationContext(),R.layout.hoslist_info);
                    hospitalListAdapter.setMainPresenter(mainPresenter);
                    hosListView.setAdapter(hospitalListAdapter);
                    if(jsonArray == null){
                        JSONObject item = null;
                        if (items != null) {
                            item = items.optJSONObject("item");
                        }
                        hospitalListAdapter.add(HospitalInfoApiClient.getJObjectFromHBean(item));
                    }else{
                        Log.i("arraLength",Integer.toString(jsonArray.length()));
                        for(int i=0; i<jsonArray.length(); i++) {
                            JSONObject item = jsonArray.optJSONObject(i);
                            hospitalListAdapter.add(HospitalInfoApiClient.getJObjectFromHBean(item));
                        }

                    }

                    hosListView.setOnItemClickListener(new ListView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            HospitalInfoApiBean hBean = hospitalListAdapter.getItem(position);
                            Intent intent = new Intent(MainActivity.this, HosMainActivity.class);
                            intent.putExtra("com.hoplanet.yadmNm", hBean.getYadmnm());
                            intent.putExtra("com.hoplanet.clCd", hBean.getClCd());
                            intent.putExtra("com.hoplanet.addr", hBean.getAddr());
                            intent.putExtra("com.hoplanet.clCdNm", hBean.getClCdNm());
                            startActivity(intent);
                        }
                    });
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
